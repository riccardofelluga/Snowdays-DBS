package it.snowdays.app;

import java.util.ArrayList;

/**
 * DataHandler
 */
public class DataHandler {

    static ArrayList<ArrayList<String>> data = null;
    
    private DataHandler(){}

    private static DataHandler instance = new DataHandler();

    public static DataHandler getInstance(){
        return instance;
    }
    
    public ArrayList<ArrayList<String>> loadRemote(String query){
        data = SQLFetcher.getData(query);
        return data;
    }

    public ArrayList<ArrayList<String>> getLocal(){
        return data;
    }

    


}