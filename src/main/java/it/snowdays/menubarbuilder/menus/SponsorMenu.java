package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
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
            TableHandler.getInstance().setTableView(SQLFetcher.getData(firmsQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageSponsorshipStuff = new MenuItem("Sponsorships stuff");
        manageSponsorshipStuff.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(sponsorshipQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageFirms);
        getItems().add(manageSponsorshipStuff);
    }
}