package it.snowdays.app;

import java.util.ArrayList;

/**
 * DataHandler
 */
public class DataHandler {

    private ArrayList<ArrayList<String>> data = null;
    private ArrayList<ArrayList<String>> tmpData = null;
    private String tableName = ""; 
    private String query = "";
    
    private DataHandler(){}

    private static DataHandler instance = new DataHandler();

    public static DataHandler getInstance(){
        return instance;
    }
    
    public ArrayList<ArrayList<String>> loadRemote(String query, String tableName){
        data = SQLFetcher.getData(query);
        this.query = query;
        this.tableName = tableName;
        tmpData = data;
        return data;
    }

    public ArrayList<ArrayList<String>> reloadRemote(){
        return this.loadRemote(this.query, this.tableName);
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

    public void resetLocal(){
        tmpData = data;
    }

    public ArrayList<String> getHeader(){
        return (tmpData.size()!=0)?tmpData.get(0):null;
    }

    public String getTableName(){
        return this.tableName;
    }

    public void setTableName(String name){
        this.tableName = name;
    }
}