package Controllers.TramControllers;

import Objects.Tram;
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
 * Created by Papai on 2016.08.03..
 */
public class TramEditController {

    @FXML
    private ComboBox EditTramBo, ColorPicker;

    @FXML
    private TextField Id, Number;

TramRepo repo = new TramRepo();

    @FXML
    private void initialize(){

        EditTramBo.setValue("Select tram to edit");
        ObservableList<String> options2 =
                FXCollections.observableArrayList();
       repo.getTrams().forEach(item->options2.add(item.getId()));
        EditTramBo.setItems(options2);

        ColorPicker.setValue("Select color");
        ObservableList<TramColors> colors =
                FXCollections.observableArrayList(
                        TramColors.values()
                );
        ColorPicker.setItems(colors);

    }

    public void Ok(ActionEvent actionEvent) {

        Tram tram = new Tram();
        tram.setNumber(Number.getText());
        tram.setId(Id.getText());
        tram.setColor(ColorPicker.getValue().toString());

        repo.update(Number.getText(),Id.getText(),ColorPicker.getValue().toString(), EditTramBo.getValue().toString());

        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void SelectTram(ActionEvent actionEvent) {

        Tram tram = repo.getTram(EditTramBo.getValue().toString());

        Id.setText(tram.getId());
        Number.setText(tram.getNumber());
        ColorPicker.setValue(tram.getColor());
    }
}
