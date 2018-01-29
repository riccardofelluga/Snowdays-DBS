package it.snowdays.app;

import java.util.ArrayList;
import java.util.Optional;
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
    
    private static Dialog<ButtonType> d;

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

        Optional<ButtonType> o = d.showAndWait();

        if(o.get() == addBtnType){
            for (TextField t : fields) {       
                collectedData.add(t.getText());
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
        
       
    }

    private static boolean insertRemote(){
        if(collectedData == null)
            return false;
        
        switch (DataHandler.getInstance().getTableName()) {
            case "participant":
                return insertParticipant();

            case "manageStops":
                insertStop();//CHANGE HERE
                break;

            case "manageStuffPayloads":
                insertStuffPayload();//CHANGE HERE
                break;

            default:
                break;
        }

        return false;

    }

    private static boolean insertParticipant(){ //insert for the pariticipant table
        boolean r1,r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO participant(stud_id, name, surname) VALUES ('" + collectedData.get(0) + "' , '" + collectedData.get(1) + "','" + collectedData.get(2) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO staff(stud_id, role) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(3) + "')");
        return r1 && r2;
    }
    private static boolean insertSportBSC(){ //insert for the pariticipant table
        boolean r1 = false;
        for(int i = 0; i < Integer.parseInt(collectedData.get(3)); i++)
            r1 = SQLFetcher.nonSelectQuery("INSERT INTO base_camp_thing(inventory_id, description, vat_no) VALUES ('" + (Integer.parseInt(collectedData.get(0))+i) + "' , '" + collectedData.get(1) + "','" + collectedData.get(2) + "')");
        return r1;
    }
    //CHANGE HERE
    private static void insertStop(){ //insert for stop & transport table
        SQLFetcher.nonSelectQuery("INSERT INTO stop(stop_id, name, departure_time, arrival_time) VALUES ('" + collectedData.get(0) + ", '" + collectedData.get(1) + "','" + collectedData.get(2) + "','" + collectedData.get(3) + "')");
        SQLFetcher.nonSelectQuery("INSERT INTO transport(transport_plateno) VALUES ('" + collectedData.get(4) + "')");
    }
    //CHANGE HERE
    private static void insertStuffPayload(){ // insert for base camp thing & transport table
        SQLFetcher.nonSelectQuery("INSERT INTO transport(transport_plateno) VALUES ('" + collectedData.get(0) + "')");
        SQLFetcher.nonSelectQuery("INSERT INTO base_camp_thing(description) VALUES ('" + collectedData.get(1) + "')");
    }


}