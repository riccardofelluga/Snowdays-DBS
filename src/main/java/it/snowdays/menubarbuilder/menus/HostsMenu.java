package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * HostsMenu
 */
public class HostsMenu extends Menu{

    public HostsMenu(){
        super("Hosts");
        getItems().add(new MenuItem("Manage hosts"));
    }
}