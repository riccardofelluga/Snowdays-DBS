package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * HostsMenu
 */
public class HostsMenu extends Menu{

    private String hostsQuery = "SELECT h.phone_no, a.name, h.name, h.surname  FROM host h LEFT JOIN accommodation a ON h.accommodation_id = a.accommodation_id";

    public HostsMenu(){
        super("Hosts");

        MenuItem manageHosts= new MenuItem("Manage hosts");
        manageHosts.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(hostsQuery, "manageHosts");
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageHosts);
    }
}