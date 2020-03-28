package edu.ahnu.controller;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXToggleButton;
import edu.ahnu.App;
import edu.ahnu.controller.module.*;
import edu.ahnu.util.AlertDialogUtil;
import edu.ahnu.util.DragAndChangeUtil;
import edu.ahnu.util.StageMapUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Data
public class MainController implements Initializable {

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
    private JFXColorPicker colorPicker;

    @FXML
    private JFXToggleButton lightBtn;

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

    @FXML
    private AnchorPane lightPane;


    //工具区域
    @FXML
    private AnchorPane toolPane;

    @FXML
    private Button chooseButton;


    //光源开关状态
    private int lightFlag = 0;


    //初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        centerPane.setDepthTest(DepthTest.DISABLE);
    }


    //正方体
    @FXML
    void createCube(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        CubeController cubeController = new CubeController();
        //画正方体
        cubeController.drawCube(centerPane, colorPicker);
    }

    @FXML
    void createCube2(ActionEvent event) throws IOException {

        String resources = "view/module/cube.fxml";
        String title = "正方体参数设置";
        CubeController cubeController = (CubeController) AlertDialogUtil.generateDialog(resources, title);
        cubeController.createCube2(centerPane, colorPicker);

    }


    //长方体
    @FXML
    void createCuboid(ActionEvent event) throws IOException {

       CuboidController cuboidController = new CuboidController();
        cuboidController.drawCuboid(centerPane, colorPicker);
    }

    @FXML
    void createCuboid2(ActionEvent event) throws IOException {
        String resources = "view/module/cuboid.fxml";
        String title = "长方体参数设置";
        CuboidController cuboidController = (CuboidController) AlertDialogUtil.generateDialog(resources, title);
        cuboidController.createCuboid2(centerPane, colorPicker);
    }

    //球体
    @FXML
    void createBall(ActionEvent event) throws IOException {


       BallController ballController = new BallController();
        ballController.drawBall(centerPane, colorPicker);
    }

    @FXML
    void createBall2(ActionEvent event) throws IOException {
        String resources = "view/module/ball.fxml";
        String title = "球体参数设置";
        BallController ballController = (BallController) AlertDialogUtil.generateDialog(resources, title);
        ballController.createBall2(centerPane, colorPicker);
    }

    //圆柱体
    @FXML
    void createCylinder(ActionEvent event) throws IOException {

       CylinderController cylinderController = new CylinderController();
        cylinderController.drawCylinder(centerPane, colorPicker);
    }

    @FXML
    void createCylinder2(ActionEvent event) throws IOException {
        String resources = "view/module/cylinder.fxml";
        String title = "圆柱体参数设置";
        CylinderController cylinderController = (CylinderController) AlertDialogUtil.generateDialog(resources, title);
        cylinderController.createCylinder2(centerPane, colorPicker);
    }



    //四棱锥
    @FXML
    void createPyramid(ActionEvent event) throws IOException {

        PyramidController pyramidController = new PyramidController();
        pyramidController.drawPyramid(centerPane, colorPicker);

    }

    @FXML
    void createPyramid2(ActionEvent event) throws IOException {
        String resources = "view/module/pyramid.fxml";
        String title = "正四棱锥参数设置";
        PyramidController pyramidController = (PyramidController) AlertDialogUtil.generateDialog(resources, title);
        pyramidController.createPyramid2(centerPane, colorPicker);
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


    //选择按钮 实现拖拽的功能
    @FXML
    void chooseNode(ActionEvent event) throws IOException {
        //移除centerPane中的EventHandler
        centerPane.setOnMousePressed(null);
        centerPane.setOnMouseDragged(null);
        centerPane.setOnMouseReleased(null);
        //添加拖拽 以及变换功能
        for (Node node : centerPane.getChildren()) {

            DragAndChangeUtil.draggable(node);
            DragAndChangeUtil.changeable(node);
            DragAndChangeUtil.colorful(node, colorPicker, centerPane);

            toolPane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL url = fxmlLoader.getClassLoader().getResource("view/tools.fxml");
            fxmlLoader.setLocation(url);
            AnchorPane root = (AnchorPane) fxmlLoader.load();
            toolPane.getChildren().add(root);

            ToolsController toolsController = fxmlLoader.getController();
            toolsController.showTools(node, centerPane);



        }
    }


    @FXML
    void lightOnOff(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = fxmlLoader.getClassLoader().getResource("view/light.fxml");
        fxmlLoader.setLocation(url);
        AnchorPane root = (AnchorPane) fxmlLoader.load();
        LightController lightController = fxmlLoader.getController();

        if (lightFlag == 0){
            lightFlag = 1;
            lightPane.getChildren().add(root);

            lightController.onOffLight(centerPane);
        }else if (lightFlag == 1){
            lightFlag = 0;
           //lightController.closeAllLight(centerPane);

            lightPane.getChildren().clear();
        }
    }




}
