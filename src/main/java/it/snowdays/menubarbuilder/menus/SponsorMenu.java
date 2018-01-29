package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * SponsorMenu
 */
public class SponsorMenu extends Menu {

    private String firmsQuery = "SELECT s.company, s.cash, s.vat_no, e.name, count(e.vat_no) AS quantity FROM (sponsor s LEFT JOIN edible e ON s.vat_no = e.vat_no) GROUP BY s.company, s.cash, e.name, s.vat_no";
    private String sponsorshipQuery = "SELECT p.stud_id, p.name, p.surname, s.company, s.vat_no FROM (participant p RIGHT JOIN finds f ON p.stud_id = f.stud_id) LEFT JOIN sponsor s ON f.vat_no = s.vat_no";

    /* 
        UPDATE finds
        SET stud_id = 33
        WHERE stud_id = '37' AND vat_no = '04041290968' 
    */

    public SponsorMenu(){
        super("Sponsor");

        MenuItem manageFirms = new MenuItem("See firms");
        manageFirms.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(firmsQuery, "manageFirms", true);
            ViewPane.getInstance().updateView();
        });

        MenuItem manageSponsorshipStaff = new MenuItem("Sponsorships staff");
        manageSponsorshipStaff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(sponsorshipQuery, "sponsorshipStaff");
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageFirms);
        getItems().add(manageSponsorshipStaff);
    }
}