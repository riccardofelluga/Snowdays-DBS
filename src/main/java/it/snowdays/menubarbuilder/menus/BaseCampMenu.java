package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * BaseCampMenu
 */
public class BaseCampMenu extends Menu{

    private String sportStuffQuery = "";
    private String cateringStuffQuery = "";
    private String logisticStuffQuery = "";
    
    public BaseCampMenu(){
        super("Base Camp Things");

        MenuItem manageSportStuff = new MenuItem("Sport stuff");
        manageSportStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(sportStuffQuery);
            ViewPane.getInstance().updateView();
        });

        MenuItem manageCateringStuff = new MenuItem("Catering stuff");
        manageCateringStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(cateringStuffQuery);
            ViewPane.getInstance().updateView();
        });

        MenuItem manageLogisticStuff = new MenuItem("Logistic stuff");
        manageLogisticStuff.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(logisticStuffQuery);
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageSportStuff);
        getItems().add(manageCateringStuff);
        getItems().add(manageLogisticStuff);
    }
}