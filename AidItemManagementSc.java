package fxProj;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class AidItemManagementSc {
    private GridPane gpane;
    private AidManager am;
    private TableView<AidItem> table;
    private ObservableList<AidItem> itemlist;
    private StackPane stackPane;


    AidItemManagementSc(AidManager am) {
        this.am = am;

        table = new TableView<>();
        itemlist = FXCollections.observableArrayList(am.getItems());
        ComboBox<String> Category = new ComboBox<>();
        Category.getItems().addAll("FoodPackage", "MedicalKit", "WinterBag", "EmergencyKit");

        Label codelb = new Label("Code : ");
        Label desclb = new Label("description : ");
        Label Catelb = new Label("Category");


        TextField codetxt = new TextField();
        TextField desctxt = new TextField();

        gpane = new GridPane();
        gpane.setHgap(5);
        gpane.setVgap(5);
        gpane.setPadding(new Insets(5, 5, 5, 5));

        gpane.add(Category, 1, 0);
        gpane.add(codelb, 0, 1);
        gpane.add(desclb, 0, 2);
        gpane.add(Catelb, 0, 0);
        gpane.add(codetxt, 1, 1);
        gpane.add(desctxt, 1, 2);

        Button add = new Button("Add Aid Items");
        add.setOnAction(e -> {
            try {
                if (codetxt.getText().isEmpty() || desctxt.getText().isEmpty() || Category.getValue() == null) {
                    throw new IllegalArgumentException("Please fill all the fields");
                }

                String code = codetxt.getText();
                String description = desctxt.getText();
                String category = Category.getValue();

                if (Category.getValue().equals("FoodPackage")) {
                    am.AddAidItem(new FoodPackage(code,description));
                } else if (Category.getValue().equals("MedicalKit")) {
                    am.AddAidItem(new Medicalkit(code,description));
                }else if (Category.getValue().equals("WinterBag")) {
                    am.AddAidItem(new WinterBag(code,description));
                }else if (Category.getValue().equals("EmergencyKit")) {
                    am.AddAidItem(new EmergencyKit(code,description));
                }else {
                    throw new IllegalArgumentException("Please fill all the fields");
                }
                itemlist.setAll(am.getItems());

            } catch (DuplicateRegistrationException ex) {
                new Alert(Alert.AlertType.ERROR, "Item exists", ButtonType.OK).show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
            }
        });


        Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            codetxt.clear();
            desctxt.clear();
            Category.getSelectionModel().clearSelection();
        });
        gpane.add(add, 1, 5);
        gpane.add(clear, 2, 5);

        TableColumn<AidItem, String> codecol = new TableColumn<>("Code");
        codecol.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<AidItem, String> desccol = new TableColumn<>("Description");
        desccol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<AidItem, String> catcol = new TableColumn<>("Category");
        catcol.setCellValueFactory(new PropertyValueFactory<>("category"));


        table.getColumns().addAll(codecol, desccol, catcol);
        table.setItems(itemlist);

        stackPane = new StackPane();
        stackPane.getChildren().add(table);

    }

    public GridPane getGpane() {
        return gpane;
    }
    public TableView getTable() {
        return table;
    }
    public ObservableList<AidItem> getItemlist() {
        return itemlist;
    }

    public AidManager getAm() {
        return am;
    }
    public StackPane getStackPane() {
        return stackPane;
    }
}
