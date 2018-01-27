package it.snowdays.app;

import java.util.ArrayList;
import java.util.Optional;

import javax.sound.sampled.BooleanControl;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

/**
 * AddHandler
 */ 

public class AddHandler {
    
    private static Dialog<String> d;

    private static ArrayList<String> collectedData;

    public AddHandler(){}

    public static void promptAdd(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        d = new Dialog<>();
        collectedData = new ArrayList<String>();
        ButtonType addBtnType = new ButtonType("Add", ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().addAll(addBtnType, ButtonType.CANCEL);
        //guard set
        Node addBtn = d.getDialogPane().lookupButton(addBtnType);
        addBtn.setDisable(true);

        //generate an array list and get from all -> colud work let's see
        int r = 0;
        ArrayList<TextField> fields = new ArrayList<TextField>();
        
        //populate the dialog
        for (String s : DataHandler.getInstance().getHeader()) {
            TextField t = new TextField();
            t.setPromptText(s);
            t.textProperty().addListener((observable, oldValue, newValue) -> {
                boolean c = true;
                for(TextField ex : fields){
                    if(ex.getText().trim().isEmpty())
                        c = false;
                }
                addBtn.setDisable(!(c && !newValue.trim().isEmpty()));
            });

            fields.add(t);
            grid.add(new Label(s + ":"),0,r);
            grid.add(t,1,r);
            r++;
        }

        d.setTitle("Add...");
        d.getDialogPane().setContent(grid);

        Optional<String> o = d.showAndWait();

        if(o.isPresent()){
            for (TextField t : fields) {       
                collectedData.add(t.getText());
            }
        }
        
        if(insertRemote()){
            return;
        }else{
            //error message
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Unable to insert");
            a.showAndWait();
        }
    }

    private static boolean insertRemote(){
        if(collectedData == null)
            return false;
        return false;
        //BRAINSTORMING NEEDED
    }
}