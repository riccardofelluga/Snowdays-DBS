package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * EdibleMenu
 */
public class EdibleMenu extends Menu{

    public EdibleMenu(){
        super("Edible");
        getItems().add(new MenuItem("Manage Frühstuck"));
        getItems().add(new MenuItem("Manage Pranzo"));
        getItems().add(new MenuItem("Manage Dinner"));
    }
}