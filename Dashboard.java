package fxProj;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Dashboard {
    private GridPane gpane;
    private AidManager am;

    public Dashboard(AidManager am) {
        this.am = am;

        Label benlb    = new Label("Total number of beneficiaries : ");
        Label aidlb    = new Label("Total number of aid items : ");
        Label distrlb  = new Label("Total distributions : ");
        Label serCitylb = new Label("Most frequently served city : ");

        Text bentxt    = new Text(String.valueOf(am.getBeneficiaries().size()));
        Text aidtxt    = new Text(String.valueOf(am.getItems().size()));
        Text distrtxt  = new Text(String.valueOf(am.getDistribution().size()));
        Text serCitytxt = new Text(am.mostServedCity());

        gpane = new GridPane();
        gpane.setHgap(15);
        gpane.setVgap(15);
        gpane.setPadding(new Insets(15, 5, 5, 5));

        gpane.add(benlb,     0, 0); gpane.add(bentxt,     1, 0);
        gpane.add(aidlb,     0, 1); gpane.add(aidtxt,     1, 1);
        gpane.add(distrlb,   0, 2); gpane.add(distrtxt,   1, 2);
        gpane.add(serCitylb, 0, 3); gpane.add(serCitytxt, 1, 3);

        Button refresh = new Button("Refresh");
        refresh.setOnAction(e -> {
            bentxt.setText(String.valueOf(am.getBeneficiaries().size()));
            aidtxt.setText(String.valueOf(am.getItems().size()));
            distrtxt.setText(String.valueOf(am.getDistribution().size()));
            serCitytxt.setText(am.mostServedCity());
        });
        gpane.add(refresh, 1, 5);
    }

    public GridPane getGpane() { return gpane; }
}

