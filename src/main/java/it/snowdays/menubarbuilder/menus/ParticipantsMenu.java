package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * ParticipantsMenu
 */
public class ParticipantsMenu extends Menu{

    public ParticipantsMenu(){
        super("Participants");
        getItems().add(new MenuItem("Manage people"));
        getItems().add(new MenuItem("Manage universities"));
    }
}