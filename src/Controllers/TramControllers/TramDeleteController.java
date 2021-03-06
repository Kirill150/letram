package Controllers.TramControllers;

import database.TramRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Papai on 2016.08.03..
 */
public class TramDeleteController {

    @FXML
    private ComboBox DeleteTramBox;

    TramRepo repo = new TramRepo();

    @FXML
    private  void initialize(){

        DeleteTramBox.setValue("Select Tram");

        List<String> ids =  new LinkedList();
        repo.getTrams().forEach(item-> ids.add(item.getId()));
        ObservableList<String> obIds =  FXCollections.observableList(ids);
        DeleteTramBox.setItems(obIds);

    }



    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Delete(ActionEvent actionEvent) {

         repo.delete(DeleteTramBox.getValue().toString());
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }
}
