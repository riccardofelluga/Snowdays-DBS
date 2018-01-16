package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * SportEventMenu
 */
public class SportEventMenu extends Menu{

    public SportEventMenu(){
        super("Sport Events");
        getItems().add(new MenuItem("Manage locations"));
        getItems().add(new MenuItem("Manage staff"));
    }
}