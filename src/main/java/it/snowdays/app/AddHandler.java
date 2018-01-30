package it.snowdays.app;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Optional;

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
            if(!DataHandler.getInstance().getReadOnlyCols().contains(s)){
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
                return manageStop();

            case "manageStuffPayloads":
                return insertStuffPayloads();

            case "BSCTStuff":
                return insertBCT();

            case "accommodationLocation":
                return insertInManageLocationsAccommodation();

            case "manageHosts":
                return insertInManageHosts();

            case "playingDJs":
                return insertPlayingDJs();

            case "manageBreakfast":
                return insertManageBreakfast();

            case "manageLunch":
                return insertManageLunch();

            case "manageDinner":
                return insertManageDinner();

            case "managePeople":
                return insertManagePeople();

            case "sponsorshipStaff":
                return insertSponsorshipStaff();

            case "manageTeams":
                return insertInManageTeams();

            case "manageLocations":
                return manageLocations();
            
            case "manageTasks":
                return manageTasks();
                
            default:
                break;
        }

        return false;

    }

    private static boolean insertParticipant(){ //insert for the pariticipant table
        boolean r1,r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO participant(stud_id, name, surname) VALUES ('" + collectedData.get(0) + "', '" + collectedData.get(1) + "','" + collectedData.get(2) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO staff(stud_id, role) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(3) + "')");
        return r1 && r2;
    }
    private static boolean insertBCT(){ //insert for the pariticipant table
        boolean r1, r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO base_camp_thing(inventory_id, description, vat_no) VALUES ('" + collectedData.get(0) + "', '" + collectedData.get(1) + "','" + collectedData.get(2) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO chooses(stud_id, inventory_id) VALUES ('" + collectedData.get(3) + "' , '" + collectedData.get(0) + "')");
        return r1 && r2;
    }

    private static boolean manageStop(){ //insert for stop & transport table
        boolean r1, r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO stop(stop_id, name, departure_time, arrival_time) VALUES ('" + collectedData.get(0) + "', '" + collectedData.get(1) + "','" + collectedData.get(2) + "','" + collectedData.get(3) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO stops_at(transport_plateno, stop_id) VALUES ('" + collectedData.get(4) + "', '" + collectedData.get(0) + "')");
        return r1 && r2;
    }

    private static boolean insertStuffPayloads(){ // insert for base camp thing & transport table
        boolean r1;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO carries(inventory_id, transport_plateno) VALUES ('" + collectedData.get(0) + "', '" + collectedData.get(1) + "')");
        return r1;
    }
    private static boolean insertInManageLocationsAccommodation(){
        boolean r1;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO accommodation(accommodation_id, name, capacity, ref_phone_number ,location_id) VALUES ('" + collectedData.get(0) + "', '" + collectedData.get(1) + "','" + collectedData.get(2) + "','" + collectedData.get(3) + "', '" + collectedData.get(4) +  "' )");
        return r1;
    }
    private static boolean insertInManageHosts(){
        boolean r1;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO host(phone_no, name, surname, accommodation_id) VALUES ('" + collectedData.get(3) + "','" + collectedData.get(1) + "','" + collectedData.get(2) + "','" + collectedData.get(0) + "')");
        return r1;
    }

    private static boolean insertPlayingDJs(){
        boolean r1, r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO performance(performance_id, dj_name, start_hour, end_hour) VALUES ('" + collectedData.get(0) + "', '" + collectedData.get(1) + "','" + collectedData.get(2) + "','" + collectedData.get(3) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO performed_at(event_id,performance_id) VALUES ('" + collectedData.get(4) + "','" + collectedData.get(0) + "')");
        return r1 && r2;
    }


    private static boolean insertManageBreakfast(){
        boolean r1, r3;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO event(event_id, start_time, end_time, type) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(1) + "','" + collectedData.get(2) + "','breakfast')");
        //r2 = SQLFetcher.nonSelectQuery("INSERT INTO location(location_id,name) VALUES ('" + collectedData.get(3) + "','" + collectedData.get(4) + "')");
        r3 = SQLFetcher.nonSelectQuery("INSERT INTO takes_place_at(location_id,event_id) VALUES ('" + collectedData.get(3) + "','" + collectedData.get(0) + "')");
    
        return r1 && r3;//r2 && r3;
    }

    private static boolean insertManageLunch(){
        boolean r1, r2, r3;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO event(event_id, start_time, end_time, type) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(1) + "','" + collectedData.get(2) +  "','lunch')");
        //r2 = SQLFetcher.nonSelectQuery("INSERT INTO location(location_id,name) VALUES ('" + collectedData.get(3) + "','" + collectedData.get(4) + "')");
        r3 = SQLFetcher.nonSelectQuery("INSERT INTO takes_place_at(location_id,event_id) VALUES ('" + collectedData.get(3) + "','" + collectedData.get(0) + "')");
        return r1 && r3; //&& r3;
    }

    private static boolean insertManageDinner(){
        boolean r1, r2, r3;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO event(event_id, start_time, end_time, type) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(1) + "','" + collectedData.get(2) + "','dinner')");
        //r2 = SQLFetcher.nonSelectQuery("INSERT INTO location(location_id,name) VALUES ('" + collectedData.get(3) + "','" + collectedData.get(4) + "')");
        r3 = SQLFetcher.nonSelectQuery("INSERT INTO takes_place_at(location_id,event_id) VALUES ('" + collectedData.get(3) + "','" + collectedData.get(0) + "')");
        return r1 && r3;
    }

    private static boolean insertManagePeople(){
        boolean r1;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO participant(stud_id, name, surname, gender, university, rent, address, size, foodallergies) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(1) + "','" + collectedData.get(2) + "','" + collectedData.get(3) +  "','" + collectedData.get(4) + "','" + collectedData.get(5) + "','" + collectedData.get(6) + "','" + collectedData.get(7) + "','" + collectedData.get(8) + "')");
        return r1;
    }

    private static boolean insertSponsorshipStaff(){
        boolean r1, r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO sponsor(vat_no, company) VALUES ('" + collectedData.get(4) + "','" + collectedData.get(3) +"')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO finds(stud_id, vat_no) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(4) + "')");
        return r1 && r2;
    }

    private static boolean insertInManageTeams(){
        boolean r1, r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO team(team_id, name) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(1) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO sport_event(description) VALUES ('" + collectedData.get(2) + "')");
        return r1 && r2;
    }
    private static boolean manageLocations(){
        boolean r1, r2, r3;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO event(event_id, name, type, theme, start_time, end_time) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(1) + "','" + collectedData.get(6) + "','" + collectedData.get(7) + "','" + collectedData.get(8) + "','" + collectedData.get(9) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO location(location_id, name, address, space_ref) VALUES ('" + collectedData.get(2) + "','" + collectedData.get(3) + "','" + collectedData.get(4) + "','" + collectedData.get(5) + "')");
        r3 = SQLFetcher.nonSelectQuery("INSERT INTO takes_place_at(location_id, event_id) VALUES ('" + collectedData.get(0) + "','" + collectedData.get(2) + "')");
        return r1 && r2 && r3;
    }

    private static boolean manageTasks(){
        boolean r1, r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO task(task_id, description, start_hour, end_hour) VALUES ('" + collectedData.get(0) + "' , '" + collectedData.get(1)+ "' , '" + collectedData.get(2) + "' , '" + collectedData.get(3) + "')");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO must_help_in(stud_id, task_id) VALUES ('"+ collectedData.get(4) + "' , '" + collectedData.get(0) + "')");
        return r1 && r2;
    }

    //Insert into scoreboard for teams

    private static boolean insertInTeamSportsScoreboard()
    {
        boolean r1, r2;
        r1 = SQLFetcher.nonSelectQuery("INSERT INTO clashes_in(placement) VALUES ('" + collectedData.get(0) + "'");
        r2 = SQLFetcher.nonSelectQuery("INSERT INTO team(name) VALUES ('" + collectedData.get(1) + "'");
        return r1 && r2;
    }

}
