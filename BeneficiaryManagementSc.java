package fxProj;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BeneficiaryManagementSc {
    private GridPane gpane;
    private AidManager am;
    private TableView<Beneficiary> table;
    private ObservableList<Beneficiary> benlist;
    private StackPane stackPane;

    BeneficiaryManagementSc(AidManager am) {
        this.am = am;

        table = new TableView<>();
        benlist = FXCollections.observableArrayList(am.getBeneficiaries());

        ComboBox<String> type = new ComboBox<>();
        type.getItems().addAll("Family", "Individual");

        Label Idlb = new Label("ID : ");
        Label Namelb = new Label("Name : ");
        Label citylb = new Label("City : ");
        Label countlb = new Label("Member count : ");
        Label statuslb = new Label("Status : ");

        TextField statustxt = new TextField();
        TextField counttxt = new TextField();
        TextField Idtxt = new TextField();
        TextField NameTxt = new TextField();
        TextField cityTxt = new TextField();

        gpane = new GridPane();
        gpane.setHgap(5);
        gpane.setVgap(5);
        gpane.setPadding(new Insets(5));

        gpane.add(new Label("Type"), 0, 0);
        gpane.add(type, 1, 0);
        gpane.add(Idlb, 0, 1);
        gpane.add(Idtxt, 1, 1);
        gpane.add(Namelb, 0, 2);
        gpane.add(NameTxt, 1, 2);
        gpane.add(citylb, 0, 3);
        gpane.add(cityTxt, 1, 3);

        gpane.add(countlb, 0, 4);
        gpane.add(counttxt, 1, 4);
        gpane.add(statuslb, 0, 5);
        gpane.add(statustxt, 1, 5);

        countlb.setVisible(false);
        counttxt.setVisible(false);
        statuslb.setVisible(false);
        statustxt.setVisible(false);

        type.setOnAction(e -> {
            if ("Family".equals(type.getValue())) {
                counttxt.setVisible(true);
                countlb.setVisible(true);
                statustxt.setVisible(false);
                statuslb.setVisible(false);
            } else if ("Individual".equals(type.getValue())) {
                statustxt.setVisible(true);
                statuslb.setVisible(true);
                counttxt.setVisible(false);
                countlb.setVisible(false);
            }
        });

        Button add = new Button("Add Beneficiary");
        add.setOnAction(e -> {
            try {
                if (Idtxt.getText().isEmpty() || NameTxt.getText().isEmpty()
                        || cityTxt.getText().isEmpty() || type.getValue() == null) {
                    throw new IllegalArgumentException("Please fill all fields");
                }
                int id = Integer.parseInt(Idtxt.getText());
                String name = NameTxt.getText();
                String city = cityTxt.getText();

                if ("Family".equals(type.getValue())) {
                    if (counttxt.getText().isEmpty()) throw new IllegalArgumentException("Enter member count");
                    int count = Integer.parseInt(counttxt.getText());
                    am.RegisterBeneficiary(new Family(id, name, city, count));
                } else if ("Individual".equals(type.getValue())) {
                    String status = statustxt.getText();
                    am.RegisterBeneficiary(new Individual(id, name, city, status));
                }
                benlist.setAll(am.getBeneficiaries());

            } catch (DuplicateRegistrationException ex) {
                new Alert(Alert.AlertType.ERROR, "Beneficiary ID already exists", ButtonType.OK).show();
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "ID and member count must be numbers", ButtonType.OK).show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).show();
            }
        });

        Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            Idtxt.clear(); NameTxt.clear(); cityTxt.clear();
            statustxt.clear(); counttxt.clear();
            type.getSelectionModel().clearSelection();
            countlb.setVisible(false); counttxt.setVisible(false);
            statustxt.setVisible(false); statuslb.setVisible(false);
        });

        gpane.add(add, 1, 6);
        gpane.add(clear, 2, 6);

        TableColumn<Beneficiary, Integer> idcol = new TableColumn<>("ID");
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Beneficiary, String> namecol = new TableColumn<>("Name");
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Beneficiary, String> citycol = new TableColumn<>("City");
        citycol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Beneficiary, String> typecol = new TableColumn<>("Type");
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.getColumns().addAll(idcol, namecol, citycol, typecol);
        table.setItems(benlist);

        stackPane = new StackPane();
        stackPane.getChildren().add(table);

        Label searchlbl = new Label("Search by ID:");
        TextField searchtxt = new TextField();
        Button search = new Button("Search");

        gpane.add(searchlbl, 0, 7);
        gpane.add(searchtxt, 1, 7);
        gpane.add(search, 2, 7);

        search.setOnAction(e -> {
            try {
                int searchId = Integer.parseInt(searchtxt.getText());
                for (int i = 0; i < benlist.size(); i++) {
                    if (benlist.get(i).getId() == searchId) {
                        table.getSelectionModel().select(i);
                        table.scrollTo(i);
                        return;
                    }
                }
                new Alert(Alert.AlertType.INFORMATION, "Beneficiary not found", ButtonType.OK).show();
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Invalid ID", ButtonType.OK).show();
            }
        });
    }

    public GridPane getGpane() { return gpane; }
    public AidManager getAm() { return am; }
    public TableView<Beneficiary> getTable() { return table; }
    public ObservableList<Beneficiary> getBenlist() { return benlist; }
    public StackPane getStackPane() { return stackPane; }
}
