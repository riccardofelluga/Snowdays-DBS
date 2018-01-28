package it.snowdays.app;

import java.util.HashMap;

import it.snowdays.app.panels.ViewPane;

/**
 * DeleteHandler
 */
public class DeleteHandler {

    public HashMap<String, String> keyMap;

    public DeleteHandler(){
        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("participant", "DELETE FROM staff WHERE stud_id = ");//put the query in the map
    }

    public void deleteRemote(String key){
        String q = keyMap.get(DataHandler.getInstance().getTableName()); //get the query from the map 
        SQLFetcher.nonSelectQuery(q + "'" + key  + "'"); //concat with the key!
        //DONE -> deleted
        DataHandler.getInstance().reloadRemote();
        ViewPane.getInstance().updateView();//refresh
    }
    
}