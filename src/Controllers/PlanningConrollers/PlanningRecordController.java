package Controllers.PlanningConrollers;

import Controllers.MainController.Controller;
import Objects.PlanningRecord;
import Objects.TramIdShiftHours;
import database.CreateStatement;
import database.ExecuteStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Papai on 2016.08.17..
 */


public class PlanningRecordController {

    @FXML
    private TextField Tram, Hours, Shift;

@FXML
public void initialize(){

        Map<String, String> userData = (Map) Controller.AddHoursStage.getUserData();
    if(userData.containsKey("hours")) {
        Tram.setText(userData.get("tramId"));
        Hours.setText(userData.get("hours"));
        Shift.setText(userData.get("shift"));

    }

}

    public void Ok(ActionEvent actionEvent) {

        Map<String, String> userData = (Map) Controller.AddHoursStage.getUserData();
        if(userData!=null){
            if(userData.containsKey("hours")){
                System.out.println(userData.get("driverId"));

                ExecuteStatement.insert(CreateStatement.updateStatementForPlanningTable(userData.get("day"),
                                                                                        userData.get("driverId"),
                                                                                        userData.get("hours"),
                                                                                        userData.get("tramId"),
                                                                                        userData.get("shift"),
                                                                                        Tram.getText(),
                                                                                        Hours.getText(),
                                                                                        Shift.getText()));

            }else {
                System.out.println("insert");
                ExecuteStatement.insert(CreateStatement.insertStatementForPlaningRecord(userData.get("driverId"), Tram.getText(), userData.get("day"),
                        Hours.getText(), Shift.getText(), false));
            }

        }
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();

    }

    public void Cancel(ActionEvent actionEvent) {
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Clear(ActionEvent actionEvent) {



        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();

    }
}
