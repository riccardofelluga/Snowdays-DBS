package it.snowdays.app;

import javafx.application.Application;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.Scene;
import javafx.stage.*;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Optional;

import it.snowdays.menubarbuilder.MenuBarBuilder;
import it.snowdays.app.panels.*;

/**
 * Hello world!
 *
 */
public class App extends Application{
    
    protected ArrayList<String> fields = new ArrayList<String>();

    public static void main(String[] args){
        launch(args);//start point of javafx
    }
    
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Snowdays DBMS");
        populateArray();//populate the array with the options

        ChoiceDialog<String> dialog = new ChoiceDialog<String>("Mainco", fields);
        dialog.setTitle("Who are you?");
        dialog.setHeaderText("Choose your position in the staff:");

        Optional<String> result = dialog.showAndWait();

        if(result.isPresent()){
            System.out.println(result.get());
            String panelName = result.get();
            
            switch (panelName) {
                case "Mainco":
                    stage.setScene(new Scene(new AppPane(MenuBarBuilder.maincoMenu())));
                    break;
                case "Sport":
                    stage.setScene(new Scene(new AppPane(MenuBarBuilder.sportMenu())));
                    break;
                case "Logistic":
                    stage.setScene(new Scene(new AppPane(MenuBarBuilder.logisticMenu())));
                    break;
                case "C&A":
                    stage.setScene(new Scene(new AppPane(MenuBarBuilder.caMenu())));
                    break;
                case "Sponsor":
                    stage.setScene(new Scene(new AppPane(MenuBarBuilder.sponsorMenu())));
                    break;
            
                default:
                    break;
            }

         stage.show();   
        }
    }

    private void populateArray(){
        fields.add("Mainco");
        fields.add("Sport");
        fields.add("Logistic");
        fields.add("C&A");
        fields.add("Sponsor");
    }
}
