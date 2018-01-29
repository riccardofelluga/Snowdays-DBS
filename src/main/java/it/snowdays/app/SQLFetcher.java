package it.snowdays.app;

import it.snowdays.app.panels.ViewPane;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * SQLFetcher
 */
public class SQLFetcher {

    private static final String databaseURL = "jdbc:postgresql://93.46.114.82:1995/postgres";
    //jdbc:postgresql://localhost:5432/postgres
    //private static Connection connection;
    private static String username = "3lgrindero";
    //"postgres";
    private static String password = "Montebello16";
            //"postgresql";
    

    public static ArrayList<ArrayList<String>> getData(String query){
        ArrayList<ArrayList<String>> retrivedData = new ArrayList<ArrayList<String>>();

        Statement statement; 
        ResultSet set;
        boolean isHeaderParsed = false;
    
     try {
        statement = DriverManager.getConnection(databaseURL, username, password).createStatement();
        set = statement.executeQuery(query);
        ResultSetMetaData rsmd = set.getMetaData();
        int colCount = rsmd.getColumnCount();
        do{

            ArrayList<String> item  = new ArrayList<String>();
            
            if(!isHeaderParsed){
                for(int i = 1; i <= colCount; i++ ){
                    item.add(rsmd.getColumnName(i));
                }
                isHeaderParsed = true;
            } else{
                for(int i = 1; i <= colCount; i++){
                    item.add(set.getString(i));
                }
            }
            retrivedData.add(item);    
        }while (set.next());

		} catch (SQLException e) {
			Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("SQL error");
            a.setHeaderText(e.getSQLState());
            a.setContentText(e.getMessage());
            a.showAndWait();
        }
        return retrivedData;
    }

    public static void nonSelectQuery(String query){
        Statement statement; 
        ResultSet set;
        try {
            statement = DriverManager.getConnection(databaseURL, username, password).createStatement();
            set = statement.executeQuery(query);
            System.out.println("query fired!");
        } catch(SQLException e) {
            if(!e.getSQLState().equals("02000")){
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("SQL error");
            a.setHeaderText(e.getSQLState());
            a.setContentText(e.getMessage());
            a.showAndWait();

            DataHandler.getInstance().reloadRemote();
            ViewPane.getInstance().updateView();//refresh
            }
        }

    }
}