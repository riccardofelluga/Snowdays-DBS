package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelperMenu extends Menu{
    
    public HelperMenu(){
        super("Helper");

        getItems().add(new MenuItem("Manage tasks"));
        getItems().add(new MenuItem("Assign to staff"));
        getItems().add(new MenuItem("Manage personnel"));
    }

}