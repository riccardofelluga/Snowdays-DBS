package it.snowdays.app.panels;


import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * ViewPane
 */
public class ViewPane extends VBox{

    private OptionsPane options = new OptionsPane();
    //private ObservableList tabContent; use it to have an always up-to-date list
    private static TableView<ArrayList<String>> table = TableHandler.getCurrentTable();
    
    private static ViewPane instance = new ViewPane();

    private ViewPane(){
        getChildren().add(options);
        getChildren().add(table);
        setVgrow(table, Priority.ALWAYS);
    }
    public static ViewPane getInstance(){
        return instance;
    }

    public void setNewTable(){
        table = TableHandler.getCurrentTable();
        
        this.getChildren().set(1, table);
    }
    private class OptionsPane extends GridPane{

        ChoiceBox<String> daySelector;
        ChoiceBox<String> selector;

        public OptionsPane(){

            daySelector = new ChoiceBox<>();
            selector = new ChoiceBox<String>();

            setPadding(new Insets(10, 10, 10, 10));
            setHgap(10);

            TextField searchField = new TextField();
            setHgrow(searchField, Priority.ALWAYS);

            searchField.setOnKeyPressed(e -> {
                if(e.getCode().equals(KeyCode.ENTER))//trigger only if enter is pressed
                    search(searchField.getText(), selector.getValue());
            });

            populateSelector();

            daySelector.getItems().add("Day 1");
            daySelector.getItems().add("Day 2");
            daySelector.getItems().add("Day 3");
            daySelector.setValue("Day 1");
    
            addColumn(0, daySelector);
            addColumn(1, searchField);
            addColumn(2, selector);

        }

        private void search(String input, String tab){

            if(input.equals("") || input.equals(null))
                return;

            ArrayList<ArrayList<String>> searchres = new ArrayList<ArrayList<String>>();

            //firstly we get the nuber of the col selected
            int t = 0, c = 0;
            for (String s : SQLFetcher.getList().get(0)) {
                if(s.equals(tab))
                    c=t;
                t++;
            }

            boolean first = true;

            for (ArrayList<String> p : SQLFetcher.getList()) {
               
                if(p.get(c).toUpperCase().contains(input.toUpperCase())){
                    
                    if(first)//to avoid the cols names to be shown in result
                        first = false;
                    else
                        searchres.add(p);
                }
                    

                
            }

            if(!searchres.isEmpty()){
                //add first to keep the col names
                searchres.add(0, SQLFetcher.getList().get(0));
                TableHandler.getInstance().setTableView(searchres);
                ViewPane.instance.setNewTable();
                
            }else{
                Alert a = new Alert(AlertType.WARNING);
                a.setTitle("No results");
                a.setHeaderText("No results found for " + input);
                a.showAndWait();
                //reset table
                TableHandler.getInstance().setTableView(SQLFetcher.getList());
                ViewPane.instance.setNewTable();
            }
            
        }

        private void populateSelector(){
            boolean first = true;
            for (String s : SQLFetcher.getList().get(0)) {
                selector.getItems().add(s);
                if(first){
                    selector.setValue(s);
                    first = false;
                }
            }
        }
    }

}

