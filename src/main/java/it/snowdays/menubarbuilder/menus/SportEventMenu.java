package it.snowdays.menubarbuilder.menus;

import java.util.HashMap;

import it.snowdays.app.DataHandler;
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
            String ID = getSBID();
            DataHandler.getInstance().loadRemote("SELECT * FROM " + ID);
            ViewPane.getInstance().updateView();
        });

        getItems().add(scoreboard);
        getItems().add(manageSportLocations);
        getItems().add(manageSportStaff);
    }

    private String getSBID(){

        HashMap<String, String> sKeys  = new HashMap<String, String>();
        sKeys.put("", "");//insert here the mapping

        ChoiceDialog<String> d = new ChoiceDialog<String>();
        d.setTitle("Choose scoreboard...");
        d.setHeaderText("Choose the scoreboard you want to see");
        d.setContentText("Scoreboard: ");
        d.getItems().addAll(sKeys.values());
        //d.setSelectedItem(""); //set the default selection!

        d.showAndWait();

        return "staff"; // returns the id of the table
    }
}