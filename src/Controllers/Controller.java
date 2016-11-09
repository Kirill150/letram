package Controllers;

import Objects.*;
import database.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Controller {

    @FXML
    public TableColumn<PlanningRecord, String> PlanVardsCol, PlanKopa;
    @FXML
    public TableColumn<Driver, String> ColVards, ColUzvards, ColKods;
    @FXML
    public TableColumn<Tram, String> TramN,TramId,Color;
    @FXML
    private  TableView DriverTable, TablePlan, TramTable;
    @FXML
    private ComboBox<String> PlanMonth, MainMonth;
    @FXML
    private ComboBox<Integer>  PlanYear, MainDay, MainYear;

    private PlanningRepo planningRepo = new PlanningRepo();
    private DriverRepo driverRepo = new DriverRepo();
    private TramRepo tramRepo = new TramRepo();
    private PlanDateRepo planDateRepo = new PlanDateRepo();
    private MainDateRepo mainDateRepo = new MainDateRepo();

    ContextMenu cMenu = new ContextMenu();
    MenuItem item2 = new MenuItem("Delete");
    MenuItem item3 = new MenuItem("Add hours for another tram");
    MenuItem item4 = new MenuItem("More info about this record");
    Map<String, String> userData;
    public static Stage AddHoursStage;
    public static Tram tramForDoubleClickEdit;
    MothMapper monthStirngToValue;
    @FXML
    public void initialize() {


        ColVards.setVisible(false);

        monthStirngToValue = new MothMapper();

        createTables();
        initializePlanningTable();
        initDriversTable();
        initTramTable();
        initializeMonthsAndYear();

    }

    // Creates tables
    private void createTables(){

        CreateTables createTables = new CreateTables();
        createTables.createTables();

    }
    // Initializes planning table, sets item to this, and double click event handler
    private void initializePlanningTable() {
        PlanVardsCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        PlanKopa.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        PlanVardsCol.setSortable(false);

        TablePlan.getColumns().forEach(item -> {
            TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;
            initCellFactory(col);
        });

        TablePlan.setItems(planningRepo.getRecords());
        TablePlan.getSelectionModel().setCellSelectionEnabled(true);
        TablePlan.setEditable(true);

    }
    // Initializes Drivers table
    private void initDriversTable(){

        ColVards.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColUzvards.setCellValueFactory(new PropertyValueFactory<>("surname"));
        DriverTable.setItems(driverRepo.getDrivers());
        ColKods.setCellValueFactory(new PropertyValueFactory<>("code"));
        DriverTable.setEditable(true);
        DriverTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TableView c = (TableView) event.getSource();

                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                    try {

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
    TramTable.setItems(tramRepo.getTrams());



    TramTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TableView c = (TableView) event.getSource();

                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                    try {
                        TablePosition pos = (TablePosition) c.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();

                        tramForDoubleClickEdit = (Tram) c.getItems().get(row);

                        EditTram();

                    } catch (NullPointerException exx) {
                        System.out.println("empty cell");
                    } catch (IndexOutOfBoundsException indExc) {
                        System.out.println("empty row");
                    }
                }

            }
        });

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
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/ui/LinkedToDriversUi/editDriver.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/ui/LinkedToDriversUi/deleteDriver.fxml"));
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
        Parent root = loader.load(getClass().getResource("/Controllers/ui/LinkedToDriversUi/addDriver.fxml"));
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
        Parent root = loader.load(getClass().getResource("/Controllers/ui/LinkedToDriversUi/addDriver.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/ui/LinkedToTramsUi/addTram.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/ui/LinkedToTramsUi/addTram.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/ui/LinkedToTramsUi/editTram.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/ui/LinkedToTramsUi/editTramOnDoubleClick.fxml"));
        stage.setTitle("Edit Tram");
        stage.setMinHeight(250);
        stage.setMinWidth(150);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(TramTable.getScene().getWindow());
        stage.show();
        stage.setOnHiding(event -> {
            updateTramTable();
            tramForDoubleClickEdit = null;
            }
        );
        }catch(IOException e){
            System.out.println("addTram.fxml not found");
        }
    }

    public void DeleteTram(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/ui/LinkedToTramsUi/deleteTram.fxml"));
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

    private void updateDriversTable() {

        DriverTable.setItems(driverRepo.getDrivers());


    }

    private void updateTramTable(){
        TramTable.setItems(tramRepo.getTrams());


    }

    public  void updatePlanningTable(){

        TablePlan.setItems(planningRepo.getRecords());
    }

    public void initCellFactory(TableColumn<PlanningRecord,String> col){
        if(!col.getText().equals("Vards")&&!col.getText().equals("Kopa")) {
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
                                        userData.put("month", PlanMonth.getValue());
                                        userData.put("year", PlanYear.getValue().toString());

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
                                cMenu.getItems().add(item2);
                                cMenu.getItems().add(item3);
                                cMenu.getItems().add(item4);
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
                Parent root = loader.load(getClass().getResource("/Controllers/ui/AddHoursPlan.fxml"));
                AddHoursStage.setTitle("Add Hours");
                AddHoursStage.setResizable(false);
                AddHoursStage.setScene(new Scene(root));
                AddHoursStage.initModality(Modality.WINDOW_MODAL);
                AddHoursStage.initOwner(TablePlan.getScene().getWindow());
                AddHoursStage.show();
            } catch (Exception exc) {
                System.out.println(exc.getClass());
            }

    }

    public void PlanShow(ActionEvent actionEvent) {

        planDateRepo.insert(monthStirngToValue.getMonthIntValue(PlanMonth.getValue()), PlanYear.getValue());

    }

    public void MainShow(ActionEvent actionEvent) {

       mainDateRepo.insert(MainDay.getValue(),monthStirngToValue.getMonthIntValue(PlanMonth.getValue()), PlanYear.getValue());

    }

    public void initializeMonthsAndYear(){

        List<String> PlanMonths = new LinkedList<>();
        PlanMonths.add("January");
        PlanMonths.add("February");
        PlanMonths.add("March");
        PlanMonths.add("April");
        PlanMonths.add("May");
        PlanMonths.add("June");
        PlanMonths.add("July");
        PlanMonths.add("August");
        PlanMonths.add("September");
        PlanMonths.add("October");
        PlanMonths.add("November");
        PlanMonths.add("December");

        PlanMonth.setItems(FXCollections.observableList(PlanMonths));
        MainMonth.setItems(FXCollections.observableList(PlanMonths));

        List<Integer> PlanYears = new LinkedList<>();
        PlanYears.add(2016);
        PlanYears.add(2017);
        PlanYears.add(2018);
        PlanYears.add(2019);
        PlanYears.add(2020);
        PlanYears.add(2021);
        PlanYears.add(2022);
        PlanYears.add(2023);
        PlanYears.add(2024);
        PlanYears.add(2025);
        PlanYears.add(2026);
        PlanYears.add(2027);

        PlanYear.setItems(FXCollections.observableList(PlanYears));
        MainYear.setItems(FXCollections.observableList(PlanYears));



    }
}