package edu.ahnu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //加载FXML文件
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/main.fxml");
        fxmlLoader.setLocation(url);

        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
