package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * AccomodationMenu
 */
public class AccomodationsMenu extends Menu{

    public AccomodationsMenu(){
        super("Accomodations");
        getItems().add(new MenuItem("Manage locations"));
        getItems().add(new MenuItem("Assign participants"));

    }
    
}