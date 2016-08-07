package main;

import Objects.Driver;
import database.CreateStatement;
import database.CreateTable;
import database.GetConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Driver driver = new Driver();
        driver.setName("Janis");
        driver.setCode("42");
        CreateTable.createDriverTable(  CreateStatement.createStatementForDriverPersonalTable(driver));
        CreateTable.insert(CreateStatement.insertStatementForDriverPersonalTable(driver));
//        Parent root = FXMLLoader.load(getClass().getResource("../FXML/ui/Main.fxml"));
//        primaryStage.setTitle("Liepajas Tramvajs");
//        primaryStage.setScene(new Scene(root, 640, 480));
//        primaryStage.show();
//        primaryStage.setMinHeight(480);
//        primaryStage.setMinWidth(750);


    }


    public static void main(String[] args) {
        launch(args);
    }


}
