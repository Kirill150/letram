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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public  class Controller {

    @FXML
    public TableColumn<PlanningRecord, String> PlanVardsCol, PlanKopa, SickCol, DayH,NightH;
    @FXML
    public TableColumn<Driver, String> ColVards, ColUzvards, ColKods;
    @FXML
    public TableColumn<Tram, String> TramN,TramId,Color;
    @FXML
    private  TableView DriverTable, TablePlan, TramTable;
    @FXML
    public ComboBox<String> PlanMonth, MainMonth;
    @FXML
    public ComboBox<Integer>  PlanYear, MainDay, MainYear;
    @FXML
    private CheckBox ShowSick, ShowKopa, ShowDayH, ShowNightH;

    private MothMapper mapper = new MothMapper();
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

        monthStirngToValue = new MothMapper();

     //  "-fx-background-color:red"

        createTables();
        initPlanDate();
        initializePlanningTable();
        initDriversTable();
        initTramTable();
        initializeMonthsAndYear();
        customizeColumns();




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

        TablePlan.setItems(planningRepo.getRecords(mapper.getMonthIntValue(PlanMonth.getValue()), PlanYear.getValue()));
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

    private void customizeColumns(){

        PlanKopa.setVisible(false);
        SickCol.setVisible(false);
        DayH.setVisible(false);
        NightH.setVisible(false);
        ShowKopa.setOnAction(event->{
            if(ShowKopa.isSelected()){
                PlanKopa.setVisible(true);
            }else{
                PlanKopa.setVisible(false);
            }
        });

        ShowSick.setOnAction(event->{
            if(ShowSick.isSelected()){
                SickCol.setVisible(true);
            }else{
                SickCol.setVisible(false);
            }
        });


        ShowDayH.setOnAction(event->{
            if(ShowDayH.isSelected()){
                DayH.setVisible(true);
            }else{
                DayH.setVisible(false);
            }
        });

        ShowNightH.setOnAction(event->{
            if(ShowNightH.isSelected()){
                NightH.setVisible(true);
            }else{
                NightH.setVisible(false);
            }
        });

    }

    private void initPlanDate(){

        PlanMonth.setValue(mapper.getMonthStringValue(planDateRepo.selectMonth()));
        PlanYear.setValue(planDateRepo.selectYear());
        PlanShow();

    }

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

        TablePlan.setItems(planningRepo.getRecords(mapper.getMonthIntValue(PlanMonth.getValue()),PlanYear.getValue()));
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

                        setStyle(col.getStyle());

                        this.setOnMouseClicked(event -> {
                            if (event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2) {
                                userData = new HashMap<>();
                                if(cell.getTableRow()!=null) {
                                    PlanningRecord oldRecord = (PlanningRecord) cell.getTableRow().getItem();

                                        userData.put("day", col.getText());
                                        userData.put("driverId", oldRecord.getDriverId());
                                        userData.put("month", PlanMonth.getValue());
                                        userData.put("year", PlanYear.getValue().toString());
                                        userData.put("holiday", col.getUserData().toString());

                                        if(oldRecord.getHoursPerDaymap().containsKey(col.getText())&&item!=null) {
                                            userData.put("hours", oldRecord.getHoursPerDaymap().get(col.getText()).getHours());
                                            userData.put("shift", oldRecord.getHoursPerDaymap().get(col.getText()).getShift());
                                            userData.put("tramId", oldRecord.getHoursPerDaymap().get(col.getText()).getTramId());

                                    }
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

    public void PlanShow() {

        planDateRepo.insert(monthStirngToValue.getMonthIntValue(PlanMonth.getValue()), PlanYear.getValue());
        LocalDate date = LocalDate.of(PlanYear.getValue(),mapper.getMonthIntValue(PlanMonth.getValue()),Integer.valueOf(1));
        int month =  date.lengthOfMonth() ;
        TableColumn<PlanningRecord, String> col31 =( TableColumn<PlanningRecord, String>) TablePlan.getColumns().get(31);
        TableColumn<PlanningRecord, String> col30 =( TableColumn<PlanningRecord, String>) TablePlan.getColumns().get(30);
        TableColumn<PlanningRecord, String> col29 =( TableColumn<PlanningRecord, String>) TablePlan.getColumns().get(29);

        switch (month){
            case 31 :
                col30.setVisible(true);
                col29.setVisible(true);
                col31.setVisible(true);
                TablePlan.getColumns().subList(1,32).forEach(item -> {

                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;
                    markWeekends(col);
                });
                break;

            case 30 :
                col30.setVisible(true);
                col29.setVisible(true);
                col31.setVisible(false);
                TablePlan.getColumns().subList(1,31).forEach(item -> {
                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;
                    markWeekends(col);
                });
                break;

            case 29 :
                col30.setVisible(false);
                col29.setVisible(true);
                col31.setVisible(false);
                TablePlan.getColumns().subList(1,30).forEach(item -> {
                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;
                    markWeekends(col);
                });
                break;

            case 28 :
                col30.setVisible(false);
                col29.setVisible(false);
                col31.setVisible(false);
                TablePlan.getColumns().subList(1,29).forEach(item -> {
                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;
                    markWeekends(col);
                });
        }

    }

    public void PlanShow(ActionEvent actionEvent) {

        planDateRepo.insert(monthStirngToValue.getMonthIntValue(PlanMonth.getValue()), PlanYear.getValue());
        LocalDate date = LocalDate.of(PlanYear.getValue(),mapper.getMonthIntValue(PlanMonth.getValue()),Integer.valueOf(1));
        int month =  date.lengthOfMonth() ;
        TableColumn<PlanningRecord, String> col31 =( TableColumn<PlanningRecord, String>) TablePlan.getColumns().get(31);
        TableColumn<PlanningRecord, String> col30 =( TableColumn<PlanningRecord, String>) TablePlan.getColumns().get(30);
        TableColumn<PlanningRecord, String> col29 =( TableColumn<PlanningRecord, String>) TablePlan.getColumns().get(29);

        switch (month){
            case 31 :
                col30.setVisible(true);
                col29.setVisible(true);
                col31.setVisible(true);
                TablePlan.getColumns().subList(1,32).forEach(item -> {

                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;
                    System.out.println(col.getText());
                    markWeekends(col);
                });
                break;

            case 30 :
                col30.setVisible(true);
                col29.setVisible(true);
                col31.setVisible(false);
                TablePlan.getColumns().subList(1,31).forEach(item -> {
                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;

                    markWeekends(col);
                });
                break;

            case 29 :
                col30.setVisible(false);
                col29.setVisible(true);
                col31.setVisible(false);
                TablePlan.getColumns().subList(1,30).forEach(item -> {
                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;

                    markWeekends(col);
                });
                break;

            case 28 :
                col30.setVisible(false);
                col29.setVisible(false);
                col31.setVisible(false);
                TablePlan.getColumns().subList(1,29).forEach(item -> {
                    TableColumn<PlanningRecord, String> col = (TableColumn<PlanningRecord, String>) item;

                    markWeekends(col);
                });
        }
     updatePlanningTable();
    }

    private boolean isHoliday(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
        List<String> svetki = Arrays.asList("18-11", "24-12", "25-12", "26-12", "01-01", "31-12", "01-05", "04-05",
                "24-06", "23-06");

        if(date.getDayOfWeek().getValue()==6 || date.getDayOfWeek().getValue()==7 ){
            return true;
        } else if(svetki.contains(date.format(formatter))) {
            return true;
        }
        return false;
    }

    private void markWeekends(TableColumn<PlanningRecord,String> col){

        LocalDate date = LocalDate.of(PlanYear.getValue(), mapper.getMonthIntValue(PlanMonth.getValue()),Integer.parseInt(col.getText()));
        col.setUserData(date.getDayOfWeek().toString());
        if(isHoliday(date)==true){

            col.setStyle(" -fx-background-color: lightGray;  -fx-border-color:grey; -fx-border-width: 1 1 1 1");
            initCellFactory(col);

        } else {
            col.setStyle(null);
            initCellFactory(col);
        }
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