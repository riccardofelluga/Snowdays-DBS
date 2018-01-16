package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * CompetesInMenu
 */
public class CompetesInMenu extends Menu {

    public CompetesInMenu(){
        super("Competes In");
        getItems().add(new MenuItem("Manage teams"));
        getItems().add(new MenuItem("Scoreboard"));
    }
}