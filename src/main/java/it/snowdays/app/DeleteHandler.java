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
        keyMap.put("playingDJs", "DELETE FROM performance WHERE performance_id = ");
        
        keyMap = new HashMap<String, String>();
        keyMap.put("manageBreakfast", "DELETE FROM event WHERE event_id = ");
        
        keyMap = new HashMap<String, String>();
        keyMap.put("manageLunch", "DELETE FROM event WHERE event_id = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("manageDinner", "DELETE FROM event WHERE event_id = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("managePeople", "DELETE FROM participant WHERE stud_id = ");
        
        keyMap = new HashMap<String, String>();
        keyMap.put("manageStop", "DELETE FROM stop WHERE stop_id = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("insertStuffPayload", "DELETE FROM transport WHERE transport_plateno = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("insertTeams", "DELETE FROM team WHERE team_id = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("manageHosts", "DELETE FROM host WHERE phone_no = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("insertInManageLocationsEvent", "DELETE FROM event WHERE event_id = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("sponsorshipStaff", "DELETE FROM finds WHERE stud_id = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("BSCTStuff", "DELETE FROM base_camp_thing WHERE inventory_id = ");

        keyMap = new HashMap<String, String>();
        keyMap.put("accommodationLocation", "DELETE FROM accomodation WHERE accomodation_id = ");

    }

    public void deleteRemote(String key){
        String q = keyMap.get(DataHandler.getInstance().getTableName()); //get the query from the map 
        SQLFetcher.nonSelectQuery(q + "'" + key  + "'"); //concat with the key!
        //DONE -> deleted
        DataHandler.getInstance().reloadRemote();
        ViewPane.getInstance().updateView();//refresh
    }
    
}
