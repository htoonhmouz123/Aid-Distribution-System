package fxProj;


import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class Menus{
    private HBox hbox;
    private MenuItem dash;
    private MenuItem beneMi;
    private MenuItem aidMi;
    private MenuItem distMi;
    private MenuItem cityFamser;
    private MenuItem totitem;
    private MenuItem countcat;
    private MenuItem dates2;
    private MenuItem servcity;

    private MenuItem fileMi;

    Menus(){
        MenuBar menubar = new MenuBar();
        Menu Home = new  Menu("HomeScreen");
        Menu beneM = new Menu("Beneficiary ");
        Menu aidM = new Menu("Aid Item ");
        Menu distM = new Menu("Aid Distribution");
        Menu reportM = new Menu("Reports ");
        Menu fileM = new Menu("File ");

        dash = new MenuItem("Dashboard");
        beneMi= new MenuItem("Beneficiary Management");
        aidMi = new MenuItem("Aid Item Management");
        distMi = new MenuItem("Aid Distribution");
        cityFamser = new MenuItem(" Number of families served in a city ");
        totitem = new MenuItem("Total aid items distributed ");
        countcat = new MenuItem("Count aid items by category ");
        dates2 = new MenuItem("Beneficiaries served between two dates");
        servcity = new MenuItem("Most frequently served city ");
        fileMi = new MenuItem("File Operations");

        menubar.getMenus().addAll(Home,beneM,aidM,distM,reportM, fileM);
        Home.getItems().add(dash);
        beneM.getItems().add(beneMi);
        aidM.getItems().add(aidMi);
        distM.getItems().add(distMi);
        reportM.getItems().addAll(cityFamser,totitem,countcat,dates2,servcity);
        fileM.getItems().add(fileMi);

        hbox = new HBox(5);
        hbox.getChildren().add(menubar);
        hbox.setAlignment(Pos.TOP_LEFT);

    }


    public HBox getHbox() {
        return hbox;
    }

    public MenuItem getDash() {
        return dash;
    }

    public MenuItem getBeneMi() {
        return beneMi;
    }

    public MenuItem getDistMi() {
        return distMi;
    }
    public MenuItem getFileMi() {
        return fileMi;
    }

    public MenuItem getAidMi() {
        return aidMi;
    }

    public MenuItem getCityFamser() {
        return cityFamser;
    }
    public MenuItem getTotitem() {
        return totitem;
    }
    public MenuItem getCountcat() {
        return countcat;
    }
    public MenuItem getDates2() {
        return dates2;
    }
    public MenuItem getServcity() {
        return servcity;
    }
    public MenuItem getFile() {
        return fileMi;
    }

}