package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * CompetesInMenu
 */
public class CompetesInMenu extends Menu {

    private String teamsQuery = "SELECT t.team_id, t.name, se.description FROM (team t LEFT JOIN clashes_in ci ON t.team_id = ci.team_id) LEFT JOIN sport_event se ON ci.sport_event_id = se.sport_event_id";
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