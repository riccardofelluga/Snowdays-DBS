package it.snowdays.app;

import java.io.StringReader;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * TableHandler
 */
public class TableHandler {

    static ArrayList<ArrayList<String>> sampleData = new ArrayList<ArrayList<String>>();

    
    public TableHandler() {
        ArrayList<String> testHeader = new ArrayList<String>();
        testHeader.add("Nome");
        testHeader.add("Surname");
        testHeader.add("Stadt");

        ArrayList<String> testRow = new ArrayList<String>();
        testRow.add("a");
        testRow.add("b");
        testRow.add("c");

        sampleData.add(testHeader);
        sampleData.add(testRow);
    }

    public static TableView getCurrentTable() {

        ArrayList<String> testHeader = new ArrayList<String>();
        testHeader.add("Nome");
        testHeader.add("Surname");
        testHeader.add("Stadt");

        ArrayList<String> testRow = new ArrayList<String>();
        testRow.add("a");
        testRow.add("b");
        testRow.add("c");

        sampleData.add(testHeader);
        sampleData.add(testRow);

        TableView<ArrayList<String>> table = new TableView<>();

        ObservableList<ArrayList<String>> inTableData = FXCollections.observableArrayList();
        inTableData.addAll(sampleData);
        inTableData.remove(0);
        for(int i = 0; i<sampleData.get(0).size(); i++){
            TableColumn<ArrayList<String>, String> tc = new TableColumn<ArrayList<String>, String>(sampleData.get(0).get(i));
            final int c = i;
            Callback cb = new Callback<CellDataFeatures<ArrayList<String>, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(CellDataFeatures<ArrayList<String>, String> param) {
					return new SimpleStringProperty(param.getValue().get(c));
				}

            };

            tc.setCellValueFactory(cb);

            //custom things here!

            table.getColumns().add(tc);
        }

        table.setItems(inTableData);
        return table;
    }

    // public static setTableView(){

    // }

}