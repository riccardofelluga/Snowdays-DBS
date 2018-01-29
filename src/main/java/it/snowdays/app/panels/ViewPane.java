package it.snowdays.app.panels;


import it.snowdays.app.AddHandler;
import it.snowdays.app.DataHandler;
import it.snowdays.app.TableHandler;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private static TableView<ArrayList<String>> table = TableHandler.generateTable();
    
    private static ViewPane instance = new ViewPane();

    private ViewPane(){
        getChildren().add(options);
        getChildren().add(table);
        setVgrow(table, Priority.ALWAYS);
    }
    public static ViewPane getInstance(){
        return instance;
    }

    public void updateView(){
        table = TableHandler.generateTable();
        getChildren().set(1, table);
        setVgrow(table, Priority.ALWAYS);
        options.populateSelector();
    }

    private class OptionsPane extends GridPane{

        private ChoiceBox<String> selector = null;

        public OptionsPane(){

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
    
            Button addBtn = new Button("+");
            addBtn.setOnAction(e -> {
                AddHandler.promptAdd();
                DataHandler.getInstance().reloadRemote();//fetch again the DB
                updateView();
            });

            Button rstButton = new Button("X");
            rstButton.setOnAction(e -> {
                //set back the dataset to default
                searchField.clear();
                DataHandler.getInstance().resetLocal();
                ViewPane.getInstance().updateView();
            });


            //syling
            setStyle("-fx-background-color: #87cefa");    
            

            addColumn(0, addBtn);
            addColumn(1, searchField);
            addColumn(2, selector);
            addColumn(3, rstButton);

        }

        private void search(String input, String tab){

            if(input.equals("") || input.equals(null)){
                DataHandler.getInstance().resetLocal();
                ViewPane.getInstance().updateView();
                return;
            }

            //reset before search!
            DataHandler.getInstance().resetLocal();

            ArrayList<ArrayList<String>> searchres = new ArrayList<ArrayList<String>>();

            //firstly we get the nuber of the col selected
            int t = 0, c = 0;
            for (String s : DataHandler.getInstance().getLocal().get(0)) {
                if(s.equals(tab))
                    c=t;
                t++;
            }

            boolean first = true;

            for (ArrayList<String> p : DataHandler.getInstance().getLocal()) {
               
                if(first){//to avoid the cols names to be shown in result
                    first = false;
                } else {
                    if(p.get(c).toUpperCase().contains(input.toUpperCase())){
                        searchres.add(p);
                    }     
                }
            }

            if(!searchres.isEmpty()){
                //add first to keep the col names
                searchres.add(0, DataHandler.getInstance().getLocal().get(0));
                DataHandler.getInstance().setLocal(searchres);
                ViewPane.getInstance().updateView();
                
            }else{
                Alert a = new Alert(AlertType.WARNING);
                a.setTitle("No results");
                a.setHeaderText("No results found for " + input);
                a.showAndWait();
                //reset table
                DataHandler.getInstance().resetLocal();
                ViewPane.getInstance().updateView();
            }
            
        }

        protected void populateSelector(){

            ArrayList<String> prevSelector = new ArrayList<String>();
            ArrayList<String> nextSelector = (DataHandler.getInstance().getLocal().size()!= 0)? DataHandler.getInstance().getLocal().get(0): new ArrayList<String>();

            if (selector.getItems().size() != 0){
                for (String t : selector.getItems()) {
                    prevSelector.add(t);
                }
            }

            if(!nextSelector.equals(prevSelector)){ //if diffrent headers change selector
                selector.getItems().clear();
                if(DataHandler.getInstance().getLocal().size() != 0 ){
                    boolean first = true;
                    for (String s : DataHandler.getInstance().getLocal().get(0)) {
                        selector.getItems().add(s);
                        if(first){
                            selector.setValue(s);
                            first = false;
                        }
                    }
                }   
            }
        }
    }
}

