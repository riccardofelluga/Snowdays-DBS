package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * SponsorMenu
 */
public class SponsorMenu extends Menu {

    private String firmsQuery = "";
    private String sponsorshipQuery = "";

    public SponsorMenu(){
        super("Sponsor");

        MenuItem manageFirms = new MenuItem("Manage firms");
        manageFirms.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(firmsQuery, "");
            ViewPane.getInstance().updateView();
        });

        MenuItem manageSponsorshipStuff = new MenuItem("Sponsorships stuff");
        manageSponsorshipStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(sponsorshipQuery, "");
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageFirms);
        getItems().add(manageSponsorshipStuff);
    }
}