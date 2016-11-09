package Controllers.DriverControllers;

import database.DriverRepo;
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
    DriverRepo repo = new DriverRepo();

    @FXML
    void InsertDriver(ActionEvent event) throws IOException {

        repo.insert(id.getText(),Name.getText(),Surname.getText());
        ((Button)event.getTarget()).getScene().getWindow().hide();

    }

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        ((Button)event.getTarget()).getScene().getWindow().hide();
        }

    }



