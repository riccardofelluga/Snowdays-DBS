package it.snowdays.menubarbuilder.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * SportEventMenu
 */
public class SportEventMenu extends Menu{

    private String locQuery = "SELECT se.description, l.address, l.name " + 
                                " FROM (sport_event se LEFT JOIN is_played_at ipa ON se.sport_event_id = ipa.sport_event_id)" +
                                " LEFT JOIN location l ON ipa.location_id = l.location_id";
    
    private String staffQuery = "SELECT p.name, p.surname, se.description AS sport_event, l.address AS location " +
                                " FROM (((staff s LEFT JOIN participant p ON s.stud_id = p.stud_id) " +
                                " RIGHT JOIN put_together pt ON pt.stud_id = s.stud_id " +
                                " LEFT JOIN sport_event se ON pt.sport_event_id = se.sport_event_id) " +
                                " LEFT JOIN is_played_at ipa ON se.sport_event_id = ipa.sport_event_id) " +
                                " LEFT JOIN location l ON ipa.location_id = l.location_id";
    
    private int sportEventId;
    private String competition;
    private String retrieveId = "SELECT se.sport_event_id FROM sport_event se WHERE se.description = \'" + competition + "\'";

    private String scoreboardSingle = "SELECT ci.placement, p.name, p.surname, p.university " +
                                        " FROM competes_in ci LEFT JOIN participant p ON ci.stud_id = p.stud_id " +
                                        " WHERE sport_event_id = " + sportEventId + " ORDER BY placement ASC";
    
    public SportEventMenu(){
        super("Sport Events");

        MenuItem manageSportLocations = new MenuItem("Manage locations");
        manageSportLocations.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(locQuery);
            ViewPane.getInstance().updateView();
        });

        MenuItem manageSportStaff = new MenuItem("Manage staff");
        manageSportStaff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(staffQuery);
            ViewPane.getInstance().updateView();
        });

        MenuItem scoreboard = new MenuItem("Scoreboard");
        scoreboard.setOnAction(e -> {
            sportEventId = getSBID();
            DataHandler.getInstance().loadRemote(scoreboardSingle);
            ViewPane.getInstance().updateView();//
        });

        getItems().add(scoreboard);
        getItems().add(manageSportLocations);
        getItems().add(manageSportStaff);
    }

    private int getSBID(){

        ArrayList<String> events  = new ArrayList<String>();
        events.add("Ski Race");
        events.add("Beer Pong");
        events.add("Tiro cinnio");
        events.add("Giro al Sas");

        ChoiceDialog<String> d = new ChoiceDialog<String>();
        d.setTitle("Choose scoreboard...");
        d.setHeaderText("Choose the scoreboard you want to see");
        d.setContentText("Scoreboard: ");
        d.getItems().addAll(events);
        //d.setSelectedItem(""); //set the default selection!

        Optional<String> result = d.showAndWait();
        if (result.isPresent()){
            competition = result.get();
        }
        sportEventId = loadRemote(retrieveId);
        return sportEventId; // returns the id of the table
    }
}