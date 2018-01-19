package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelperMenu extends Menu{
    private String query = "SELECT t.task_id, t.description, t.start_hour, t.end_hour, m.stud_id FROM task t LEFT JOIN must_help_in m ON t.task_id = m.task_id ORDER BY task_id";

    public HelperMenu(){
        super("Helper");
        MenuItem assigStaff = new MenuItem("Assign to staff");
        assigStaff.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(query));
            ViewPane.getInstance().setNewTable();
        });
        getItems().add(new MenuItem("Manage tasks"));
        getItems().add(assigStaff);
        getItems().add(new MenuItem("Manage personnel"));
    }


}