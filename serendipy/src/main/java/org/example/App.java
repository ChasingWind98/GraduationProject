package org.example;

import edu.ahnu.Draw;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App extends Application {
    public static void main(String[] args) {
        System.out.println("main() = " + Thread.currentThread().getName());
        launch(args);

    }

    @Override
    public void init() throws Exception {
        System.out.println("init() = " + Thread.currentThread().getName());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Stage类
        //设置标题
        primaryStage.setTitle("三维图形变换");
        //设置icon图标
        primaryStage.getIcons().add(new Image("/image/icon_website.png"));

        //设置宽和高
//        primaryStage.setWidth(1500.0);
//        primaryStage.setHeight(600.0);

        //动态获取到窗口的宽和高
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("当前高度 " + newValue);
            }
        });

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("当前高度 " + newValue);
            }
        });

        //设置全屏  可以使用esc退出   不同于最大化  最大化还是会有状态栏的
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(new Group()));

        primaryStage.show();
    }


    @Override
    public void stop() throws Exception {
        System.out.println("stop() = " + Thread.currentThread().getName());
    }
}
