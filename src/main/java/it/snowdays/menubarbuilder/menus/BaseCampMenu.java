package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * BaseCampMenu
 */
public class BaseCampMenu extends Menu{

    public BaseCampMenu(){
        super("Base Camp Things");
        getItems().add(new MenuItem("Sport stuff"));
        getItems().add(new MenuItem("Catering stuff"));
        getItems().add(new MenuItem("Logistic stuff"));
    }
}