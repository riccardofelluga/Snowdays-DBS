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

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("playingDJs", "DELETE FROM performance WHERE performance_id = ");
        
        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("manageBreakfast", "DELETE FROM event WHERE event_id = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("manageLunch", "DELETE FROM event WHERE event_id = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("manageDinner", "DELETE FROM event WHERE event_id = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("managePeople", "DELETE FROM participant WHERE stud_id = ");
        
        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("manageStop", "DELETE FROM stop WHERE stop_id = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("insertStuffPayload", "DELETE FROM transport WHERE transport_plateno = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("insertTeams", "DELETE FROM team WHERE team_id = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("insertInManageHosts", "DELETE FROM host WHERE phone_no = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("insertInManageLocationsAccommodation", "DELETE FROM accommodation WHERE accommodation_id = ");

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("insertInManageLocationsEvent", "DELETE FROM event WHERE event_id = ");
    }

    public void deleteRemote(String key){
        String q = keyMap.get(DataHandler.getInstance().getTableName()); //get the query from the map 
        SQLFetcher.nonSelectQuery(q + "'" + key  + "'"); //concat with the key!
        //DONE -> deleted
        DataHandler.getInstance().reloadRemote();
        ViewPane.getInstance().updateView();//refresh
    }
    
}
