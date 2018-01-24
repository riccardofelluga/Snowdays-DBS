package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.SQLFetcher;
import it.snowdays.app.TableHandler;
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
            TableHandler.getInstance().setTableView(SQLFetcher.getData(sportStuffQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageCateringStuff = new MenuItem("Catering stuff");
        manageCateringStuff.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(cateringStuffQuery));
            ViewPane.getInstance().setNewTable();
        });

        MenuItem manageLogisticStuff = new MenuItem("Logistic stuff");
        manageLogisticStuff.setOnAction(e -> {
            TableHandler.getInstance().setTableView(SQLFetcher.getData(logisticStuffQuery));
            ViewPane.getInstance().setNewTable();
        });

        getItems().add(manageSportStuff);
        getItems().add(manageCateringStuff);
        getItems().add(manageLogisticStuff);
    }
}