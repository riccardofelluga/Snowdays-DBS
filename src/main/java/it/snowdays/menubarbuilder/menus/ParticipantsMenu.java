package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * ParticipantsMenu
 */
public class ParticipantsMenu extends Menu{

    private String partQuery = "SELECT * FROM participant";
    private String uniQuery = "SELECT p.university, count(p.university) as quantity FROM participant p GROUP BY p.university";

    public ParticipantsMenu(){
        super("Participants");

        MenuItem managePart = new MenuItem("Manage people");
        managePart.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(partQuery, "managePeople");
            ViewPane.getInstance().updateView();
        });

        MenuItem manageUni = new MenuItem("See universities stats");
        manageUni.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(uniQuery, "manageUniversities", true);
            ViewPane.getInstance().updateView();
        });

        getItems().add(managePart);
        getItems().add(manageUni);
    }
}