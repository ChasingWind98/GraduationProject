package edu.ahnu.controller;

import edu.ahnu.controller.module.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import lombok.Data;

import java.io.IOException;
import java.net.URL;


@Data
public class MainController {

    //页面上的图形按钮
    @FXML
    private Button cubeButton;

    @FXML
    private Button cuboidButton;

    @FXML
    private Button ballButton;

    @FXML
    private Button cylinderButton;

    @FXML
    private Button prismButton;

    @FXML
    private Button coneButton;


    //布局区域

    @FXML
    private Menu menuBar;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private Label bottomLabel;

    @FXML
    private FlowPane leftPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private VBox centerBox;

    @FXML
    private Canvas drawingCanvas;


    //测试区域
    @FXML
    private AnchorPane testPane;


    @FXML
    void createCube(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/module/test.fxml");
        fxmlLoader.setLocation(url);
        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        testPane.getChildren().add(root);
    }


    @FXML
    void setMouseExit(MouseEvent event) {
        bottomLabel.setText("");
    }

    @FXML
    void setMouseLocation(MouseEvent event) {
        bottomLabel.setText(String.format("%.1f, %.1fpx ", event.getX(), event.getY()));
    }

}
