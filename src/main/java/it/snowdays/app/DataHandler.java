package it.snowdays.app;

import java.util.ArrayList;

/**
 * DataHandler
 */
public class DataHandler {

    private ArrayList<ArrayList<String>> data = null;
    private ArrayList<ArrayList<String>> tmpData = null;
    
    private DataHandler(){}

    private static DataHandler instance = new DataHandler();

    public static DataHandler getInstance(){
        return instance;
    }
    
    public ArrayList<ArrayList<String>> loadRemote(String query){
        data = SQLFetcher.getData(query);
        tmpData = data;
        return data;
    }

    public ArrayList<ArrayList<String>> getLocal(){
        return tmpData;
    }
    
    public void setLocal(ArrayList<ArrayList<String>> d){
        tmpData = d;
    }

    public ArrayList<ArrayList<String>> getFullDataset(){
        return data;
    }


}