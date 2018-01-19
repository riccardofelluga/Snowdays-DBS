package it.snowdays.app.panels;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

/**
 * AppPane
 */
public class AppPane extends BorderPane{

    public AppPane(MenuBar mb){
        setTop(mb);
        setCenter(ViewPane.getInstance());
    }
    
}
