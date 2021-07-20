package edu.ahnu.util;

import edu.ahnu.controller.module.CubeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AlertDialogUtil {
    /**
     * 用于生成弹窗的工具类
     *
     * @param resources 页面资源路径
     * @param title     弹窗标题
     * @throws IOException
     */
    public static Object generateDialog(String resources, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource(resources);
        fxmlLoader.setLocation(url);
        AnchorPane root = (AnchorPane) fxmlLoader.load();

        Object controller = fxmlLoader.getController();


        Scene scene = new Scene(root, 300, 200);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initOwner(StageMapUtil.STAGE.get("topStage"));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();


        return controller;
    }
}
