package Controllers.DriverControllers;

import Objects.Driver;
import database.CreateStatement;
import database.ExecuteStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Papai on 2016.08.03..
 */
public class EditDriver {


    @FXML
    private TextField Vards;
    @FXML
    private TextField Uzvards;
    @FXML
    private TextField Kods;

    Driver driver2;

    @FXML
    private ComboBox EditDriverCBox;

    @FXML
    private void initialize(){

        EditDriverCBox.setValue("Select Driver");
        List<String> names =  new LinkedList();
        ExecuteStatement.ParsingDrivers().forEach(item-> names.add(item.getName()));
        ObservableList<String> drivers =  FXCollections.observableList(names);
        EditDriverCBox.setItems(drivers);

    }

    public void ChangeName(ActionEvent actionEvent) {
        driver2 =  ExecuteStatement.findDriver(EditDriverCBox.getValue().toString());
        Vards.setText(driver2.getName());
        Uzvards.setText(driver2.getSurname());
        Kods.setText(driver2.getCode());
    }

    public void Ok(ActionEvent actionEvent) {


        Driver driver = new Driver();
        driver.setCode(Kods.getText());
        driver.setSurname(Uzvards.getText());
        driver.setName(Vards.getText());
        ExecuteStatement.UpdateDriver(CreateStatement.updateStatementForAllDriversTable(driver,driver2.getName()));
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();

    }
}
