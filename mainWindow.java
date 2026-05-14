package fxProj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class mainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        AidManager am = new AidManager();
        BorderPane root = new BorderPane();

        Dashboard dashboard = new Dashboard(am);
        BeneficiaryManagementSc benSc = new BeneficiaryManagementSc(am);
        AidItemManagementSc aidSc = new AidItemManagementSc(am);
        AidDistributionSc aidScd = new AidDistributionSc(am);
        StatisticalReportsSc stRep = new StatisticalReportsSc(am);
        FileOperationsSc fileSc = new FileOperationsSc(am);

        Menus menu = new Menus();

        root.setTop(menu.getHbox());
        root.setCenter(dashboard.getGpane());

        menu.getDash().setOnAction(e->{
            root.setCenter(dashboard.getGpane());
            root.setBottom(null);
        });

        menu.getBeneMi().setOnAction(e->{
            root.setCenter(benSc.getTable());
            root.setBottom(benSc.getGpane());
        });
        menu.getAidMi().setOnAction(e->{
            root.setCenter(aidSc.getTable());
            root.setBottom(aidSc.getGpane());
        });
        menu.getDistMi().setOnAction(e->{
            aidScd.refreshCombo();
            root.setCenter(aidScd.getTable());
            root.setBottom(aidScd.getGpane());
        });
        menu.getCityFamser().setOnAction(e->{
            stRep.numFamserv();
            root.setCenter(stRep.getGpane());
            root.setBottom(null);

        });
        menu.getTotitem().setOnAction(e->{
            stRep.totaid();
            root.setCenter(stRep.getGpane());
            root.setBottom(null);
        });
        menu.getCountcat().setOnAction(e->{
            try {
                stRep.countcat();
            } catch (ItemNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            root.setCenter(stRep.getGpane());
            root.setBottom(null);
        });
        menu.getDates2().setOnAction(e->{
            stRep.showdates();
            root.setCenter(stRep.getGpane());
            root.setBottom(null);
        });
        menu.getServcity().setOnAction(e->{
            stRep.mostCity();
            root.setCenter(stRep.getGpane());
            root.setBottom(null);
        });

        menu.getFileMi().setOnAction(e->{
            root.setCenter(fileSc.getGpane());
            root.setBottom(null);
        });

        Scene scene = new Scene(root, 800 ,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

