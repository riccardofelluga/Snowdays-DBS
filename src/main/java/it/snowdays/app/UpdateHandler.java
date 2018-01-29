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
        
    
    }

    public void updateRemote(String keyValue, String attribute,String value){
        String q = queryMap.get(new Pair<String, String>(DataHandler.getInstance().getTableName(), attribute)); //get the query from the map
        String k = keyMap.get(DataHandler.getInstance().getTableName());

        SQLFetcher.nonSelectQuery(q + " SET " + attribute + " = '" + value + "' WHERE " + k + " = '" + keyValue + "'");

    }
}