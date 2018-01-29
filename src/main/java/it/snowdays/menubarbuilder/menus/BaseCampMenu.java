package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * BaseCampMenu
 */
public class BaseCampMenu extends Menu{

    private String sportStuffQuery = "SELECT bct.inventory_id, bct.description, bct.vat_no, count(description) AS quantity FROM (base_camp_thing bct LEFT JOIN chooses c ON bct.inventory_id = c.inventory_id) LEFT JOIN staff s ON s.stud_id = c.stud_id WHERE s.role = 'Sport' GROUP BY bct.description, bct.inventory_id, bct.vat_no;";
    private String cateringStuffQuery = "SELECT bct.inventory_id, bct.description, count(description) AS quantity FROM (base_camp_thing bct LEFT JOIN chooses c ON bct.inventory_id = c.inventory_id) LEFT JOIN staff s ON s.stud_id = c.stud_id WHERE s.role = 'Catering' GROUP BY bct.description, bct.inventory_id";
    private String logisticStuffQuery = "SELECT bct.inventory_id, bct.description, count(description) AS quantity FROM (base_camp_thing bct LEFT JOIN chooses c ON bct.inventory_id = c.inventory_id) LEFT JOIN staff s ON s.stud_id = c.stud_id WHERE s.role = 'Logistic' GROUP BY bct.description, bct.inventory_id";
    
    public BaseCampMenu(){
        super("Base Camp Things");

        MenuItem manageSportStuff = new MenuItem("Sport stuff");
        manageSportStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(sportStuffQuery, "sportStuff");
            ViewPane.getInstance().updateView();
        });

        MenuItem manageCateringStuff = new MenuItem("Catering stuff");
        manageCateringStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(cateringStuffQuery, "cateringStuff");
            ViewPane.getInstance().updateView();
        });

        MenuItem manageLogisticStuff = new MenuItem("Logistic stuff");
        manageLogisticStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(logisticStuffQuery, "logisticStuff");
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageSportStuff);
        getItems().add(manageCateringStuff);
        getItems().add(manageLogisticStuff);
    }
}