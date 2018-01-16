package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * TransportMenu
 */
public class TransportMenu extends Menu{

    public TransportMenu(){
        super("Transport");
        getItems().add(new MenuItem("Manage stops"));
        getItems().add(new MenuItem("Manage people buses"));
        getItems().add(new MenuItem("Manage stuff payloads"));
        
    }
}