package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * ParticipantsMenu
 */
public class ParticipantsMenu extends Menu{

    private String partQuery = "";
    private String uniQuery = "";

    public ParticipantsMenu(){
        super("Participants");

        MenuItem managePart = new MenuItem("Manage people");
        managePart.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(partQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageUni = new MenuItem("Manage universities");
        manageUni.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(uniQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(managePart);
        getItems().add(manageUni);
    }
}