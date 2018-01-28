package it.snowdays.app;

import java.util.HashMap;

/**
 * DeleteHandler
 */
public class DeleteHandler {

    public HashMap<String, String> preloaded;

    public DeleteHandler(){
        preloaded = new HashMap<String, String>();
        //populate map here!
        preloaded.put("ManageHelper", "DELETE FROM helper WHERE stud_id = ");//put the query in the map
    }

    public void deleteRemote(String key){
        String q = preloaded.get(DataHandler.getInstance().getTableName()); //get the query from the map 
        SQLFetcher.nonSelectQuery(q + "'" + key  + "'"); //concat with the key!
        //DONE -> deleted
    }
    
}