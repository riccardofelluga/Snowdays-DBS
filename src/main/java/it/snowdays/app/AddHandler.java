package it.snowdays.app;

import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

/**
 * AddHandler
 */ 

public class AddHandler {
    
    private Dialog<String> d;

    public AddHandler(){
        d = new Dialog<>();
        GridPane grid = new GridPane();
    } 
    private GridPane setUI(){
        GridPane grid = new GridPane();
        return grid;
    }
}