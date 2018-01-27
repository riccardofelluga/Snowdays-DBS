package it.snowdays.menubarbuilder.menus;

import java.util.ArrayList;
import java.util.Optional;
import it.snowdays.app.DataHandler;
import it.snowdays.app.SQLFetcher;
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

        MenuItem scoreboardIndividual = new MenuItem("Individual Sports Scoreboard");
        scoreboardIndividual.setOnAction(e -> {
            String ID = getSBID("SELECT DISTINCT se.description FROM sport_event se RIGHT JOIN competes_in ci ON ci.sport_event_id = se.sport_event_id");//query here to fetch description
            DataHandler.getInstance().loadRemote("SELECT ci.placement, p.name, p.surname, p.university FROM competes_in ci LEFT JOIN participant p ON ci.stud_id = p.stud_id WHERE sport_event_id = " + ID + " ORDER BY placement ASC");
            ViewPane.getInstance().updateView();
        });

        MenuItem scoreboardTeam = new MenuItem("Team Sports Scoreboard");
        scoreboardTeam.setOnAction(e -> {
            String ID = getSBID("SELECT DISTINCT se.description FROM sport_event se RIGHT JOIN clashes_in ci ON ci.sport_event_id = se.sport_event_id");//query here to fetch description
            System.out.println(ID);
            DataHandler.getInstance().loadRemote("SELECT ci.placement, t.name FROM clashes_in ci LEFT JOIN team t ON ci.team_id = t.team_id WHERE sport_event_id = " + ID + " ORDER BY placement ASC");
            ViewPane.getInstance().updateView();
        });

        
        getItems().add(scoreboardIndividual);
        getItems().add(scoreboardTeam);
        getItems().add(manageSportLocations);
        getItems().add(manageSportStaff);
    }

    private String getSBID(String query){

        String sbName;

        ChoiceDialog<String> d = new ChoiceDialog<String>();
        d.setTitle("Choose scoreboard...");
        d.setHeaderText("Choose the scoreboard you want to see");
        d.setContentText("Scoreboard of: ");
        
        boolean first = true, second = true;
        for (ArrayList<String> t : SQLFetcher.getData(query)) {
            if(first){
                first = false;//do not include header
            }else if (second){
                second = false;
                d.getItems().add(t.get(0));
                d.setSelectedItem(t.get(0));//set the first element as selected def value
            }
            else{
                d.getItems().add(t.get(0));
            }
        }

        Optional<String> result = d.showAndWait();

        if(result.isPresent()){
            sbName = result.get();
        }else{
            sbName = d.getItems().get(0); // returns the id of the default table
        }

        return SQLFetcher.getData("SELECT sport_event_id FROM sport_event WHERE description = '" + sbName + "'").get(1).get(0);
    }
}