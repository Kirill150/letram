package Controllers.MainController;


import Objects.*;
import database.CreateStatement;
import database.ExecuteStatement;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.plaf.basic.BasicColorChooserUI;
import javax.swing.text.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.collections.FXCollections.observableArrayList;

public class Controller {

    @FXML
    public TableColumn<PlanningRecord, String> PlanVardsCol;
    @FXML
    public TableColumn<Driver, String> ColVards, ColUzvards, ColKods;
    @FXML
    public TableColumn<Tram, String> TramN,TramId,Color;
    @FXML
    private  TableView DriverTable, TablePlan, TramTable;

    ContextMenu cMenu = new ContextMenu();
    MenuItem item1 = new MenuItem("Edit");
    MenuItem item2 = new MenuItem("Delete");
    Map<String, String> userData;
    public static Stage AddHoursStage;

    @FXML
    public void initialize() {


        createTables();
        initializePlanningTable();
        initDriversTable();
        initTramTable();

    }

    // Creates tables for drivers, trams, planning records
    private void createTables(){

        ExecuteStatement.createTable(CreateStatement.CreateStatementForAllDriversTable());
        ExecuteStatement.createTable(CreateStatement.CreateStatementForPlaningTable());
        ExecuteStatement.createTable(CreateStatement.createTramTable());
    }
    // Initializes planning table, sets item to this, and double click event handler
    private void initializePlanningTable() {
        PlanVardsCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        PlanVardsCol.setSortable(false);

        TablePlan.getColumns().forEach(item -> {
            TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;
            initCellFactory(col);
        });

        TablePlan.setItems(ExecuteStatement.test());
        TablePlan.getSelectionModel().setCellSelectionEnabled(true);
        TablePlan.setEditable(true);

    }
    // Initializes Drivers table
    private void initDriversTable(){

        ColVards.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColUzvards.setCellValueFactory(new PropertyValueFactory<>("surname"));
        DriverTable.setItems(ExecuteStatement.ParsingDrivers());
        ColKods.setCellValueFactory(new PropertyValueFactory<>("code"));
        ColKods.setCellFactory(column -> {
            return new TableCell<Driver, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);


                    setText(item);
                }
            };
        });


        DriverTable.getSelectionModel().setCellSelectionEnabled(true);
        DriverTable.setEditable(true);
        DriverTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TableView c = (TableView) event.getSource();

                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                    try {
                        TablePosition pos = (TablePosition) c.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();

                        Driver item = (Driver) c.getItems().get(row);
                        TableColumn col = pos.getTableColumn();
                        AddDriver();

                    } catch (NullPointerException exx) {
                        System.out.println("empty cell");
                    } catch (IndexOutOfBoundsException indExc) {
                        System.out.println("empty row");
                    }
                }

            }
        });

    }

    private void initTramTable(){
    TramN.setCellValueFactory(new PropertyValueFactory<>("Number"));
    TramId.setCellValueFactory(new PropertyValueFactory<>("id"));
    Color.setCellValueFactory(new PropertyValueFactory<>("color"));
    TramTable.setItems(ExecuteStatement.parsingTrams());

    TramTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TableView c = (TableView) event.getSource();

                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                    try {
                        TablePosition pos = (TablePosition) c.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();

                        Tram item = (Tram) c.getItems().get(row);

                        TableColumn col = pos.getTableColumn();
                        EditTram();

                    } catch (NullPointerException exx) {
                        System.out.println("empty cell");
                    } catch (IndexOutOfBoundsException indExc) {
                        System.out.println("empty row");
                    }
                }

            }
        });
      //  javafx.scene.paint.Color;

                    TramTable.setRowFactory(row -> new TableRow<Tram>() {
                        @Override
                        public void updateItem(Tram item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item == null || empty)
                                setStyle("");
                            else {
                                item.getColor().toUpperCase();
                                for(int i = 0; i < TramColors.values().length; i++){
                                    if(TramColors.values()[i].toString().equals(item.getColor().toUpperCase())){
                                        this.setStyle("-fx-text-background-color:"+item.getColor());

                                    }

                                }
                            }
                        }
                    });}

    public void EditDriver(ActionEvent actionEvent) throws Exception {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../FXML/ui/LinkedToDriversUi/editDriver.fxml"));
        stage.setTitle("Edit Driver");
        stage.setMinHeight(250);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> {
            updateDriversTable();
            updatePlanningTable();
        });
    }

    public void DeleteDriver(ActionEvent actionEvent) throws Exception {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../FXML/ui/LinkedToDriversUi/deleteDriver.fxml"));
        stage.setTitle("Delete Driver");
        stage.setMinHeight(100);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> {
            updateDriversTable();
            updatePlanningTable();
        });
    }

    public void AddDriver(ActionEvent actionEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        Parent root = loader.load(getClass().getResource("../../FXML/ui/LinkedToDriversUi/addDriver.fxml"));
        stage.setTitle("Add Driver");
        stage.setMinHeight(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> {updateDriversTable();;updatePlanningTable();});

    }

    public void AddDriver(){

        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        try {
        Parent root = loader.load(getClass().getResource("../../FXML/ui/LinkedToDriversUi/addDriver.fxml"));
        stage.setTitle("Add Driver");
        stage.setMinHeight(150);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(DriverTable.getScene().getWindow());
        stage.show();
        }catch(IOException e){
            System.out.println("addDriver.fxml not found");
        }
        stage.setOnHiding(event ->{updateDriversTable();updatePlanningTable();});

    }

    public void AddTram(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../FXML/ui/LinkedToTramsUi/addTram.fxml"));
        stage.setTitle("Add Tram");
        stage.setMinHeight(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> updateTramTable());

    }

    public void AddTram(){

        Stage stage = new Stage();
        try{
        Parent root = FXMLLoader.load(getClass().getResource("../../FXML/ui/LinkedToTramsUi/addTram.fxml"));
        stage.setTitle("Add Tram");
        stage.setMinHeight(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(TramTable.getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> updateTramTable());
        }catch(IOException e){
            System.out.println("addTram.fxml not found");
        }

    }

    public void EditTram(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../FXML/ui/LinkedToTramsUi/editTram.fxml"));
        stage.setTitle("Edit Tram");
        stage.setMinHeight(250);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> updateTramTable());
    }

    public void EditTram (){
        Stage stage = new Stage();
        try{
        Parent root = FXMLLoader.load(getClass().getResource("../../FXML/ui/LinkedToTramsUi/editTram.fxml"));
        stage.setTitle("Edit Tram");
        stage.setMinHeight(250);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(TramTable.getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> updateTramTable());
        }catch(IOException e){
            System.out.println("addTram.fxml not found");
        }
    }

    public void DeleteTram(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../FXML/ui/LinkedToTramsUi/deleteTram.fxml"));
        stage.setTitle("Delete Tram");
        stage.setMinHeight(100);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> updateTramTable());
    }

    public void PlanAdd(ActionEvent actionEvent) throws IOException {

        ExecuteStatement.test();
    }

    private void updateDriversTable() {

        DriverTable.setItems(ExecuteStatement.ParsingDrivers());


    }

    private void updateTramTable(){
        TramTable.setItems(ExecuteStatement.parsingTrams());


    }

    public  void updatePlanningTable(){

        TablePlan.setItems(ExecuteStatement.test());
    }

    public void initCellFactory(TableColumn<PlanningRecord,String> col){
        if(!col.getText().equals("Vards")) {
            col.setSortable(false);
            col.setCellValueFactory(e -> {
                PlanningRecord p = e.getValue();
                if(p.getHoursPerDaymap().containsKey(col.getText())) {
                    return new ReadOnlyObjectWrapper(p.getHoursPerDaymap().get(col.getText()).getHours());
                }
                else return new ReadOnlyObjectWrapper("");
            });

            col.setCellFactory(column -> {
                return new TableCell<PlanningRecord, String>() {
                    TableCell cell = this;
                    @Override
                    protected void updateItem(String item, boolean empty) {

                        super.updateItem(item, empty);

                        this.setOnMouseClicked(event -> {
                            if (event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2) {
                                userData = new HashMap<>();
                                if(cell.getTableRow()!=null) {
                                    PlanningRecord oldRecord = (PlanningRecord) cell.getTableRow().getItem();
                                        userData.put("day", col.getText());
                                        userData.put("driverId", oldRecord.getDriverId());
                                   ;
                                        if(oldRecord.getHoursPerDaymap().containsKey(col.getText())) {
                                            userData.put("hours", oldRecord.getHoursPerDaymap().get(col.getText()).getHours());
                                            userData.put("shift", oldRecord.getHoursPerDaymap().get(col.getText()).getShift());
                                            userData.put("tramId", oldRecord.getHoursPerDaymap().get(col.getText()).getTramId());

                                    }
                                }
                               if(item==null){
                                   userData.remove("hours");
                                   userData.remove("shift");
                                   userData.remove("tramId");
                               }
                                showAddHoursPane();

                            }
                            if(event.getButton()==MouseButton.SECONDARY){

                                if(cell.getTableRow()!=null) {
                                    PlanningRecord oldRecord = (PlanningRecord) cell.getTableRow().getItem();
                                    userData = new HashMap<>();
                                    userData.put("day", col.getText());
                                    userData.put("driverId", oldRecord.getDriverId());
                                    if(oldRecord.getHoursPerDaymap().containsKey(col.getText())) {
                                        System.out.println(col.getText());
                                        userData.put("hours", oldRecord.getHoursPerDaymap().get(col.getText()).getHours());
                                        userData.put("shift", oldRecord.getHoursPerDaymap().get(col.getText()).getShift());
                                        userData.put("tramId", oldRecord.getHoursPerDaymap().get(col.getText()).getTramId());
                                    }
                                }
                                if(item==null)userData=null;
                                item1.setOnAction(action->showAddHoursPane());
                                cMenu.getItems().add(item1);
                                cMenu.getItems().add(item2);
                                cell.setContextMenu(cMenu);
                            }
                        });
                    cell.setText(item);
                    }
                };
            });
        }
    }

    public void showAddHoursPane() {

            FXMLLoader loader = new FXMLLoader();
            AddHoursStage = new Stage();
        AddHoursStage.setUserData(userData);
        AddHoursStage.setOnHiding(hidingEvent->updatePlanningTable());
            try {
                Parent root = loader.load(getClass().getResource("../../FXML/ui/AddHoursPlan.fxml"));
                AddHoursStage.setTitle("Add Hours");
                AddHoursStage.setMaxWidth(150);
                AddHoursStage.setResizable(false);
                AddHoursStage.setScene(new Scene(root));
                AddHoursStage.initModality(Modality.WINDOW_MODAL);
                AddHoursStage.initOwner(TablePlan.getScene().getWindow());
                AddHoursStage.show();
            } catch (Exception exc) {
                System.out.println(exc.getClass());
            }

    }


}