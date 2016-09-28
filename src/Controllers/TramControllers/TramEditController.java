package Controllers.TramControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * Created by Papai on 2016.08.03..
 */
public class TramEditController {

    @FXML
    private ComboBox EditTramBo;

    @FXML
    private void initialize(){

        EditTramBo.setValue("Select tram to edit");
        ObservableList<String> options2 =
                FXCollections.observableArrayList(
                        "Tram1",
                        "Tram2",
                        "Tram3"
                );
        EditTramBo.setItems(options2);
    }
}
