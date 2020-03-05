package edu.ahnu.controller;

import edu.ahnu.service.MainService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Data;
import lombok.Value;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Data
public class MainController implements Initializable {

    final MainService service = new MainService();

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

    @FXML
    private ColorPicker colorPicker;


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

    @FXML
    private Group group;


    //工具区域
    @FXML
    private AnchorPane toolPane;


    //初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //页面自适应
        centerBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                drawingCanvas.setHeight(newValue.doubleValue());
            }
        });

        centerBox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                drawingCanvas.setWidth(newValue.doubleValue());
            }
        });
        //设置颜色选择器的初始颜色
        colorPicker.setValue(Color.BLACK);
    }


    //正方体
    @FXML
    void createCube(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        //添加功能区
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/module/cubeMenu.fxml");
        fxmlLoader.setLocation(url);
        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        toolPane.getChildren().add(root);


    }

    //长方体
    @FXML
    void createCuboid(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/module/cuboidMenu.fxml");
        fxmlLoader.setLocation(url);
        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        toolPane.getChildren().add(root);
    }

    //球体
    @FXML
    void createBall(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/module/ballMenu.fxml");
        fxmlLoader.setLocation(url);
        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        toolPane.getChildren().add(root);
    }

    //圆柱体
    @FXML
    void createCylinder(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/module/cylinderMenu.fxml");
        fxmlLoader.setLocation(url);
        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        toolPane.getChildren().add(root);
    }

    //棱柱
    @FXML
    void createPrism(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/module/prismMenu.fxml");
        fxmlLoader.setLocation(url);
        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        toolPane.getChildren().add(root);
    }


    //圆锥
    @FXML
    void createCone(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/module/coneMenu.fxml");
        fxmlLoader.setLocation(url);
        //获取加载的根结点
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        toolPane.getChildren().add(root);
    }




    //底部状态栏 显示当前鼠标所在的位置信息
    @FXML
    void setMouseExit(MouseEvent event) {
        bottomLabel.setText("");
    }

    @FXML
    void setMouseLocation(MouseEvent event) {
        bottomLabel.setText(String.format("%.1f, %.1fpx ", event.getX(), event.getY()));
    }



}
