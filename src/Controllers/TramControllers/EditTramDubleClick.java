package Controllers.TramControllers;

import Controllers.Controller;
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
 * Created by Papai on 2016.10.05..
 */
public class EditTramDubleClick {

    TramRepo repo = new TramRepo();
    @FXML
    private TextField Id,Number;
    @FXML
    private ComboBox ColorPicker;
    @FXML
   private void initialize(){

        ObservableList<TramColors> colors =
                FXCollections.observableArrayList(TramColors.values());
        ColorPicker.setItems(colors);

        if(Controller.tramForDoubleClickEdit!=null){
            Id.setText(Controller.tramForDoubleClickEdit.getId());
            Number.setText(Controller.tramForDoubleClickEdit.getNumber());
            ColorPicker.setValue(Controller.tramForDoubleClickEdit.getColor());
        }
    }

    public void Ok(ActionEvent actionEvent) {

        repo.update(Number.getText(),Id.getText(),ColorPicker.getValue().toString(),Controller.tramForDoubleClickEdit.getId());

        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Cancel(ActionEvent actionEvent) {

        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }
}
