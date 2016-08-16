package Controllers;


import database.CreateStatement;
import database.ExecuteStatement;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Objects.Driver;
import javafx.util.Callback;

import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.scene.control.cell.TextFieldTableCell.*;


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
        TablePlan.setEditable(true);

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

        PlanVardsCol.setCellFactory(e-> {
                final TableCell<Driver, String> cell = new TableCell<>();
                cell.textProperty().bind(cell.itemProperty()); // in general might need to subclass TableCell and override updateItem(...) here
                cell.setOnMouseClicked(event -> System.out.println(cell.getText()));
                return cell ;
            });

        Plan1.setCellFactory(e-> {
            TableCell<Driver, String> cell = new TableCell<>();
            cell.setOnMouseClicked(event->{
                if(event.getClickCount()==2&&event.getButton()==MouseButton.PRIMARY) {
                    try {
                        showAddDriver();
                    } catch (Exception ex) {
                        System.out.println("EXCE");
                    }
                }
            });
            return  cell;
        });


        final ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("About");

        MenuItem item2 = new MenuItem("Preferences");

        contextMenu.getItems().addAll(item1, item2);


        Plan2.setCellFactory(e-> {
            TableCell<Driver, String> cell = new TableCell<>();
            cell.setOnMouseClicked(event -> {
                if (event.getButton()== MouseButton.SECONDARY) {
                    cell.setContextMenu(contextMenu);

                   // contextMenu.show(cell.getScene().getWindow());

               }
            });
            return cell;
        } );

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

    public void showAddDriver()throws Exception {
    FXMLLoader loader = new FXMLLoader();
    Stage stage = new Stage();
    Parent root = loader.load(getClass().getResource("../FXML/ui/LinkedToDriversUi/addDriver.fxml"));
    stage.setTitle("Add Driver");
    stage.setMinHeight(150);
    stage.setMinWidth(400);
    stage.setResizable(false);
    stage.setScene(new Scene(root));
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(TablePlan.getScene().getWindow());
    stage.show();
    stage.setOnHiding(event -> {
        updateDriversTable();
    });
}


}
