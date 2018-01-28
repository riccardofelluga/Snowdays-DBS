package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
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
            DataHandler.getInstance().loadRemote(teamsQuery, "");
            ViewPane.getInstance().updateView();
        });

        MenuItem manageScoreboard = new MenuItem("Manage teams");
        manageScoreboard.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(scoreboardQuery, "");
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageTeams);
        getItems().add(manageScoreboard);
    }
}