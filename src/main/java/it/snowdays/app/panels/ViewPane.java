package it.snowdays.app.panels;


import it.snowdays.app.TableHandler;
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
    //private static TableView<String> table = new TableView<String>();
    
    public ViewPane(){
        getChildren().add(options);
        getChildren().add(TableHandler.getCurrentTable());
        setVgrow(TableHandler.getCurrentTable(), Priority.ALWAYS);
    }
    
    public static void setNewTable(){
        
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

