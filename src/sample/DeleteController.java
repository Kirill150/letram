package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

/**
 * Created by Papai on 2016.08.03..
 */
public class DeleteController {

    @FXML
    private ComboBox DeleteChoiceBox;



    @FXML
    private void initialize(){
        DeleteChoiceBox.setValue("Select driver to delete");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Janis",
                        "Peteris",
                        "Aleksandrs"
                );
        DeleteChoiceBox.setItems(options);



    }

}
