package Controllers.DriverControllers;

import Objects.Driver;
import database.CreateStatement;
import database.ExecuteStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InsertDriver {

    @FXML
    public Button InsertDriver;
    @FXML
    private Button CloseButton;
    @FXML
    private TextField Name;
    @FXML
    private TextField Surname;
    @FXML
    private TextField id;


    @FXML
    void InsertDriver(ActionEvent event) throws IOException {
        Driver driver = new Driver();
        driver.setName(Name.getText());
        driver.setSurname(Surname.getText());
        driver.setCode(id.getText());
        ExecuteStatement.insert(CreateStatement.insertStatementForAllDriversTable(driver));
        ((Button)event.getTarget()).getScene().getWindow().hide();

    }

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        ((Button)event.getTarget()).getScene().getWindow().hide();
        }

    }



