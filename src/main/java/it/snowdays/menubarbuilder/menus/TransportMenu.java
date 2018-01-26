package it.snowdays.menubarbuilder.menus;

import it.snowdays.app.DataHandler;
import it.snowdays.app.panels.ViewPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * TransportMenu
 */
public class TransportMenu extends Menu{

    private String stopsQuery = "SELECT s.name, s.departure_time, s.arrival_time, sa.transport_plateno FROM stop s LEFT JOIN stops_at sa ON s.stop_id = sa.stop_id";
    // private String busPeopleQuery = "";
    private String payloadQuery = "";

    public TransportMenu(){
        super("Transport");
        
        MenuItem manageStops = new MenuItem("Manage stops");
        manageStops.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(stopsQuery);
            ViewPane.getInstance().updateView();
        });

        // MenuItem manageBusPeople = new MenuItem("Manage people buses");
        // manageBusPeople.setOnAction(e -> {
        //     TableHandler.getInstance().setTableView(SQLFetcher.getData(busPeopleQuery));
        //     ViewPane.getInstance().setNewTable();
        // });

        MenuItem manageStuffPayloads = new MenuItem("Manage stuff payloads");
        manageStuffPayloads.setOnAction(e -> {
            DataHandler.getInstance().loadRemote(payloadQuery);
            ViewPane.getInstance().updateView();
        });

        getItems().add(manageStops);
        // getItems().add(manageBusPeople);
        getItems().add(manageStuffPayloads);
        
    }
}