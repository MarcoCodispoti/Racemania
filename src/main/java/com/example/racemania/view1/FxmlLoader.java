package com.example.racemania.view1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FxmlLoader extends Application {
    private static Stage stage1;

    @Override
    public void start(Stage primaryStage) throws IOException{
        Stage stage;
        FXMLLoader fxmlloader = new FXMLLoader(FxmlLoader.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1200, 740);
        stage = primaryStage;
        stage.setTitle("Racemania");
        stage.setResizable(false);
        stage.setScene(scene);
        setScene(stage);
        stage.show();

    }

    private static void setScene(Stage stage) {
        stage1 = stage;
    }

    public static void setPage(String fileName) {
        URL fileUrl = FxmlLoader.class.getResource(fileName + ".fxml");
        FXMLLoader loader = new FXMLLoader(fileUrl);
        assert fileUrl != null;
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 740);
            stage1.setScene(scene);
        } catch (IOException e) {
            //not handled
        }
    }

    public static <T> T setPageAndReturnController(String fileName) {
        try{
            FXMLLoader loader = new FXMLLoader(FxmlLoader.class.getResource(fileName + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 740);
            stage1.setScene(scene);
            return loader.getController();
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
