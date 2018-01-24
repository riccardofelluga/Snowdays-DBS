package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * SportEventMenu
 */
public class SportEventMenu extends Menu{

    private String locQuery = "";
    private String staffQuery = "";

    public SportEventMenu(){
        super("Sport Events");

        MenuItem manageSportLocations = new MenuItem("Manage locations");
        manageSportLocations.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(locQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageSportStaff = new MenuItem("Manage staff");
        manageSportStaff.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(staffQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageSportLocations);
        getItems().add(manageSportStaff);
    }
}