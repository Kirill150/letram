package Controllers.TramControllers;

import Objects.Tram;
import Objects.TramColors;
import database.ExecuteStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by Papai on 2016.08.03..
 */
public class TramEditController {

    @FXML
    private ComboBox EditTramBo, ColorPicker;

    @FXML
    private TextField Id, Number;



    @FXML
    private void initialize(){

        EditTramBo.setValue("Select tram to edit");
        ObservableList<String> options2 =
                FXCollections.observableArrayList();
        ExecuteStatement.parsingTrams().forEach(item->options2.add(item.getId()));
        EditTramBo.setItems(options2);

        ColorPicker.setValue("Select color");
        ObservableList<TramColors> colors =
                FXCollections.observableArrayList(
                        TramColors.BLUE,
                        TramColors.RED,
                        TramColors.GREEN
                );
        ColorPicker.setItems(colors);

    }

    public void Ok(ActionEvent actionEvent) {


    }

    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void SelectTram(ActionEvent actionEvent) {
   Tram tram = ExecuteStatement.findTram(EditTramBo.getValue().toString());
        Id.setText(tram.getId());
        Number.setText(tram.getNumber());
        ColorPicker.setValue(tram.getColor());
    }
}
