package edu.ahnu.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import lombok.Data;
import lombok.SneakyThrows;

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
    private TextField translationX;

    @FXML
    private TextField translationY;

    @FXML
    private TextField translationZ;
    
    @FXML
    private TextField rotateX;
    
    @FXML
    private TextField rotateY;
    
    @FXML
    private TextField rotateZ;


    @FXML
    private Slider scaleX;

    @FXML
    private Slider scaleY;

    @FXML
    private Slider scaleZ;

    @FXML
    private Slider shearX;

    @FXML
    private Slider shearY;


    @FXML
    private ImageView mirrorXimg;

    @FXML
    private ImageView mirrorYimg;


    /**
     * 用于在工具栏显示node的信息
     *
     * @param node
     */
    public void showTools(Node node, Pane toolPane) {
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @SneakyThrows
            @Override
            public void handle(MouseEvent event) {
                //平移
                translation(node);
                //旋转  TODO 在当前的基础上再次进行旋转
                rotate(node);
                //缩放
                scale(node);
                //镜像
                mirror(node);
                //错切
                shear(node);

            }
        });


    }

    /**
     * 节点平移
     *
     * @param node
     */
    private void translation(Node node) {

        translationX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!regex(newValue)) {
                    translationX.setText(oldValue);
                }

            }
        });


        translationX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //初始位置
                if (!translationX.getText().isEmpty()) {
                    currentTranslationX = currentTranslationX + Double.valueOf(translationX.getText());
                    node.setTranslateX(currentTranslationX);
                }

            }
        });

        translationY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!regex(newValue)) {
                    translationY.setText(oldValue);
                }
            }
        });


        translationY.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!translationY.getText().isEmpty()) {
                    currentTranslationY = currentTranslationY + Double.valueOf(translationY.getText());
                    node.setTranslateY(currentTranslationY);
                }


            }
        });


        translationZ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!regex(newValue)) {
                    translationZ.setText(oldValue);
                }
            }
        });


        translationZ.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!translationZ.getText().isEmpty()) {
                    currentTranslationZ = currentTranslationZ + Double.valueOf(translationZ.getText());
                    node.setTranslateZ(currentTranslationZ);
                }

            }
        });


    }

    /**
     * 节点旋转
     *
     * @param node
     */
    private void rotate(Node node) {
        rotateX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!regex(newValue)) {
                    rotateX.setText(oldValue);
                }
            }
        });
        
        rotateX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!rotateX.getText().isEmpty()){
                    currentRotateX = currentRotateX + Double.valueOf(rotateX.getText());
                    node.setRotationAxis(Rotate.X_AXIS);
                    node.setRotate(currentRotateX);
                }
            }
        });
        
        
        rotateY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!regex(newValue)) {
                    rotateY.setText(oldValue);
                }
            }
        });
        
        rotateY.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!rotateY.getText().isEmpty()){
                    currentRotateY = currentRotateY + Double.valueOf(rotateY.getText());
                    node.setRotationAxis(Rotate.Y_AXIS);
                    node.setRotate(currentRotateY);
                }
            }
        });


        rotateZ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!regex(newValue)) {
                    rotateZ.setText(oldValue);
                }
            }
        });

        rotateZ.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!rotateZ.getText().isEmpty()){
                    currentRotateZ = currentRotateZ + Double.valueOf(rotateZ.getText());
                    node.setRotationAxis(Rotate.Z_AXIS);
                    node.setRotate(currentRotateZ);
                }
            }
        });
    }

    /**
     * 节点缩放
     *
     * @param node
     */
    private void scale(Node node) {
       scaleX.valueProperty().addListener(new ChangeListener<Number>() {
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               node.setScaleX(newValue.doubleValue());
           }
       });

        scaleY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                node.setScaleY(newValue.doubleValue());
            }
        });


        scaleZ.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                node.setScaleZ(newValue.doubleValue());
            }
        });

    }

    //节点镜像变换
    private void mirror(Node node) {
        mirrorXimg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flagX == 0){
                    flagX = 1;
                    //关 --->  开
                    mirrorXimg.setImage(new Image("/image/open.png"));
                }else {
                    //开  --->  关
                    flagX = 0;
                    mirrorXimg.setImage(new Image("/image/close.png"));
                }
            }
        });

        mirrorYimg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flagY == 0){
                    flagY = 1;
                    //关 --->  开
                    mirrorYimg.setImage(new Image("/image/open.png"));
                }else {
                    //开  --->  关
                    flagY = 0;
                    mirrorYimg.setImage(new Image("/image/close.png"));

                }
            }
        });
    }

    //节点错切变换
    private void shear(Node node) {
        shearX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {


                currentShearX = newValue.doubleValue() - oldValue.doubleValue();
                double pivotX = node.getLayoutBounds().getWidth() / 2;
                double pivotY = node.getLayoutBounds().getMaxY();
                Shear shX = new Shear(currentShearX, 0, pivotX, pivotY);

                node.getTransforms().add(shX);
            }
        });

        shearY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                currentShearY = newValue.doubleValue() - oldValue.doubleValue();
                double pivotX = node.getLayoutBounds().getMinX();
                double pivotY = node.getLayoutBounds().getHeight() / 2;

                Shear shY = new Shear(0, currentShearY, pivotX,pivotY);
                node.getTransforms().add(shY);

            }
        });
    }


    //正负数的正则表达式
    private boolean regex(String content) {
        Pattern r = Pattern.compile("^[-\\+]?[\\d]*$");
        Matcher m = r.matcher(content);
        return m.matches();
    }

}
