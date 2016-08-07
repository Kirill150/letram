package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * Created by Papai on 2016.08.03..
 */
public class TramDeleteController {

    @FXML
    private ComboBox DeleteTramBox;

    @FXML
    private  void initialize(){
                DeleteTramBox.setValue("Select tram to delete");
        ObservableList<String> options2 =
                FXCollections.observableArrayList(
                        "Tram1",
                        "Tram2",
                        "Tram3"
                );
        DeleteTramBox.setItems(options2);
    }
}
