package edu.ahnu;

import edu.ahnu.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        //main.fxml对应的Controller
        fxmlLoader.getRoot();
        MainController mainController = fxmlLoader.getController();


        //用于绑定页面 自适应页面
        mainController.getMenu().prefWidthProperty().bind(root.widthProperty());
        mainController.getMainPane().prefHeightProperty().bind(root.heightProperty());
        mainController.getMainPane().prefWidthProperty().bind(root.widthProperty());
        mainController.getLeftBox().prefHeightProperty().bind(root.heightProperty());



        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("三维图形绘制");
        primaryStage.getIcons().add(new Image("/image/icon.png"));


        primaryStage.show();

    }
}
