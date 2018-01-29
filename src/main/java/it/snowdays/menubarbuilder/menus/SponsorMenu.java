package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * SponsorMenu
 */
public class SponsorMenu extends Menu {

    private String firmsQuery = "s.company, s.cash, e.name, count(e.vat_no) AS quantity FROM (sponsor s LEFT JOIN edible e ON s.vat_no = e.vat_no) GROUP BY s.company, s.cash, e.name";
    private String sponsorshipQuery = "p.stud_id, p.name, p.surname, s.company FROM (participant p RIGHT JOIN finds f ON p.stud_id = f.stud_id) LEFT JOIN sponsor s ON f.vat_no = s.vat_no";

    public SponsorMenu(){
        super("Sponsor");

        MenuItem manageFirms = new MenuItem("Manage firms");
        manageFirms.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(firmsQuery, "manageFirms");
            ViewPane.getInstance().updateView();
        });

        MenuItem manageSponsorshipStuff = new MenuItem("Sponsorships stuff");
        manageSponsorshipStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(sponsorshipQuery, "sponsorshipStuff");
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageFirms);
        getItems().add(manageSponsorshipStuff);
    }
}