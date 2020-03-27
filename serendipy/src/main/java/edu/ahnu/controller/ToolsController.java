package edu.ahnu.controller;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class ToolsController {

    //存储位移位置的变量
    double currentTranslationX = 0;
    double currentTranslationY = 0;
    double currentTranslationZ = 0;


    //存储旋转角度的变量
    double currentRotateX = 0;
    double currentRotateY = 0;
    double currentRotateZ = 0;


    //错切当前的角度
    double currentShearX = 0;
    double currentShearY = 0;

    //镜像变换开关按钮背景设置
    int flagX = 0;
    int flagY = 0;



    

    @FXML
    private JFXTextField translationX;

    @FXML
    private JFXTextField translationY;

    @FXML
    private JFXTextField translationZ;
    
    @FXML
    private JFXTextField rotateX;
    
    @FXML
    private JFXTextField rotateY;
    
    @FXML
    private JFXTextField rotateZ;


    @FXML
    private JFXSlider scaleX;

    @FXML
    private JFXSlider scaleY;

    @FXML
    private JFXSlider scaleZ;

    @FXML
    private JFXSlider shearX;

    @FXML
    private JFXSlider shearY;


    @FXML
    private JFXToggleButton mirrorXBtn;

    @FXML
    private JFXToggleButton mirrorYBtn;


    /**
     * 用于在工具栏显示node的信息
     *
     * @param node
     */
    public void showTools(Node node, Pane centerPane) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            //平移
            translation(node);
            //旋转  TODO 在当前的基础上再次进行旋转
            rotate(node);
            //缩放
            scale(node);
            //镜像
            mirror(node, centerPane);
            //错切
            shear(node);

        });


    }

    /**
     * 节点平移
     *
     * @param node
     */
    private void translation(Node node) {

        translationX.textProperty().addListener((observable, oldValue, newValue) -> {

            if (judgeNum(newValue)) {
                translationX.setText(oldValue);
            }

        });


        translationX.setOnAction(event -> {

            //初始位置
            if (!translationX.getText().isEmpty()) {
                currentTranslationX = currentTranslationX + Double.parseDouble(translationX.getText());
                node.setTranslateX(currentTranslationX);
            }

        });

        translationY.textProperty().addListener((observable, oldValue, newValue) -> {

            if (judgeNum(newValue)) {
                translationY.setText(oldValue);
            }
        });


        translationY.setOnAction(event -> {

            if (!translationY.getText().isEmpty()) {
                currentTranslationY = currentTranslationY + Double.parseDouble(translationY.getText());
                node.setTranslateY(currentTranslationY);
            }


        });


        translationZ.textProperty().addListener((observable, oldValue, newValue) -> {

            if (judgeNum(newValue)) {
                translationZ.setText(oldValue);
            }
        });


        translationZ.setOnAction(event -> {

            if (!translationZ.getText().isEmpty()) {
                currentTranslationZ = currentTranslationZ + Double.parseDouble(translationZ.getText());
                node.setTranslateZ(currentTranslationZ);
            }

        });


    }

    /**
     * 节点旋转
     *
     * @param node
     */
    private void rotate(Node node) {

        rotateX.textProperty().addListener((observable, oldValue, newValue) -> {
            if (judgeNum(newValue)) {
                rotateX.setText(oldValue);
            }
        });
        
        rotateX.setOnAction(event -> {
            if (!rotateX.getText().isEmpty()){

                currentRotateX = currentRotateX + Double.parseDouble(rotateX.getText());

                node.setRotationAxis(Rotate.X_AXIS);
                node.setRotate(currentRotateX);

            }
        });
        
        
        rotateY.textProperty().addListener((observable, oldValue, newValue) -> {
            if (judgeNum(newValue)) {
                rotateY.setText(oldValue);
            }
        });
        
        rotateY.setOnAction(event -> {
            if (!rotateY.getText().isEmpty()){
                currentRotateY = currentRotateY + Double.parseDouble(rotateY.getText());
                node.setRotationAxis(Rotate.Y_AXIS);
                node.setRotate(currentRotateY);


            }
        });


        rotateZ.textProperty().addListener((observable, oldValue, newValue) -> {
            if (judgeNum(newValue)) {
                rotateZ.setText(oldValue);
            }
        });

        rotateZ.setOnAction(event -> {
            if (!rotateZ.getText().isEmpty()){
                currentRotateZ = currentRotateZ + Double.parseDouble(rotateZ.getText());
                node.setRotationAxis(Rotate.Z_AXIS);
                node.setRotate(currentRotateZ);


            }
        });

    }

    /**
     * 节点缩放
     *
     * @param node
     */
    private void scale(Node node) {
       scaleX.valueProperty().addListener((observable, oldValue, newValue) -> node.setScaleX(newValue.doubleValue()));

        scaleY.valueProperty().addListener((observable, oldValue, newValue) -> node.setScaleY(newValue.doubleValue()));


        scaleZ.valueProperty().addListener((observable, oldValue, newValue) -> node.setScaleZ(newValue.doubleValue()));

    }

    //节点镜像变换
    private void mirror(Node node, Pane centerPane) {

        //水平镜像
        mirrorXBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
            Node mirrorXNode = null;
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)){
                    try {
                        mirrorXNode = (Node) BeanUtils.cloneBean(node);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    ObservableList<Transform> transforms = node.getTransforms();
                    mirrorXNode.getTransforms().addAll(transforms);
                    centerPane.getChildren().add(mirrorXNode);

                    mirrorXNode.setTranslateX(node.getBoundsInParent().getWidth() + 10);

                    double scX = node.getScaleX();
                    mirrorXNode.setScaleX(-1 * scX);


                }else {
                    centerPane.getChildren().remove(mirrorXNode);
                }
            }
        });

        //垂直镜像
        mirrorYBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
            Node mirrorYNode = null;
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)){
                    try {
                        mirrorYNode = (Node) BeanUtils.cloneBean(node);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }


                    ObservableList<Transform> transforms = node.getTransforms();
                    mirrorYNode.getTransforms().addAll(transforms);
                    centerPane.getChildren().add(mirrorYNode);

                    mirrorYNode.setTranslateY(node.getBoundsInParent().getHeight() + 10);
                    mirrorYNode.setScaleY(-1);
                }else {
                    centerPane.getChildren().remove(mirrorYNode);
                }
            }
        });

    }

    //节点错切变换
    private void shear(Node node) {
        shearX.valueProperty().addListener((observable, oldValue, newValue) -> {

            currentShearX = newValue.doubleValue() - oldValue.doubleValue();
            double pivotX = node.getLayoutBounds().getWidth() / 2;
            double pivotY = node.getLayoutBounds().getMaxY();
            Shear shX = new Shear(currentShearX, 0, pivotX, pivotY);

            node.getTransforms().add(shX);
        });

        shearY.valueProperty().addListener((observable, oldValue, newValue) -> {

            currentShearY = newValue.doubleValue() - oldValue.doubleValue();
            double pivotX = node.getLayoutBounds().getMinX();
            double pivotY = node.getLayoutBounds().getHeight() / 2;

            Shear shY = new Shear(0, currentShearY, pivotX,pivotY);
            node.getTransforms().add(shY);

        });


    }


    //正负数的正则表达式
    private boolean judgeNum(String content) {
        Pattern r = Pattern.compile("^[-+]?[\\d]*$");
        Matcher m = r.matcher(content);
        return !m.matches();
    }

}
