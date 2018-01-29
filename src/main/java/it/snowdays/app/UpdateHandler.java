package it.snowdays.app;

import java.util.HashMap;

import javafx.util.Pair;

/**
 * UpdateHandler
 */
public class UpdateHandler {

    private HashMap<Pair<String, String>, String> queryMap;
    private HashMap<String, String> keyMap;

    public UpdateHandler(){
        
        queryMap = new HashMap<Pair<String, String>, String>();
        keyMap = new HashMap<String, String>();
        //populate map here!
        queryMap.put(new Pair<String,String>("participant", "stud_id"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("participant", "name"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("participant", "surname"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("participant", "role"), "UPDATE staff");
        keyMap.put("participant", "stud_id");

        queryMap.put(new Pair<String,String>("playingDJs", "performance_id"), "UPDATE performance");
        queryMap.put(new Pair<String,String>("playingDJs", "dj_name"), "UPDATE performance");
        queryMap.put(new Pair<String,String>("playingDJs", "start_hour"), "UPDATE performance");
        queryMap.put(new Pair<String,String>("playingDJs", "end_hour"), "UPDATE performance");
        queryMap.put(new Pair<String,String>("playingDJs", "name"), "UPDATE event");
        queryMap.put(new Pair<String,String>("playingDJs", "address"), "UPDATE location");
        keyMap.put("playingDJs", "performance_id");

        queryMap.put(new Pair<String,String>("manageBreakfast", "event_id"), "UPDATE event");
        queryMap.put(new Pair<String,String>("manageBreakfast", "name"), "UPDATE location");
        queryMap.put(new Pair<String,String>("manageBreakfast", "start_time"), "UPDATE event");
        queryMap.put(new Pair<String,String>("manageBreakfast", "end_time"), "UPDATE event");
        keyMap.put("manageBreakfast", "event_id");

        queryMap.put(new Pair<String,String>("manageLunch", "event_id"), "UPDATE event");
        queryMap.put(new Pair<String,String>("manageLunch", "name"), "UPDATE location");
        queryMap.put(new Pair<String,String>("manageLunch", "start_time"), "UPDATE event");
        queryMap.put(new Pair<String,String>("manageLunch", "end_time"), "UPDATE event");
        keyMap.put("manageLunch", "event_id");

        queryMap.put(new Pair<String,String>("manageDinner", "event_id"), "UPDATE event");
        queryMap.put(new Pair<String,String>("manageDinner", "name"), "UPDATE location");
        queryMap.put(new Pair<String,String>("manageDinner", "start_time"), "UPDATE event");
        queryMap.put(new Pair<String,String>("manageDinner", "end_time"), "UPDATE event");
        keyMap.put("manageDinner", "event_id");

        queryMap.put(new Pair<String,String>("managePeople", "stud_id"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "name"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "surname"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "gender"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "university"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "rent"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "address"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "size"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("managePeople", "foodallergies"), "UPDATE participant");
        keyMap.put("managePeople", "stud_id");

        queryMap.put(new Pair<String,String>("manageStops", "stop_id"), "UPDATE stop");
        queryMap.put(new Pair<String,String>("manageStops", "name"), "UPDATE stop");
        queryMap.put(new Pair<String,String>("manageStops", "departure_time"), "UPDATE stop");
        queryMap.put(new Pair<String,String>("manageStops", "arrival_time"), "UPDATE stop");
        queryMap.put(new Pair<String,String>("manageStops", "transport_plateno"), "UPDATE transport");
        keyMap.put("manageStop", "stop_id");

        queryMap.put(new Pair<String,String>("insertStuffPayload", "transport_plateno"), "UPDATE transport");
        queryMap.put(new Pair<String,String>("insertStuffPayload", "description"), "UPDATE base_camp_thing");
        keyMap.put("insertStuffPayload", "transport_plateno");

        queryMap.put(new Pair<String,String>("insertTeams", "team_id"), "UPDATE team");
        queryMap.put(new Pair<String,String>("insertTeams", "name"), "UPDATE team");
        queryMap.put(new Pair<String,String>("insertTeams", "description"), "UPDATE sport_event");
        keyMap.put("insertTeams", "team_id");

        queryMap.put(new Pair<String,String>("insertInManageHosts", "phone_no"), "UPDATE host");
        queryMap.put(new Pair<String,String>("insertInManageHosts", "name"), "UPDATE host");
        queryMap.put(new Pair<String,String>("insertInManageHosts", "surname"), "UPDATE host");
        queryMap.put(new Pair<String,String>("insertInManageHosts", "name"), "UPDATE accomodation");
        keyMap.put("insertInManageHosts", "phone_no");

        queryMap.put(new Pair<String,String>("insertInManageLocationsAccommodation", "accommodation_id"), "UPDATE accommodation");
        queryMap.put(new Pair<String,String>("insertInManageLocationsAccommodation", "name"), "UPDATE accommodation");
        queryMap.put(new Pair<String,String>("insertInManageLocationsAccommodation", "capacity"), "UPDATE accommodation");
        queryMap.put(new Pair<String,String>("insertInManageLocationsAccommodation", "ref_phone_number"), "UPDATE accommodation");
        queryMap.put(new Pair<String,String>("insertInManageLocationsAccommodation", "location_id"), "UPDATE location");
        keyMap.put("insertInManageLocationsAccommodation", "accommodation_id");
        
        queryMap.put(new Pair<String,String>("sponsorshipStaff", "stud_id"), "UPDATE chooses");
      /*  queryMap.put(new Pair<String,String>("sponsorshipStaff", "name"), "UPDATE participant");
        queryMap.put(new Pair<String,String>("sponsorshipStaff", "surname"), "UPDATE participant");*/
        queryMap.put(new Pair<String,String>("sponsorshipStaff", "company"), "UPDATE chooses");
        keyMap.put("sponsorshipStaff", "stud_id");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "event_id"), "UPDATE event");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "name"), "UPDATE location");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "address"), "UPDATE location");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "space_ref"), "UPDATE location");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "type"), "UPDATE event");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "theme"), "UPDATE event");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "start_time"), "UPDATE event");
        queryMap.put(new Pair<String,String>("insertInManageLocationsEvent", "end_time"), "UPDATE event");
        keyMap.put("insertInManageLocationsEvent", "event_id");

        queryMap.put(new Pair<String,String>("sportStuff", "inventory_id"), "UPDATE base_camp_thing");
        queryMap.put(new Pair<String,String>("sportStuff", "description"), "UPDATE base_camp_thing");
        queryMap.put(new Pair<String,String>("sportStuff", "vat_no"), "UPDATE base_camp_thing");
        queryMap.put(new Pair<String,String>("sportStuff", "stud_id"), "UPDATE chooses");
        keyMap.put("sportStuff", "inventory_id");
    }

    public void updateRemote(String keyValue, String attribute,String value){
        String q = queryMap.get(new Pair<String, String>(DataHandler.getInstance().getTableName(), attribute)); //get the query from the map
        String k = keyMap.get(DataHandler.getInstance().getTableName());

        SQLFetcher.nonSelectQuery(q + " SET " + attribute + " = '" + value + "' WHERE " + k + " = '" + keyValue + "'");

    }
}
