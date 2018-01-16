package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * EventMenu
 */
public class EventMenu extends Menu{

    public EventMenu(){
        super("Event");
        getItems().add(new MenuItem("Manage locations"));
        getItems().add(new MenuItem("Playing DJs"));
    }
    
}