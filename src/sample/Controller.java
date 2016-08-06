package sample;


import Objects.Stundas;
import Objects.Tram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.LinkedList;

import static javafx.collections.FXCollections.observableArrayList;


public class Controller {


    @FXML
    private TableColumn<Driver,String> ColVards;
    @FXML
    private TableColumn<Driver,String> ColUzvards;
    @FXML
    private TableColumn<Driver,String> ColKods;

    @FXML
    private TableView DriverTable;
    @FXML
    private TableView MainTable;
    @FXML
    private TableView TablePlan;
    @FXML
    private TableColumn<Stundas,String> ColStundas;
    @FXML
    private TableColumn<Driver,String> ColTram1;
    @FXML
    private TableColumn<Stundas,String> ColTram2;
    @FXML
    private TableColumn<Stundas,String> ColTram3;
    @FXML
    private TableColumn<Driver,String> PlanVardsCol;
    @FXML
    private TableColumn<Driver,String> Plan1;
    @FXML
    private TableColumn<Driver,String> Plan2;
    @FXML
    private TableColumn<Driver,String> Plan3;
    @FXML
    private TableColumn<Driver,String> PlanKopa;

@FXML
private void initialize(){

    ColVards.setCellValueFactory(new PropertyValueFactory<Driver, String>("vards"));
    PlanVardsCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("vards"));
    Plan1.setCellValueFactory(new PropertyValueFactory<Driver, String>("hours"));
    Plan2.setCellValueFactory(new PropertyValueFactory<Driver, String>("hours"));
    Plan3.setCellValueFactory(new PropertyValueFactory<Driver, String>("hours"));
    PlanKopa.setCellValueFactory(new PropertyValueFactory<Driver, String>("Total"));
    ColUzvards.setCellValueFactory(new PropertyValueFactory<Driver, String>("uzvards"));
    ColKods.setCellValueFactory(new PropertyValueFactory<Driver, String>("kods"));
Driver driver1 = new Driver();
    driver1.setUzvards("Berzins");
    driver1.setVards("Janis");
    driver1.setKods("334242");
    driver1.setHours("8");
    driver1.setTotal("24");
    Driver driver2 = new Driver();
    driver2.setVards("Peteris");
    driver2.setUzvards("Laurins");
    driver2.setKods("12345");
    driver2.setHours("8");
    driver2.setTotal("24");
    Driver driver3 = new Driver();
    driver3.setVards("Aleksandrs");
    driver3.setUzvards("Purtis");
    driver3.setKods("999");
    driver3.setHours("8");
    driver3.setTotal("24");
    ObservableList<Driver> options =
            FXCollections.observableArrayList(driver1, driver2, driver3

            );
    DriverTable.setItems(options);
    TablePlan.setItems(options);

    ColStundas.setCellValueFactory(new PropertyValueFactory<Stundas, String>("stundas"));
    ColTram1.setCellValueFactory(new PropertyValueFactory<Driver, String>("vards"));
    ObservableList<Object> stundas =
            FXCollections.observableArrayList(
            );
    for(Integer i= 0; i<24; i++){

        Stundas st1 = new Stundas();
        st1.setStundas(i.toString());
        stundas.add(st1);

    }



    MainTable.setItems(stundas);












}

    public void EditDriver(ActionEvent actionEvent) throws Exception{



        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
        stage.setTitle("Edit Driver");
        stage.setMinHeight(250);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();




    }

    public void DeleteDriver(ActionEvent actionEvent) throws Exception {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
        stage.setTitle("Delete Driver");
        stage.setMinHeight(100);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();

    }

    public void AddDriver(ActionEvent actionEvent) throws Exception {



        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));
        stage.setTitle("Add Driver");
        stage.setMinHeight(150);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();

    }

    public void AddTram(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addTram.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("editTram.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("deleteTram.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("addTram.fxml"));
        stage.setTitle("Add Tram");
        stage.setMinHeight(150);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();


    }
}
