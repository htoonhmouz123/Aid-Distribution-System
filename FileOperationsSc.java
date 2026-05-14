package fxProj;



import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class FileOperationsSc {
    private AidManager am;
    private GridPane gpane;

    FileOperationsSc(AidManager am){
        this.am = am;
        gpane = new GridPane();
        gpane.setHgap(10);
        gpane.setVgap(10);
        gpane.setPadding(new Insets(20));

        Button savetxt = new Button("Save to text file");
        savetxt.setOnAction(e -> {
            try{
                am.SaveToTextFile("data.txt");
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
            }
        });
        Button loadtxt = new Button("Load from text file");
        loadtxt.setOnAction(e -> {
            try{
                am.LoadFromTextFile("data.txt");
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
            }
        });
        Button savebin = new Button("Save to Binary file");
        savebin.setOnAction(e -> {
            try{
                am.SaveToBinaryFile("data.bin");
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
            }
        });
        Button loadbin = new Button("Load from Binary file");
        loadbin.setOnAction(e -> {
            try{
                am.LoadFromBinaryFile("data.bin");
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
            }
        });
        gpane.add(savetxt, 0, 0);
        gpane.add(loadtxt, 0, 1);
        gpane.add(savebin, 0, 2);
        gpane.add(loadbin, 0, 3);

    }

    public GridPane getGpane() {
        return gpane;
    }

    public AidManager getAm() {
        return am;
    }
}
