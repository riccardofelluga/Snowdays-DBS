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

        

    }

    public void updateRemote(String keyValue, String attribute,String value){
        String q = queryMap.get(new Pair<String, String>(DataHandler.getInstance().getTableName(), attribute)); //get the query from the map
        String k = keyMap.get(DataHandler.getInstance().getTableName());

        SQLFetcher.nonSelectQuery(q + " SET " + attribute + " = '" + value + "' WHERE " + k + " = '" + keyValue + "'");

    }
}