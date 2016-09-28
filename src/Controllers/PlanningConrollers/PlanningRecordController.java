package Controllers.PlanningConrollers;

import Controllers.MainController.Controller;
import Objects.PlanningRecord;
import Objects.TramIdShiftHours;
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



    public void Ok(ActionEvent actionEvent) {
        if(Controller.AddHoursStage.getUserData()!=null){
            PlanningRecord planningRecord = new PlanningRecord();
            Map<String, String> userData =(Map) Controller.AddHoursStage.getUserData();
            planningRecord.setDriverId(userData.get("driver"));
            TramIdShiftHours tramIdShiftHours = new TramIdShiftHours();
            tramIdShiftHours.setHours(Hours.getText());
            tramIdShiftHours.setTramId(Tram.getText());
            tramIdShiftHours.setShift(Shift.getText());
            Map<String, TramIdShiftHours> hoursPerDayMap = new HashMap<>();
            hoursPerDayMap.put(userData.get("day"), tramIdShiftHours);
            planningRecord.setHoursPerDaymap(hoursPerDayMap);

            Controller.AddHoursStage.setOnHiding(event -> {

            });
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
