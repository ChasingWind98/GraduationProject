package edu.ahnu.controller;

import edu.ahnu.controller.module.*;
import edu.ahnu.service.MainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lombok.Data;

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

    @FXML
    private MenuItem clearButton;


    //布局区域

    @FXML
    private MenuBar menuBar;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private Label bottomLabel;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane centerPane;


    //工具区域
    @FXML
    private AnchorPane toolPane;


    //初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //设置颜色选择器的初始颜色
        colorPicker.setValue(Color.BLACK);

        centerPane.setDepthTest(DepthTest.DISABLE);
        leftPane.setDepthTest(DepthTest.DISABLE);
        menuBar.setDepthTest(DepthTest.DISABLE);
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

        CubeController cubeController = (CubeController)fxmlLoader.getController();
        //画正方体
        cubeController.drawCube(centerPane);

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
        //画
        CuboidController cuboidController = (CuboidController)fxmlLoader.getController();
        cuboidController.drawCuboid(centerPane);
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

        BallController ballController = (BallController)fxmlLoader.getController();
        ballController.drawBall(centerPane);
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

        CylinderController cylinderController = (CylinderController)fxmlLoader.getController();
        cylinderController.drawCylinder(centerPane);
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

        //获取控制器
        PrismController prismController = fxmlLoader.getController();
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

        //获取控制器
        ConeController coneController = fxmlLoader.getController();

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


    //清除centerPane的内容
    @FXML
    void cleanAll(ActionEvent event) {
        centerPane.getChildren().clear();
    }

}
