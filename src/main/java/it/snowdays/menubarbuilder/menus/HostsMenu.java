package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
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
            TableHandler.getInstance().setTableView(SQLFetcher.getData(hostsQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageHosts);
    }
}