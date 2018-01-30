package it.snowdays.menubarbuilder.menus;

import java.util.ArrayList;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * EventMenu
 */
public class EventMenu extends Menu{
    
    private String locQuery = "SELECT e.event_id, l.name, l.address, l.space_ref, e.type, e.theme, e.start_time, e.end_time FROM location l RIGHT JOIN takes_place_at t ON l.location_id = t.location_id, takes_place_at p RIGHT JOIN event e ON p.event_id = e.event_id";
    private String dJQuery = "SELECT per.performance_id, per.dj_name, per.start_hour, per.end_hour, ev.event_id, ev.name FROM (performance per RIGHT JOIN performed_at pat ON per.performance_id = pat.performance_id) LEFT JOIN event ev ON pat.event_id = ev.event_id";
    
    public EventMenu(){
        super("Event");
        MenuItem manageEventLoc = new MenuItem("Manage locations");
        manageEventLoc.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(locQuery, "manageLocations");
            ViewPane.getInstance().updateView();
        });
        MenuItem manageDJ = new MenuItem("Playing DJs");
        manageDJ.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(dJQuery, "playingDJs");
            ArrayList<String> a = new ArrayList<String>();
            a.add("name");
            DataHandler.getInstance().setReadCols(a);
            ViewPane.getInstance().updateView();
        });
        getItems().add(manageEventLoc);
        getItems().add(manageDJ);
    }
    
}