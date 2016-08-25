package Controllers;

import Objects.Driver;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * Created by Papai on 2016.08.17..
 */
public class PlanningRecordController {
    public void Ok(ActionEvent actionEvent) {
        Driver d = new Driver();
        d.setName("papai");
        Controller.AddHoursstage.setUserData(d);
        System.out.println(Controller.AddHoursstage.getUserData().toString());
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }
}
