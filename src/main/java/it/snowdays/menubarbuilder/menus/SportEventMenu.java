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

    private String locQuery = "";
    private String staffQuery = "";
    
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
            String ID = getSBID("");//query here to fetch description
            DataHandler.getInstance().loadRemote("SELECT ci.placement, p.name, p.surname, p.university FROM competes_in ci LEFT JOIN participant p ON ci.stud_id = p.stud_id WHERE sport_event_id = "+ ID + " ORDER BY placement ASC");
            ViewPane.getInstance().updateView();
        });

        
        getItems().add(scoreboard);
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

        return SQLFetcher.getData("SELECT sport_event_id FROM sport_event WHERE description = '" + sbName + "'").get(0).get(0);
    }
}