package edu.ahnu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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
        BorderPane root = (BorderPane) fxmlLoader.load();

        //开启3D渲染 以及抗锯齿
        Scene scene = new Scene(root,1000, 800, false, SceneAntialiasing.BALANCED);
        //添加相机  透视相机
        scene.setCamera(new PerspectiveCamera());



        primaryStage.setScene(scene);
        primaryStage.setTitle("三维图形绘制");
        primaryStage.getIcons().add(new Image("/image/icon.png"));

        primaryStage.show();
    }
}
