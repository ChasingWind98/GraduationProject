package edu.ahnu.controller;

import com.jfoenix.controls.JFXColorPicker;
import edu.ahnu.controller.module.*;
import edu.ahnu.util.DragAndChangeUtil;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Data
public class MainController implements Initializable {


    ObservableMap<String, Node> map;

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

    @FXML
    private Button chooseButton;




    //初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    //正方体
    @FXML
    void createCube(ActionEvent event) throws IOException {
        toolPane.getChildren().clear();
        CubeController cubeController = new CubeController();
        //画正方体
        cubeController.drawCube(centerPane, colorPicker);
    }

    //长方体
    @FXML
    void createCuboid(ActionEvent event) throws IOException {

       CuboidController cuboidController = new CuboidController();
        cuboidController.drawCuboid(centerPane, colorPicker);
    }

    //球体
    @FXML
    void createBall(ActionEvent event) throws IOException {


       BallController ballController = new BallController();
        ballController.drawBall(centerPane, colorPicker);
    }

    //圆柱体
    @FXML
    void createCylinder(ActionEvent event) throws IOException {

       CylinderController cylinderController = new CylinderController();
        cylinderController.drawCylinder(centerPane, colorPicker);
    }



    //四棱锥
    @FXML
    void createPyramid(ActionEvent event) throws IOException {

        PyramidController pyramidController = new PyramidController();
        pyramidController.drawPyramid(centerPane, colorPicker);

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


}
