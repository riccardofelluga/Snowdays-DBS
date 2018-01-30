package it.snowdays.app;

import java.util.ArrayList;

import it.snowdays.app.panels.ViewPane;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * TableHandler
 */
public class TableHandler {

    private static TableHandler instance = new TableHandler();
    private static TableView<ArrayList<String>> table;

    private TableHandler() {}

    public static TableHandler getInstance() {
        return instance;
    }

    public static TableView<ArrayList<String>> generateTable() {

        ArrayList<ArrayList<String>> data = DataHandler.getInstance().getLocal();

        table = new TableView<ArrayList<String>>();
        
        if(data == null || data.size() == 0)//if never set or empty return empty table
            return table;

        ObservableList<ArrayList<String>> inTableData = FXCollections.observableArrayList();

        inTableData.addAll(DataHandler.getInstance().getLocal());
        inTableData.remove(0);

        //column names 
        for(int i = 0; i<DataHandler.getInstance().getLocal().get(0).size(); i++){
            TableColumn<ArrayList<String>, String> tc = new TableColumn<ArrayList<String>, String>(data.get(0).get(i));
            final int c = i;
            Callback<CellDataFeatures<ArrayList<String>, String>, ObservableValue<String>> cb = new Callback<CellDataFeatures<ArrayList<String>, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(CellDataFeatures<ArrayList<String>, String> param) {
					return new SimpleStringProperty(param.getValue().get(c));
                }

            };

            tc.setCellValueFactory(cb);
            tc.setCellFactory(TextFieldTableCell.forTableColumn());
            tc.setOnEditCommit(editEvent);
            
            if(DataHandler.getInstance().getReadOnlyCols().contains(DataHandler.getInstance().getHeader().get(i)))
                tc.setEditable(false);
                
           
            //custom things here!
            if(!DataHandler.getInstance().isReadOnly()){
                table.setEditable(true);
            }
            table.getColumns().add(tc);
        }
        
        TableColumn<ArrayList<String>, String> delCol = new TableColumn<>("Remove");

        Callback<CellDataFeatures<ArrayList<String>, String>, ObservableValue<String>> delCb = new Callback<TableColumn.CellDataFeatures<ArrayList<String>,String>,ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<ArrayList<String>, String> param) {
                return new ReadOnlyObjectWrapper<String>(param.getValue().get(0));//first col
			}
        };

        delCol.setCellValueFactory(delCb);
        //try with button as type
        delCol.setCellFactory(param -> new TableCell<ArrayList<String>, String>(){
            private final Button delBtn = new Button("X");
            @Override
            protected void updateItem(String id, boolean empty){
                super.updateItem(id, empty);

                if(id == null){
                    setGraphic(null);
                    return;
                }

                setGraphic(delBtn);
                setAlignment(Pos.CENTER);
                delBtn.setOnAction(e->{
                    //SET DELETE
                    DeleteHandler d = new DeleteHandler();
                    d.deleteRemote(id);    
                });
            }
        });
        
        if(!DataHandler.getInstance().isReadOnly()){
            table.getColumns().add(delCol);
        }

        table.setItems(inTableData);

        return table;
    }

    private static EventHandler<CellEditEvent<ArrayList<String>, String>> editEvent = new EventHandler<CellEditEvent<ArrayList<String>, String>>() {

		@Override
		public void handle(CellEditEvent<ArrayList<String>, String> event) {
            
            int tRow = event.getTablePosition().getRow();
            int tCol = event.getTablePosition().getColumn();

            //SET UPDATE
            UpdateHandler u = new UpdateHandler();
            if(DataHandler.getInstance().getTableName().equals("sponsorshipStaff") && (DataHandler.getInstance().getHeader().get(tCol).equals("stud_id") || DataHandler.getInstance().getHeader().get(tCol).equals("vat_no"))){
                int s = DataHandler.getInstance().getHeader().size()-1;
                u.updateRemoteOther(event.getTableView().getItems().get(tRow).get(0), DataHandler.getInstance().getHeader().get(tCol), event.getNewValue(), DataHandler.getInstance().getHeader().get(s) , event.getTableView().getItems().get(tRow).get(4));
            }else if(tCol == 0)
                u.updateRemote(event.getOldValue(), DataHandler.getInstance().getHeader().get(tCol), event.getNewValue());
            else
                u.updateRemote(event.getTableView().getItems().get(tRow).get(0), DataHandler.getInstance().getHeader().get(tCol), event.getNewValue());
            

            DataHandler.getInstance().reloadRemote();
            ViewPane.getInstance().updateView();
            
            //removed local update --> useless
            // ArrayList<String> tuple = event.getTableView().getItems().get(tRow);
            // tuple.set(tCol, event.getNewValue());

            // event.getTableView().getItems().set(tRow, tuple);

            
        }

    };

}