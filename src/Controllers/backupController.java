//package Controllers;
//
//
//import Objects.PlanningRecord;
//import database.CreateStatement;
//import database.ExecuteStatement;
//import javafx.beans.property.ReadOnlyObjectWrapper;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import Objects.Driver;
//import javafx.util.Callback;
//
//import java.io.IOException;
//
//import static javafx.collections.FXCollections.observableArrayList;
//
//
//public class Controller {
//
//    @FXML
//    private TextField HToday, HMonth, Shift, TramD, Day;
//    @FXML
//    public TableColumn<PlanningRecord,String> PlanVardsCol, Plan1, Plan2, Plan3;
//    @FXML
//    public TableColumn<Driver,String> ColVards;
//    @FXML
//    public TableColumn<Driver,String> ColUzvards;
//    @FXML
//    public TableColumn<Driver,String> ColKods;
//    @FXML
//    public TableView DriverTable, TablePlan;
//    public static Stage AddHoursStage;
//    @FXML
//    public void initialize(){
//
//        ContextMenu contextMenu = new ContextMenu();
//        MenuItem item1 = new MenuItem("Edit");
//        item1.setOnAction(event ->  System.out.println("Edit"));
//        MenuItem item2 = new MenuItem("Clean");
//        item2.setOnAction(event ->  System.out.println("Clean"));
//        contextMenu.getItems().addAll(item1, item2);
//
//
//
//        ExecuteStatement.createTable(CreateStatement.CreateStatementForAllDriversTable());
//        ExecuteStatement.createTable(CreateStatement.CreateStatementForPlaningTable());
//
//        TablePlan.selectionModelProperty();
//
//        ColVards.setCellValueFactory(new PropertyValueFactory<>("name"));
//        ColUzvards.setCellValueFactory(new PropertyValueFactory<>("surname"));
//        ColKods.setCellValueFactory(new PropertyValueFactory<>("code"));
//
//
//
//        PlanVardsCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
////        Plan1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlanningRecord, String>, ObservableValue<String>>() {
////            public ObservableValue<String> call(TableColumn.CellDataFeatures<PlanningRecord, String> p) {
////                return new ReadOnlyObjectWrapper(((PlanningRecord)p.getValue()).getMap().get("1"));
////            }
////        });
////        Plan2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlanningRecord, String>, ObservableValue<String>>() {
////            public ObservableValue<String> call(TableColumn.CellDataFeatures<PlanningRecord, String> p) {
////                return new ReadOnlyObjectWrapper(((PlanningRecord)p.getValue()).getMap().get("2"));
////            }
////        });
//
//
//        Plan1.setCellValueFactory(e->{
//            return new ReadOnlyObjectWrapper(((PlanningRecord)e.getValue()).getMap().get("1"));
//        });
//
//        Plan2.setCellValueFactory(e->{
//            return new ReadOnlyObjectWrapper(((PlanningRecord)e.getValue()).getMap().get("2"));
//        });
//
//        Plan3.setCellValueFactory(e->{
//            return new ReadOnlyObjectWrapper(((PlanningRecord)e.getValue()).getMap().get("3"));
//        });
//
//        TablePlan.setRowFactory(r->{
//            TableRow<Driver> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if(event.getButton()==MouseButton.SECONDARY){
//                    System.out.println("zalupu");
//                    row.setContextMenu(contextMenu);
//                }
//            });
//            return row;
//        });
//
//        // Plan1.setCellValueFactory(new PropertyValueFactory<>("driverId"));
//        // Plan2.setCellValueFactory(new PropertyValueFactory<>("hours"));
//        //  Plan3.setCellValueFactory(new PropertyValueFactory<>("hours"));
//
//
//
//
//        TablePlan.setItems(ExecuteStatement.test());
//        TablePlan.getSelectionModel().setCellSelectionEnabled(true);
//        TablePlan.setEditable(true);
//
//
//
//
//
//
//        // initAddHoursStage();
//
//        TablePlan.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                TableView c = (TableView) event.getSource();
//                String colName = ((TableView) event.getSource()).getFocusModel().getFocusedCell().getTableColumn().getText();
//                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY && !colName.equals("Vards")) {
//                    try {
//                        TablePosition pos = (TablePosition) c.getSelectionModel().getSelectedCells().get(0);
//                        int row = pos.getRow();
//
//                        PlanningRecord item = (PlanningRecord) c.getItems().get(row);
//
//                        TableColumn col = pos.getTableColumn();
//
//                        showAddHoursPane();
//                        AddHoursStage.setOnHiding(event1 -> {
//                            if (AddHoursStage.getUserData() != null) {
//                                PlanningRecord record = (PlanningRecord) AddHoursStage.getUserData();
//                                record.setDriverId(item.getDriverId());
//                                record.setDatums(col.getText());
//                                ExecuteStatement.insert(CreateStatement.insertStatementForPlaningRecord(record));
//                                updateDriversTable();
//                            }
//                        });
//
//                    } catch (NullPointerException exx) {
//                        System.out.println("empty cell");
//                    } catch (IndexOutOfBoundsException indExc) {
//                        System.out.println("empty row");
//                    }
//                }
//            }
//        });
//
//        // initCellFactories();
//
//        DriverTable.setItems(ExecuteStatement.ParsingDrivers());
//
//
//        DriverTable.setRowFactory(item -> {
//            TableRow<Driver> row = new TableRow<>();
//            row.setOnMouseClicked(event2 -> {
//                if (!row.isEmpty()) {
//                    Driver clickedRow = row.getItem();
//                    HToday.setText(clickedRow.getName());
//                    HMonth.setText(clickedRow.getName());
//                    Shift.setText(clickedRow.getName());
//                    TramD.setText(clickedRow.getName());
//                    Day.setText(clickedRow.getName());
//                }
//            });
//            return row;
//        });
//
//
//    }
//
//    public void EditDriver(ActionEvent actionEvent) throws Exception{
//
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToDriversUi/editDriver.fxml"));
//        stage.setTitle("Edit Driver");
//        stage.setMinHeight(250);
//        stage.setMinWidth(150);
//        stage.setResizable(false);
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//        stage.show();
//        stage.setOnHiding(event -> {
//            updateDriversTable();
//        });
//    }
//
//    public void DeleteDriver(ActionEvent actionEvent) throws Exception {
//
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToDriversUi/deleteDriver.fxml"));
//        stage.setTitle("Delete Driver");
//        stage.setMinHeight(100);
//        stage.setMinWidth(150);
//        stage.setResizable(false);
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//        stage.show();
//        stage.setOnHiding(event -> {
//            updateDriversTable();
//        });
//    }
//
//    public void AddDriver(ActionEvent actionEvent) throws Exception {
//
//        FXMLLoader loader = new FXMLLoader();
//        Stage stage = new Stage();
//        Parent root = loader.load(getClass().getResource("../FXML/ui/LinkedToDriversUi/addDriver.fxml"));
//        stage.setTitle("Add Driver");
//        stage.setMinHeight(150);
//        stage.setMinWidth(400);
//        stage.setResizable(false);
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//        stage.show();
//        stage.setOnHiding(event -> {
//            updateDriversTable();
//        });
//    }
//
//    public void AddTram(ActionEvent actionEvent) throws IOException {
//
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/addTram.fxml"));
//        stage.setTitle("Add Tram");
//        stage.setMinHeight(150);
//        stage.setMinWidth(400);
//        stage.setResizable(false);
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//        stage.show();
//
//
//    }
//
//    public void EditTram(ActionEvent actionEvent) throws IOException {
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/editTram.fxml"));
//        stage.setTitle("Edit Tram");
//        stage.setMinHeight(250);
//        stage.setMinWidth(150);
//        stage.setResizable(false);
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//        stage.show();
//    }
//
//    public void DeleteTram(ActionEvent actionEvent) throws IOException {
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/deleteTram.fxml"));
//        stage.setTitle("Delete Tram");
//        stage.setMinHeight(100);
//        stage.setMinWidth(150);
//        stage.setResizable(false);
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//        stage.show();
//    }
//
//    public void PlanAdd(ActionEvent actionEvent) throws IOException {
//
//        ExecuteStatement.test();
//    }
//
//
//
//
////        Stage stage = new Stage();
////        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/LinkedToTramsUi/addTram.fxml"));
////        stage.setTitle("Add Tram");
////        stage.setMinHeight(150);
////        stage.setMinWidth(400);
////        stage.setResizable(false);
////        stage.setScene(new Scene(root));
////        stage.initModality(Modality.WINDOW_MODAL);
////        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
////        stage.show();
//
//
//    //   }
//
//    public void updateDriversTable(){
//
//        DriverTable.setItems(ExecuteStatement.ParsingDrivers());
//        TablePlan.setItems(ExecuteStatement.ParsingDriversForPlanningTable());
//
//    }
//
//
//    public void showAddHoursPane(){
//        FXMLLoader loader = new FXMLLoader();
//        AddHoursStage = new Stage();
//
//        try {
//            Parent  root = loader.load(getClass().getResource("../FXML/ui/AddHoursPlan.fxml"));
//            AddHoursStage.setTitle("Add Hours");
//            AddHoursStage.setMaxWidth(150);
//            AddHoursStage.setResizable(false);
//            AddHoursStage.setScene(new Scene(root));
//            AddHoursStage.initModality(Modality.WINDOW_MODAL);
//            AddHoursStage.initOwner(TablePlan.getScene().getWindow());
//
//            AddHoursStage.show();
//        }catch(Exception exce){}
//    }
//
////    private void initCellFactories(){
////
////
//////        setCellFactory(Plan1);
////
//////        setCellFactory(Plan2);
//////        setCellFactory(Plan3);
////
////
////    }
//
////    public void setCellFactory(TableColumn<PlanningRecord,String> Column){
////
////        ContextMenu contextMenu = new ContextMenu();
////        MenuItem item1 = new MenuItem("Edit");
////        MenuItem item2 = new MenuItem("Clean");
////        contextMenu.getItems().addAll(item1, item2);
////
////        System.out.println(Column.getText()+"col");
////
////
////        Column.setCellFactory(e-> {
////
////            TableCell<PlanningRecord, String> cell = new TableCell<>();
////
////            try {
////                initAddHoursStage(cell, Column);
////            }catch (Exception exc){System.out.println("EXCEptionZalupa");}
////
////            cell.setOnMouseClicked(event->{
////                if(event.getClickCount()==2&&event.getButton()==MouseButton.PRIMARY&&PlanVardsCol.getCellData( cell.getIndex())!=null) {
////                    try {
////                        showAddHoursPane();
////                        AddHoursStage.setOnHiding(event1 -> {
////                            if(AddHoursStage.getUserData()!=null){
////                                PlanningRecord record =   (PlanningRecord) AddHoursStage.getUserData();
////                                record.setDriverId(PlanVardsCol.getCellData( cell.getIndex()));
////                                record.setDatums(Column.getText());
////                                ExecuteStatement.insert(CreateStatement.insertStatementForPlaningRecord(record));
////                            }
////                        });
////                    } catch (Exception ex) {
////                        System.out.println("EXCE");
////                    }
////                } if (event.getButton()== MouseButton.SECONDARY&&PlanVardsCol.getCellData( cell.getIndex())!=null) {
////                    System.out.println(cell.getIndex());
////                    cell.setContextMenu(contextMenu);
////                }
////            });
////
////                return  cell;
////        });
////
////
////
////    }
//
////    public void initAddHoursStage(){
////        FXMLLoader loader = new FXMLLoader();
////        AddHoursStage = new Stage();
////        try {
////            Parent  root = loader.load(getClass().getResource("../FXML/ui/AddHoursPlan.fxml"));
////            AddHoursStage.setTitle("Add Hours");
////            AddHoursStage.setMaxWidth(150);
////            AddHoursStage.setResizable(false);
////            AddHoursStage.setScene(new Scene(root));
////            AddHoursStage.initModality(Modality.WINDOW_MODAL);
////            System.out.println("tut");
////            AddHoursStage.initOwner(TablePlan.getScene().getWindow());
////            System.out.println("tut2");
////        }catch(Exception exce){System.out.println("initAddHoursStageError");
////            System.out.println(exce.getClass());
////            System.out.println(exce.getStackTrace().toString());
////        }
////    }
//
//
//}
