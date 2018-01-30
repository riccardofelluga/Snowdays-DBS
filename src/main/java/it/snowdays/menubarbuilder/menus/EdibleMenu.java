package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import java.util.ArrayList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * EdibleMenu
 */
public class EdibleMenu extends Menu{

    private String breakfastQuery = "SELECT e.event_id, e.start_time, e.end_time, l.location_id, l.name FROM (event e LEFT JOIN takes_place_at tpa ON e.event_id = tpa.event_id) LEFT JOIN location l ON tpa.location_id = l.location_id WHERE e.type = 'breakfast'";
    private String lunchQuery = "SELECT e.event_id, e.start_time, e.end_time, l.location_id, l.name  FROM (event e LEFT JOIN takes_place_at tpa ON e.event_id = tpa.event_id) LEFT JOIN location l ON tpa.location_id = l.location_id WHERE e.type = 'lunch'";
    private String dinnerQuery = "SELECT e.event_id, e.start_time, e.end_time, l.location_id, l.name FROM (event e LEFT JOIN takes_place_at tpa ON e.event_id = tpa.event_id) LEFT JOIN location l ON tpa.location_id = l.location_id WHERE e.type = 'dinner'";
    

    public EdibleMenu(){
        super("Edible");

        MenuItem manageBreakfast = new MenuItem("Manage breakfasts");
        manageBreakfast.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(breakfastQuery, "manageBreakfast");
            ArrayList<String> a = new ArrayList<String>();
            a.add("name");
            DataHandler.getInstance().setReadCols(a);
            ViewPane.getInstance().updateView();
        });

        MenuItem manageLunch = new MenuItem("Manage lunch");
        manageLunch.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(lunchQuery, "manageLunch");
            ArrayList<String> a = new ArrayList<String>();
            a.add("name");
            DataHandler.getInstance().setReadCols(a);
            ViewPane.getInstance().updateView();
        });

        MenuItem manageDinner = new MenuItem("Manage dinner");
        manageDinner.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(dinnerQuery, "manageDinner");
            ArrayList<String> a = new ArrayList<String>();
            a.add("name");
            DataHandler.getInstance().setReadCols(a);
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageBreakfast);
        getItems().add(manageLunch);
        getItems().add(manageDinner);
    }
}