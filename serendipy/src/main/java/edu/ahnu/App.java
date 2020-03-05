package edu.ahnu;

import com.jogamp.opengl.GLCapabilities;
import edu.ahnu.controller.MainController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.EventListener;

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

        //main.fxml对应的Controller
        fxmlLoader.getRoot();
        final MainController mainController = fxmlLoader.getController();

        mainController.getCenterBox().heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mainController.getDrawingCanvas().setHeight(newValue.doubleValue());
            }
        });
        mainController.getCenterBox().widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mainController.getDrawingCanvas().setWidth(newValue.doubleValue());
            }
        });









        //开启3D渲染
        Scene scene = new Scene(root,1000, 800, true);

        scene.setCamera(new PerspectiveCamera());

        primaryStage.setScene(scene);
        primaryStage.setTitle("三维图形绘制");
        primaryStage.getIcons().add(new Image("/image/icon.png"));


        primaryStage.show();

    }
}
