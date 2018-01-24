package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * CompetesInMenu
 */
public class CompetesInMenu extends Menu {

    private String teamsQuery = "";
    private String scoreboardQuery = "";

    public CompetesInMenu(){
        super("Competes In");

        MenuItem manageTeams = new MenuItem("Manage teams");
        manageTeams.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(teamsQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageScoreboard = new MenuItem("Manage teams");
        manageScoreboard.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(scoreboardQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageTeams);
        getItems().add(manageScoreboard);
    }
}