package Controllers;


import database.CreateStatement;
import database.ExecuteStatement;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Objects.Driver;

import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;


public class Controller {

    @FXML
    private TextField HToday, HMonth, Shift, TramD, Day;
    @FXML
    public Label Text;
    @FXML
    public TableColumn<Driver,String> ColVards;
    @FXML
    public TableColumn<Driver,String> ColUzvards;
    @FXML
    public TableColumn<Driver,String> ColKods;
    @FXML
    public TableView DriverTable;

    @FXML
    public void initialize(){
    ExecuteStatement.createTable(CreateStatement.CreateStatementForAllDriversTable());
    ExecuteStatement.createTable(CreateStatement.CreateStatementForPlaningTable());

        ColVards.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        ColUzvards.setCellValueFactory(new PropertyValueFactory<Driver, String>("surname"));
        ColKods.setCellValueFactory(new PropertyValueFactory<Driver, String>("code"));

        DriverTable.setItems(ExecuteStatement.ParsingDrivers());



        DriverTable.setRowFactory(item -> {
            TableRow<Driver> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Driver clickedRow = row.getItem();
                    if(clickedRow.getName().equals("Vasja")){
                    HToday.setText("VasjasValue");
                    HMonth.setText("VasjasValue");
                    Shift.setText("VasjasValue");
                    TramD.setText("VasjasValue");
                    Day.setText("VasjasValue");}
                    if(clickedRow.getName().equals("Matis")){
                    HToday.setText("MatissValue");
                    HMonth.setText("MatissValue");
                    Shift.setText("MatissValue");
                    TramD.setText("MatissValue");
                    Day.setText("MatissValue");}
                }
           });return row;
        });




    }

    public void EditDriver(ActionEvent actionEvent) throws Exception{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToDriversUi/editDriver.fxml"));
        stage.setTitle("Edit Driver");
        stage.setMinHeight(250);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> {
            updateDriversTable();
        });
    }

    public void DeleteDriver(ActionEvent actionEvent) throws Exception {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToDriversUi/deleteDriver.fxml"));
        stage.setTitle("Delete Driver");
        stage.setMinHeight(100);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> {
            updateDriversTable();
        });
    }

    public void AddDriver(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        Parent root = loader.load(getClass().getResource("../FXML/ui/LinkedToDriversUi/addDriver.fxml"));
        stage.setTitle("Add Driver");
        stage.setMinHeight(150);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> {
           updateDriversTable();
        });
   }

    public void AddTram(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/addTram.fxml"));
        stage.setTitle("Add Tram");
        stage.setMinHeight(150);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();


    }

    public void EditTram(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/editTram.fxml"));
        stage.setTitle("Edit Tram");
        stage.setMinHeight(250);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void DeleteTram(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/deleteTram.fxml"));
        stage.setTitle("Delete Tram");
        stage.setMinHeight(100);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void PlanAdd(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/addTram.fxml"));
        stage.setTitle("Add Tram");
        stage.setMinHeight(150);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();


    }

    public void updateDriversTable(){

        DriverTable.setItems(ExecuteStatement.ParsingDrivers());
    }




}
