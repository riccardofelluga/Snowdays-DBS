package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * AccomodationMenu
 */
public class AccomodationsMenu extends Menu{

    private String locQuery = "";
    private String assignQuery = "";

    public AccomodationsMenu(){
        super("Accomodations");

        MenuItem manageAccLoc= new MenuItem("Manage locations");
        manageAccLoc.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(locQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem assignParticipant= new MenuItem("Assign participants");
        assignParticipant.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(assignQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageAccLoc);
        getItems().add(assignParticipant);

    }
    
}