package it.snowdays.app.panels;


import it.snowdays.app.TableHandler;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

        public OptionsPane(){

            setPadding(new Insets(10, 10, 10, 10));
            setHgap(10);

            TextField searchField = new TextField();
            setHgrow(searchField, Priority.ALWAYS);

            ChoiceBox<String> daySelector = new ChoiceBox<>();
            ChoiceBox<String> selector = new ChoiceBox<String>();
    
            selector.getItems().add(0, "Tab1");
            selector.getItems().add(1, "Tab2");
            selector.setValue("Tab1");
    
            addColumn(0, daySelector);
            addColumn(1, searchField);
            addColumn(2, selector);

        }
    }

}

