package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Liepajas Tramvajs");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
        primaryStage.setMinHeight(480);
        primaryStage.setMinWidth(750);

    }


    public static void main(String[] args) {
        launch(args);
    }


}
