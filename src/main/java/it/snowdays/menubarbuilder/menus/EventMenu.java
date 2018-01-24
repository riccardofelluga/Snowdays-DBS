package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * EventMenu
 */
public class EventMenu extends Menu{
    
    private String locQuery = "SELECT l.name, l.address, l.space_ref, e.type, e.theme, e.start_time, e.end_time FROM location l RIGHT JOIN takes_place_at t ON l.location_id = t.location_id, takes_place_at p RIGHT JOIN event e ON p.event_id = e.event_id";
    private String dJQuery = "SELECT per.dj_name, per.start_hour, per.end_hour, ev.name, l.address FROM (((performance per RIGHT JOIN performed_at pat ON per.performance_id = pat.performance_id) LEFT JOIN event ev ON pat.event_id = ev.event_id) LEFT JOIN takes_place_at tpa ON ev.event_id = tpa.event_id) LEFT JOIN location l ON tpa.location_id = l.location_id";
    
    public EventMenu(){
        super("Event");
        MenuItem manageEventLoc = new MenuItem("Manage locations");
        manageEventLoc.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(locQuery));
            ViewPane.getInstance().setNewTable();
        });
        MenuItem manageDJ = new MenuItem("Playing DJs");
        manageDJ.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(dJQuery));
            ViewPane.getInstance().setNewTable();
        });
        getItems().add(manageEventLoc);
        getItems().add(manageDJ);
    }
    
}