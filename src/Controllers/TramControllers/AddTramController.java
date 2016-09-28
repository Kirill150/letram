package Controllers.TramControllers;

import Objects.Tram;
import Objects.TramColors;
import database.CreateStatement;
import database.ExecuteStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Papai on 2016.09.24..
 */
public class AddTramController {

    @FXML
    private ComboBox colorPicker;
    @FXML
    private TextField TramN,TramId;

    private Tram tram;

    @FXML
   void initialize(){

        colorPicker.setValue("Select color");
        ObservableList<TramColors> colors =
                FXCollections.observableArrayList(
                        TramColors.BLUE,
                        TramColors.RED,
                        TramColors.GREEN
                );
        colorPicker.setItems(colors);

    }

    public void Ok(ActionEvent actionEvent) {

        tram = new Tram();
        tram.setColor(colorPicker.getValue().toString());
        tram.setId(TramId.getText());
        tram.setNumber(TramN.getText());
        ExecuteStatement.insert(CreateStatement.insertTram(tram));
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }
}
