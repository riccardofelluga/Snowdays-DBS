package it.snowdays.menubarbuilder;

import javafx.scene.control.MenuBar;
import it.snowdays.menubarbuilder.menus.*;

/**
 * MenuBuilder
 */
public class MenuBarBuilder extends MenuBar{

    public static MenuBar maincoMenu(){
        MenuBar mb = new MenuBar();
        mb.getMenus().add(new HelperMenu());
        mb.getMenus().add(new EventMenu());
        mb.getMenus().add(new TransportMenu());
        mb.getMenus().add(new EdibleMenu());
        mb.getMenus().add(new BaseCampMenu());
        mb.getMenus().add(new SponsorMenu());
        mb.getMenus().add(new SportEventMenu());
        mb.getMenus().add(new ParticipantsMenu());
        mb.getMenus().add(new AccomodationsMenu());
        mb.getMenus().add(new HostsMenu());

        mb.setStyle("-fx-selection-bar: #000080;");
        mb.setStyle("-fx-background-color: #87cefa;");
//        mb.setStyle("-fx-border-color: #4682b4;");

        return mb;
    }
    public static MenuBar logisticMenu(){
        MenuBar mb = new MenuBar();

        mb.getMenus().add(new HelperMenu());
        mb.getMenus().add(new EventMenu());
        mb.getMenus().add(new TransportMenu());
        mb.getMenus().add(new EdibleMenu());
        mb.getMenus().add(new BaseCampMenu());

        mb.setStyle("-fx-selection-bar: #000080;");
        mb.setStyle("-fx-background-color: #87cefa;");

        return mb;
    }
    public static MenuBar sponsorMenu(){
        MenuBar mb = new MenuBar();
        
        mb.getMenus().add(new SponsorMenu());
        mb.getMenus().add(new EdibleMenu());

        mb.setStyle("-fx-selection-bar: #000080;");
        mb.setStyle("-fx-background-color: #87cefa;");

        return mb;
    }
    public static MenuBar caMenu(){
        MenuBar mb = new MenuBar();

        mb.getMenus().add(new ParticipantsMenu());
        mb.getMenus().add(new AccomodationsMenu());
        mb.getMenus().add(new HostsMenu());

        mb.setStyle("-fx-selection-bar: #000080;");
        mb.setStyle("-fx-background-color: #87cefa;");
       
        return mb;
    }
    public static MenuBar sportMenu(){
        MenuBar mb = new MenuBar();

        mb.getMenus().add(new SportEventMenu());
        mb.getMenus().add(new HelperMenu());

        mb.setStyle("-fx-selection-bar: #000080;");
        mb.setStyle("-fx-background-color: #87cefa;");

        return mb;
    }

    public static MenuBar cateringMenu(){
        MenuBar mb = new MenuBar();

        mb.setStyle("-fx-selection-bar: #000080;");
        mb.setStyle("-fx-background-color: #87cefa;");

        mb.getMenus().add(new HelperMenu());
        mb.getMenus().add(new EventMenu());
        mb.getMenus().add(new EdibleMenu());

        return mb;
    }
    
}