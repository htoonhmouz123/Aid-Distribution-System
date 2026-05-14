package fxProj;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.GregorianCalendar;

public class StatisticalReportsSc {
    private AidManager am;
    private GridPane gpane;

    StatisticalReportsSc(AidManager am) {
        this.am = am;
        gpane = new GridPane();
        gpane.setHgap(10);
        gpane.setVgap(10);
        gpane.setPadding(new Insets(15));
    }

    void clear() {
        gpane.getChildren().clear();
    }

    public void numFamserv() {
        clear();
        Label citylbl = new Label("Enter City:");
        TextField citytxt = new TextField();
        Button generate = new Button("Generate");
        Label resultlb = new Label();

        generate.setOnAction(e -> {
            String city = citytxt.getText().trim();
            if (city.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter a city", ButtonType.OK).show();
                return;
            }
            int num = am.numFamilyServed(city);
            resultlb.setText("Number of families served: " + num);
        });

        gpane.add(citylbl, 0, 0);
        gpane.add(citytxt, 1, 0);
        gpane.add(generate, 0, 1);
        gpane.add(resultlb, 0, 2);
    }

    public void totaid() {
        clear();
        Label citylbl = new Label("Enter City (leave blank for all):");
        TextField citytxt = new TextField();
        Button generate = new Button("Generate");
        Label resultlb = new Label();

        generate.setOnAction(e -> {
            int total = am.TotalNumAidItemDistributed(citytxt.getText().trim());
            resultlb.setText("Total aid items distributed: " + total);
        });

        gpane.add(citylbl, 0, 0);
        gpane.add(citytxt, 1, 0);
        gpane.add(generate, 0, 1);
        gpane.add(resultlb, 0, 2);
    }

    public void countcat() throws ItemNotFoundException {
        clear();
        ListView<String> list = new ListView<>();
        list.setItems(FXCollections.observableArrayList(
                "FoodPackage : " + am.CountDistributedItemCategory("FoodPackage"),
                "MedicalKit : " + am.CountDistributedItemCategory("MedicalKit"),
                "WinterBag : " + am.CountDistributedItemCategory("WinterBag"),
                "EmergencyKit : " + am.CountDistributedItemCategory("EmergencyKit")
        ));
        gpane.add(list, 0, 0);
    }

    public void showdates() {
        clear();

        TextField y1 = new TextField(); TextField y2 = new TextField();
        TextField m1 = new TextField(); TextField m2 = new TextField();
        TextField d1 = new TextField(); TextField d2 = new TextField();

        gpane.add(new Text("From year:"),  0, 0); gpane.add(y1, 1, 0);
        gpane.add(new Text("From month:"), 0, 1); gpane.add(m1, 1, 1);
        gpane.add(new Text("From day:"),   0, 2); gpane.add(d1, 1, 2);
        gpane.add(new Text("To year:"),    0, 4); gpane.add(y2, 1, 4);
        gpane.add(new Text("To month:"),   0, 5); gpane.add(m2, 1, 5);
        gpane.add(new Text("To day:"),     0, 6); gpane.add(d2, 1, 6);

        Button generate = new Button("Generate");
        ListView<String> list = new ListView<>();

        generate.setOnAction(e -> {
            try {
                list.getItems().clear();
                GregorianCalendar start = new GregorianCalendar(
                        Integer.parseInt(y1.getText()), Integer.parseInt(m1.getText()) - 1, Integer.parseInt(d1.getText()));
                GregorianCalendar end = new GregorianCalendar(
                        Integer.parseInt(y2.getText()), Integer.parseInt(m2.getText()) - 1, Integer.parseInt(d2.getText()));


                for (DistributionEvent event : am.getDistribution()) {
                    GregorianCalendar eventDate = event.date;
                    if (!eventDate.before(start) && !eventDate.after(end)) {
                        list.getItems().add(
                                event.getBeneficiary().getName() + " - " +
                                        event.getItem().getCode() + " - " +
                                        event.getDate());
                    }
                }
                if (list.getItems().isEmpty()) {
                    list.getItems().add("No distributions found in this date range.");
                }
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Please enter valid dates", ButtonType.OK).show();
            }
        });

        gpane.add(generate, 2, 7);
        gpane.add(list, 0, 8);
    }

    public void mostCity() {
        clear();
        Label mostcityserv = new Label("Most served city: " + am.mostServedCity());
        gpane.add(mostcityserv, 0, 0);
    }

    public AidManager getAid() { return am; }
    public GridPane getGpane() { return gpane; }
}
