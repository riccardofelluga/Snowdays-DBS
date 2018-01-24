package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelperMenu extends Menu{
    private String assignQuery = "SELECT t.task_id, t.description, t.start_hour, t.end_hour, m.stud_id FROM task t LEFT JOIN must_help_in m ON t.task_id = m.task_id ORDER BY task_id";
    private String tasksQuery = "SELECT t.description, t.start_hour, t.end_hour, p.name, p.surname FROM ((task t LEFT JOIN must_help_in m ON t.task_id = m.task_id) LEFT JOIN helper h ON m.stud_id  = h.stud_id) LEFT JOIN participant p ON h.stud_id = p.stud_id";
    private String personsQuery = "SELECT  p.surname, p.name, p.stud_id, s.role FROM participant p RIGHT JOIN staff s ON p.stud_id = s.stud_id ORDER BY p.surname";

    public HelperMenu(){
        super("Helper");
        MenuItem assigStaff = new MenuItem("Assign to staff");
        assigStaff.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(assignQuery));
            ViewPane.getInstance().setNewTable();
        });
        MenuItem manageTasks = new MenuItem("Manage tasks");
        manageTasks.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(tasksQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem managePersons = new MenuItem("Manage personnel");
        managePersons.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(personsQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageTasks);
        getItems().add(assigStaff);
        getItems().add(managePersons);
    }


}