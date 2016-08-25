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
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Objects.Driver;

import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;


public class Controller {

    @FXML
    private TextField HToday, HMonth, Shift, TramD, Day;
    @FXML
    public TableColumn<Driver,String> PlanVardsCol, Plan1, Plan2, Plan3;
    @FXML
    public TableColumn<Driver,String> ColVards;
    @FXML
    public TableColumn<Driver,String> ColUzvards;
    @FXML
    public TableColumn<Driver,String> ColKods;
    @FXML
    public TableView DriverTable, TablePlan;
    public static Stage AddHoursstage;
    @FXML
    public void initialize(){
    ExecuteStatement.createTable(CreateStatement.CreateStatementForAllDriversTable());
    ExecuteStatement.createTable(CreateStatement.CreateStatementForPlaningTable());
    TablePlan.selectionModelProperty();

        ColVards.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColUzvards.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ColKods.setCellValueFactory(new PropertyValueFactory<>("code"));

        PlanVardsCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TablePlan.setItems(ExecuteStatement.ParsingDrivers());
        TablePlan.getSelectionModel().setCellSelectionEnabled(true);
        TablePlan.setEditable(true);

        initCellFactories();


        DriverTable.setItems(ExecuteStatement.ParsingDrivers());


        DriverTable.setRowFactory(item -> {
            TableRow<Driver> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Driver clickedRow = row.getItem();
                    HToday.setText(clickedRow.getName());
                    HMonth.setText(clickedRow.getName());
                    Shift.setText(clickedRow.getName());
                    TramD.setText(clickedRow.getName());
                    Day.setText(clickedRow.getName());}
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
        stage.initModality(Modality.WINDOW_MODAL);
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
        TablePlan.setItems(ExecuteStatement.ParsingDrivers());

    }

    public void showAddHoursPane() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        AddHoursstage = new Stage();
        Parent root = loader.load(getClass().getResource("../FXML/ui/AddHoursPlan.fxml"));
        AddHoursstage.setTitle("Add Hours");
        AddHoursstage.setMaxWidth(150);
        AddHoursstage.setResizable(false);
        AddHoursstage.setScene(new Scene(root));
        AddHoursstage.initModality(Modality.WINDOW_MODAL);
        AddHoursstage.initOwner(TablePlan.getScene().getWindow());
        AddHoursstage.show();


        AddHoursstage.setOnHiding(event -> {
            System.out.println("hidden");
        });

    }

    private void initCellFactories(){


        setCellFactory(Plan1);
        setCellFactory(Plan2);
        setCellFactory(Plan3);

    }

    public void setCellFactory(TableColumn<Driver,String> Column){

        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Edit");
        MenuItem item2 = new MenuItem("Clean");
        contextMenu.getItems().addAll(item1, item2);

        Column.setCellFactory(e-> {
            TableCell<Driver, String> cell = new TableCell<>();
            cell.setOnMouseClicked(event->{
                if(event.getClickCount()==2&&event.getButton()==MouseButton.PRIMARY) {
                    try {
                      //  System.out.println(PlanVardsCol.getCellData( cell.getIndex()));
                        showAddHoursPane();

                        AddHoursstage.setOnHiding(event1 -> {
                            if(AddHoursstage.getUserData()!=null){
                             Driver d =   (Driver)AddHoursstage.getUserData();
                               System.out.println(d.getName());
                            }
                                //cell.setText(AddHoursstage.getUserData().toString());

                        });// AddHoursstage.setUserData(null);
                    } catch (Exception ex) {
                        System.out.println("EXCE");
                    }
                } if (event.getButton()== MouseButton.SECONDARY) {
                    cell.setContextMenu(contextMenu);
                }
            });
            return  cell;
        });



    }
}
