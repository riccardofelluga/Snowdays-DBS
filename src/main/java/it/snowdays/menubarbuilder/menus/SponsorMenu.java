package it.snowdays.menubarbuilder.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * SponsorMenu
 */
public class SponsorMenu extends Menu {

    public SponsorMenu(){
        super("Sponsor");
        getItems().add(new MenuItem("Manage firms"));
        getItems().add(new MenuItem("Sponsorships staff"));
    }
}