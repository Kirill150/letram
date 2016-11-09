package Controllers.DriverControllers;

import database.DriverRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Papai on 2016.08.08..
 */
public class DeleteDriver {

    DriverRepo repo = new DriverRepo();

    @FXML
    public ComboBox DeleteComboBox;

    @FXML
    public void initialize(){
        DeleteComboBox.setValue("Select Driver");

        List<String> names =  new LinkedList();
        repo.getDrivers().forEach(item-> names.add(item.getName()));
        ObservableList<String> drivers =  FXCollections.observableList(names);
        DeleteComboBox.setItems(drivers);

    }

    public void DeleteDriver(ActionEvent actionEvent) {

         repo.delete(DeleteComboBox.getValue().toString());

        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }
}
