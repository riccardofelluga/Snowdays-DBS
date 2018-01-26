package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * HostsMenu
 */
public class HostsMenu extends Menu{

    private String hostsQuery = "";

    public HostsMenu(){
        super("Hosts");

        MenuItem manageHosts= new MenuItem("Manage hosts");
        manageHosts.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(hostsQuery);
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageHosts);
    }
}