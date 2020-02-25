package edu.ahnu.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Data;

@Data
public class MainController {

    //图形控件
    @FXML
    private Button cubeButton;

    @FXML
    private Button ballButton;

    @FXML
    private Button cylinderButton;

    @FXML
    private Button coneButton;


    @FXML
    private Canvas canvas;

    @FXML
    private Label locationInfo;



    //布局区域
    @FXML
    private MenuBar menu;

    @FXML
    private VBox leftBox;

    @FXML
    private HBox bottomBox;

    @FXML
    private BorderPane mainPane;

    @FXML
    void showInfo(MouseEvent event) {
        locationInfo.setText(event.getX() + ", " + event.getY() + " px");
    }

}
