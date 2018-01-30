package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelperMenu extends Menu{
    //private String assignQuery = "SELECT t.task_id, t.description, t.start_hour, t.end_hour, m.stud_id FROM task t LEFT JOIN must_help_in m ON t.task_id = m.task_id ORDER BY task_id";
    private String personsQuery = "SELECT  p.stud_id, p.surname, p.name, s.role FROM participant p RIGHT JOIN staff s ON p.stud_id = s.stud_id ORDER BY p.surname";
    private String tasksQuery = "SELECT t.task_id, t.description, t.start_hour, t.end_hour, p.stud_id FROM ((task t RIGHT JOIN must_help_in m ON t.task_id = m.task_id) LEFT JOIN helper h ON m.stud_id  = h.stud_id) LEFT JOIN participant p ON h.stud_id = p.stud_id";

    public HelperMenu(){
        super("Helper");
        // MenuItem assigStaff = new MenuItem("Assign to staff");
        // assigStaff.setOnAction(e -> {
        //     DataHandler.getInstance().loadRemote(assignQuery, "assignToStaff");
        //     ViewPane.getInstance().updateView();
        // });
        MenuItem manageTasks = new MenuItem("Assign to staff");
        manageTasks.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(tasksQuery, "manageTasks");
            ViewPane.getInstance().updateView();
        });

        MenuItem managePersons = new MenuItem("Manage personnel");
        managePersons.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(personsQuery, "participant");
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageTasks);
        //getItems().add(assigStaff);
        getItems().add(managePersons);
    }


}