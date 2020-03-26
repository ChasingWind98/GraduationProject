package edu.ahnu.controller.module;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import lombok.Data;

@Data
public class CylinderController {


    private double startX;
    private double startY;

    private double endLengthX;
    private double endLengthY;

    private Cylinder cylinder;

    private PhongMaterial material;

    //flag用于判断鼠标释放之前的状态
    int flag = 0;


    @FXML
    private TextField radiusText;

    @FXML
    private TextField heightText;

    public void drawCylinder(final Pane pane, ColorPicker colorPicker){
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                flag = 1;

                startX = event.getX();
                startY = event.getY();


                //创建材质
                Color value = colorPicker.valueProperty().getValue();
                material = new PhongMaterial();
                material.setDiffuseColor(value);
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                flag = 2;

                pane.getChildren().remove(cylinder);

                endLengthX = event.getX() - startX;
                endLengthY = event.getY() - startY;

                double radius = endLengthX /2;

               /* if (radius <= 0 || endLengthY <= 0){
                    radiusText.setText("0.0");
                    heightText.setText("0.0");
                }else {
                    radiusText.setText(String.format("%.1f", radius));
                    heightText.setText(String.format("%.1f", endLengthY));
                }*/



                cylinder = new Cylinder(radius, endLengthY);
                cylinder.setLayoutX(startX);
                cylinder.setLayoutY(startY);

                pane.getChildren().add(cylinder);
                cylinder.setMaterial(material);
            }
        });

        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flag == 2 && endLengthX >= 0 && endLengthY >= 0) {
                    flag = 1;
                    pane.getChildren().remove(cylinder);
                    Cylinder cylinderEnd = new Cylinder(endLengthX / 2, endLengthY);
                    cylinderEnd.setId("cylinder");
                    cylinderEnd.setLayoutX(startX);
                    cylinderEnd.setLayoutY(startY);

                    pane.getChildren().add(cylinderEnd);
                    cylinderEnd.setMaterial(material);
                }
            }
        });
    }
}
