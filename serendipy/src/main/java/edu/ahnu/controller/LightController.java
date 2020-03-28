package edu.ahnu.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXToggleButton;
import edu.ahnu.util.DragAndChangeUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.Cursor;
import javafx.scene.PointLight;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.Paint;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class LightController {

    @FXML
    private JFXToggleButton ambientLightBtn;

    @FXML
    private JFXToggleButton pointLightBtn;

    @FXML
    private JFXColorPicker lightColorPicker;


    private AmbientLight ambientLight;

    private PointLight pointLight;

    private JFXButton jfxBtn;

    private Color color;



    public void onOffLight(Pane pane) {
        ambientLightBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)) {

                    color = lightColorPicker.getValue();
                    ambientLight = new AmbientLight();
                    ambientLight.setColor(color);
                    ambientLight.setLightOn(true);

                    lightColorPicker.valueProperty().addListener(new ChangeListener<Color>() {
                        @Override
                        public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                            ambientLight.setColor(newValue);
                        }
                    });


                    pane.getChildren().add(ambientLight);

                } else {
                    //ambientLight.setLightOn(false);
                    pane.getChildren().remove(ambientLight);
                }
            }
        });


        pointLightBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)) {
                    //这个颜色在这里使用来第一次当颜色选择器没有改变的时候初始化使用的
                    color = lightColorPicker.getValue();


                    String colorStr = color.toString();
                    jfxBtn = new JFXButton();
                    jfxBtn.setOpacity(1);
                    jfxBtn.setGraphic(new ImageView("/image/light.png"));
                    jfxBtn.setCursor(Cursor.HAND);
                    jfxBtn.setButtonType(JFXButton.ButtonType.RAISED);
                    jfxBtn.setRipplerFill(Paint.valueOf(colorStr));

                    pointLight = new PointLight();
                    pointLight.setColor(color);


                    pointLight.layoutXProperty().bindBidirectional(jfxBtn.layoutXProperty());
                    pointLight.layoutYProperty().bindBidirectional(jfxBtn.layoutYProperty());
                    pointLight.setLightOn(true);

                    pane.getChildren().addAll(jfxBtn, pointLight);


                    DragAndChangeUtil.draggable(jfxBtn);


                    jfxBtn.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            jfxBtn.setRipplerFill(Paint.valueOf(lightColorPicker.getValue().toString()));

                        }
                    });

                    lightColorPicker.valueProperty().addListener(new ChangeListener<Color>() {
                        @Override
                        public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                            pointLight.setColor(newValue);
                        }
                    });

                } else {
                    pane.getChildren().removeAll(pointLight, jfxBtn);
                }
            }
        });


    }




}
