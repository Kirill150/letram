package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * Created by Papai on 2016.08.03..
 */
public class EditDriverController {

    @FXML
    private TextField Vards;

    @FXML
    private ComboBox EditDriverCBox;

    @FXML
    private void initialize(){

        EditDriverCBox.setValue("Select Driver");
        EditDriverCBox.setTooltip(new Tooltip("Select driver to edit"));
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Janis",
                        "Peteris",
                        "Aleksandrs"
                );
        EditDriverCBox.setItems(options);

    }

    public void ChangeName(ActionEvent actionEvent) {
        Vards.setText(EditDriverCBox.getValue().toString());
    }
}
