package fxProj;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class AidDistributionSc {
    private AidManager aid;
    private GridPane gpane;
    private ComboBox<Beneficiary> comboben;
    private ComboBox<AidItem> comboaid;
    private TableView<DistributionEvent> table;
    private ObservableList<DistributionEvent> distriblist;
    private StackPane stackPane;

    AidDistributionSc(AidManager aid) {
        this.aid = aid;

        table = new TableView<>();
        distriblist = FXCollections.observableArrayList();

        comboben = new ComboBox<>();
        comboaid = new ComboBox<>();

        TextField yeartxt = new TextField();
        TextField monthtxt = new TextField();
        TextField daytxt = new TextField();
        Label yearlb = new Label("Year : ");
        Label monthlb = new Label("Month : ");
        Label daylb = new Label("Day : ");

        gpane = new GridPane();
        gpane.setHgap(10);
        gpane.setVgap(10);
        gpane.setPadding(new Insets(10));

        gpane.add(new Label("Select Beneficiary"), 0, 0);
        gpane.add(new Label("Select Aid Item"), 0, 1);
        gpane.add(comboben, 1, 0);
        gpane.add(comboaid, 1, 1);
        gpane.add(new Label("Select Date"), 0, 2);
        gpane.add(yearlb, 1, 3);
        gpane.add(yeartxt, 2, 3);
        gpane.add(monthlb, 1, 4);
        gpane.add(monthtxt, 2, 4);
        gpane.add(daylb, 1, 5);
        gpane.add(daytxt, 2, 5);

        Button rec = new Button("Record Distribution");
        gpane.add(rec, 1, 6);

        rec.setOnAction(e -> {
            try {
                if (comboben.getValue() == null || comboaid.getValue() == null
                        || yeartxt.getText().isEmpty() || monthtxt.getText().isEmpty() || daytxt.getText().isEmpty()) {
                    throw new IllegalArgumentException("Please fill all fields");
                }

                Beneficiary b = comboben.getValue();
                AidItem ai = comboaid.getValue();
                int year = Integer.parseInt(yeartxt.getText());
                int month = Integer.parseInt(monthtxt.getText());
                int day = Integer.parseInt(daytxt.getText());

                DistributionEvent disEvent = new DistributionEvent(b, ai, year, month, day);

                distriblist.add(disEvent);
                aid.getDistribution().add(disEvent);

                comboaid.getSelectionModel().clearSelection();
                comboben.getSelectionModel().clearSelection();
                yeartxt.clear();
                monthtxt.clear();
                daytxt.clear();

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Year, month and day must be numbers", ButtonType.OK).show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).show();
            }
        });

        TableColumn<DistributionEvent, Integer> bencol = new TableColumn<>("Beneficiary ID");
        bencol.setCellValueFactory(new PropertyValueFactory<>("beneficiaryId"));

        TableColumn<DistributionEvent, String> aidcol = new TableColumn<>("Aid Item");
        aidcol.setCellValueFactory(new PropertyValueFactory<>("itemCode"));

        TableColumn<DistributionEvent, String> datecol = new TableColumn<>("Date");
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.getColumns().addAll(bencol, aidcol, datecol);
        table.setItems(distriblist);

        stackPane = new StackPane();
        stackPane.getChildren().add(table);
    }

    public void refreshCombo() {
        comboaid.setItems(FXCollections.observableArrayList(aid.getItems()));
        comboben.setItems(FXCollections.observableArrayList(aid.getBeneficiaries()));
        // FIX: also sync the table so loaded data appears after file load
        distriblist.setAll(aid.getDistribution());
    }

    public AidManager getAid() { return aid; }
    public GridPane getGpane() { return gpane; }
    public TableView<DistributionEvent> getTable() { return table; }
    public ObservableList<DistributionEvent> getDistriblist() { return distriblist; }
    public StackPane getStackPane() { return stackPane; }
}

