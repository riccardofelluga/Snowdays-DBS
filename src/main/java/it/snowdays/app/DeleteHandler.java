package it.snowdays.app;

import it.snowdays.app.panels.ViewPane;

import java.util.HashMap;

/**
 * DeleteHandler
 */
public class DeleteHandler {

    public HashMap<String, String> keyMap;

    public DeleteHandler(){

        keyMap = new HashMap<String, String>();
        //populate map here!
        keyMap.put("participant", "DELETE FROM staff WHERE stud_id = ");//put the query in the map

        keyMap.put("playingDJs", "DELETE FROM performance WHERE performance_id = ");
                
        keyMap.put("manageBreakfast", "DELETE FROM event WHERE event_id = ");
        
        keyMap.put("manageLunch", "DELETE FROM event WHERE event_id = ");

        keyMap.put("manageDinner", "DELETE FROM event WHERE event_id = ");
        
        keyMap.put("managePeople", "DELETE FROM participant WHERE stud_id = ");
                
        keyMap.put("manageStops", "DELETE FROM stop WHERE stop_id = ");

        keyMap.put("insertStuffPayload", "DELETE FROM transport WHERE transport_plateno = ");
        
        keyMap.put("insertTeams", "DELETE FROM team WHERE team_id = ");
        
        keyMap.put("manageHosts", "DELETE FROM host WHERE accommodation_id = ");
        
        keyMap.put("insertInManageLocationsEvent", "DELETE FROM event WHERE event_id = ");
        
        keyMap.put("sponsorshipStaff", "DELETE FROM finds WHERE stud_id = ");
        
        keyMap.put("BSCTStuff", "DELETE FROM base_camp_thing WHERE inventory_id = ");
        
        keyMap.put("accommodationLocation", "DELETE FROM accomodation WHERE location_id = ");

    }

    public void deleteRemote(String key){
        String q = keyMap.get(DataHandler.getInstance().getTableName()); //get the query from the map 
        SQLFetcher.nonSelectQuery(q + "'" + key  + "'"); //concat with the key!
        //DONE -> deleted
        DataHandler.getInstance().reloadRemote();
        ViewPane.getInstance().updateView();//refresh
    }
    
}
