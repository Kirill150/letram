package Controllers.PlanningConrollers;


import Controllers.Controller;
import Objects.MothMapper;
import database.PlanningRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Map;

/**
 * Created by Papai on 2016.08.17..
 */


public class PlanningRecordController{

    @FXML
    private TextField Tram, Hours, Shift;
    @FXML
    private Label Date;
    @FXML
    private ComboBox<Integer> PlanYear, MainDay, MainYear;

    private PlanningRepo planningRepo = new PlanningRepo();

    MothMapper mapper = new MothMapper();

    @FXML
    public void initialize(){

        Map<String, String> userData = (Map) Controller.AddHoursStage.getUserData();
       // Date.setText(userData.get("day")+" of " + userData.get("month") +" "+ userData.get("year"));

         if(userData.containsKey("hours")) {
             Tram.setText(userData.get("tramId"));
             Hours.setText(userData.get("hours"));
             Shift.setText(userData.get("shift"));
         }

    }

    @FXML
    public void initialize(String name){

        Tram.setText(name);

    }

    public void Ok(ActionEvent actionEvent) {

        Map<String, String> userData = (Map) Controller.AddHoursStage.getUserData();
        if(userData!=null){
            if(userData.containsKey("hours")){
                System.out.println("update");

                planningRepo.updateRecord(
                        Integer.parseInt(userData.get("day")),
                        mapper.getMonthIntValue(userData.get("month")),
                        Integer.parseInt(userData.get("year") ),
                        userData.get("driverId"),
                        userData.get("hours"),
                        userData.get("tramId"),
                        userData.get("shift"),
                        Tram.getText(),
                        Hours.getText(),
                        Shift.getText());

            }else {
                System.out.println("insert");

                planningRepo.insertRecord(
                        Integer.parseInt(userData.get("day")),
                        mapper.getMonthIntValue(userData.get("month")),
                        Integer.parseInt(userData.get("year")) ,
                        userData.get("driverId"),
                        Tram.getText(),
                        Hours.getText(),
                        Shift.getText(),
                        userData.get("holiday"));
            }

        }
        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
        ((Map) Controller.AddHoursStage.getUserData()).clear();
    }

    public void Cancel(ActionEvent actionEvent) {

        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();
    }

    public void Clear(ActionEvent actionEvent) {

        ((Button)actionEvent.getTarget()).getScene().getWindow().hide();

    }
}
