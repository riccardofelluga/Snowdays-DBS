package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * EdibleMenu
 */
public class EdibleMenu extends Menu{

    private String breakfastQuery = "SELECT l.name, e.start_time, e.end_time FROM (event e LEFT JOIN takes_place_at tpa ON e.event_id = tpa.event_id) LEFT JOIN location l ON tpa.location_id = l.location_id WHERE e.type = \'breakfast\' ";
    private String lunchQuery = "SELECT l.name, e.start_time, e.end_time FROM (event e LEFT JOIN takes_place_at tpa ON e.event_id = tpa.event_id) LEFT JOIN location l ON tpa.location_id = l.location_id WHERE e.type = \'lunch\'";
    private String dinnerQuery = "SELECT l.name, e.start_time, e.end_time FROM (event e LEFT JOIN takes_place_at tpa ON e.event_id = tpa.event_id) LEFT JOIN location l ON tpa.location_id = l.location_id WHERE e.type = \'dinner\'";
    

    public EdibleMenu(){
        super("Edible");

        MenuItem manageBreakfast = new MenuItem("Manage breakfasts");
        manageBreakfast.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(breakfastQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageLunch = new MenuItem("Manage lunch");
        manageBreakfast.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(lunchQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageDinner = new MenuItem("Manage dinner");
        manageBreakfast.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(dinnerQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageBreakfast);
        getItems().add(manageLunch);
        getItems().add(manageDinner);
    }
}