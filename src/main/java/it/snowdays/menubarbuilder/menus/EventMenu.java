package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

/**
 * EventMenu
 */
public class EventMenu extends Menu{
    
    private String dJQuery = "SELECT per.performance_id, per.dj_name, per.start_hour, per.end_hour, ev.event_id, ev.name FROM (performance per RIGHT JOIN performed_at pat ON per.performance_id = pat.performance_id) LEFT JOIN event ev ON pat.event_id = ev.event_id";
    
    public EventMenu(){
        super("Event");
        
        MenuItem manageDJ = new MenuItem("Playing DJs");
        manageDJ.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(dJQuery, "playingDJs");
            ArrayList<String> b = new ArrayList<String>();
            b.add("name");
            DataHandler.getInstance().setReadCols(b);
            ViewPane.getInstance().updateView();
        });
        getItems().add(manageDJ);
    }
    
}