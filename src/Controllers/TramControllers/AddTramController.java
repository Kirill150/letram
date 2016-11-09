package Controllers.TramControllers;

import Objects.TramColors;
import database.TramRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by Papai on 2016.09.24..
 */
public class AddTramController {

    @FXML
    private ComboBox colorPicker;
    @FXML
    private TextField TramN,TramId;

    TramRepo repo = new TramRepo();

    @FXML
   void initialize(){

        colorPicker.setValue("Select color");
        ObservableList<TramColors> colors =
                FXCollections.observableArrayList(

                        TramColors.values()
                );
        colorPicker.setItems(colors);

    }

    public void Ok(ActionEvent actionEvent) {

        repo.insert(TramN.getText(),TramId.getText(),colorPicker.getValue().toString());

        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }
}
