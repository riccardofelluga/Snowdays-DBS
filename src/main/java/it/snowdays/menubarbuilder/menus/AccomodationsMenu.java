package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * AccomodationMenu
 */
public class AccomodationsMenu extends Menu{

    private String locQuery = "SELECT a.location_id, a.name, a.capacity, a.ref_phone_number, a.accommodation_id FROM accommodation a LEFT JOIN location l ON a.location_id = l.location_id ORDER BY accommodation_id";

    public AccomodationsMenu(){
        super("Accomodations");

        MenuItem manageAccLoc= new MenuItem("Manage locations");
        manageAccLoc.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(locQuery, "accommodationLocation");
            ViewPane.getInstance().updateView();
        });

        // MenuItem assignParticipant= new MenuItem("Assign participants");
        // assignParticipant.setOnAction(e -> {
        //     DataHandler.getInstance().loadRemote(assignQuery);
        //     ViewPane.getInstance().updateView();
        // });

        getItems().add(manageAccLoc);
        // getItems().add(assignParticipant);

    }
    
}