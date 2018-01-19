package it.snowdays.app;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

    static ArrayList<ArrayList<String>> data = null;

    private static TableHandler instance = new TableHandler();
    private static TableView<ArrayList<String>> table;

    private TableHandler() {
        //here only for testing
        data = SQLFetcher.getData("SELECT per.dj_name, per.start_hour, per.end_hour,  l.name, l.address, e.theme FROM performance per RIGHT JOIN performed_at pat ON per.performance_id = pat.performance_id,	performed_at pa RIGHT JOIN event ev ON pa.event_id = ev.event_id, event e RIGHT JOIN takes_place_at p ON e.event_id = p.event_id, takes_place_at t RIGHT JOIN location l ON t.location_id = l.location_id WHERE e.type = 'party' ");
    }

    public static TableHandler getInstance() {
        return instance;
    }

    public static TableView<ArrayList<String>> getCurrentTable() {

        table = new TableView<ArrayList<String>>();
        
        if(data == null || data.size() == 0)//if never set or empty return empty table
            return table;

        ObservableList<ArrayList<String>> inTableData = FXCollections.observableArrayList();
        inTableData.addAll(data);
        inTableData.remove(0);
        for(int i = 0; i<data.get(0).size(); i++){
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
            //custom things here!

            table.getColumns().add(tc);
            table.setEditable(true);
        }
         
        table.setItems(inTableData);

        return table;
    }

    private static EventHandler<CellEditEvent<ArrayList<String>, String>> editEvent = new EventHandler<CellEditEvent<ArrayList<String>, String>>() {

		@Override
		public void handle(CellEditEvent<ArrayList<String>, String> event) {
            
            int tRow = event.getTablePosition().getRow();
            int tCol = event.getTablePosition().getColumn();

            ArrayList<String> tuple = event.getTableView().getItems().get(tRow);
            tuple.set(tCol, event.getNewValue());

            event.getTableView().getItems().set(tRow, tuple);

        }

    };

    public void setTableView(ArrayList<ArrayList<String>> inputDataSet) {
        data = inputDataSet;
    }

}