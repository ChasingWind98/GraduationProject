package edu.ahnu.controller.module;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import lombok.Data;

import javax.tools.Tool;

@Data
public class CubeController {

    private double startX;
    private double startY;

    private double endX;

    private Box box;

    private PhongMaterial material;


    @FXML
    private TextField lengthText;


    //flag用于判断鼠标释放之前的状态
    int flag = 0;

    public void drawCube(final Pane pane, ColorPicker colorPicker) {


        pane.setOnMousePressed(event -> {
            flag = 1;
            startX = event.getX();
            startY = event.getY();

            //创建材质
            Color value = colorPicker.valueProperty().getValue();
            material = new PhongMaterial();
            material.setDiffuseColor(value);
        });


        pane.setOnMouseDragged(event -> {


            flag = 2;
            pane.getChildren().remove(box);
            endX = event.getX() - startX;


            //绘制的时候显示数据

            box = new Box(endX, endX, endX);

            box.setLayoutX(startX);
            box.setLayoutY(startY);

            pane.getChildren().add(box);
            box.setMaterial(material);
        });


        pane.setOnMouseReleased(event -> {
            if (flag == 2 && endX >= 0) {
                flag = 1;
                pane.getChildren().remove(box);

                final Box boxEnd = new Box(endX, endX, endX);
                boxEnd.setId("cube");

                //设置位置 以及材质

                boxEnd.setLayoutX(startX);
                boxEnd.setLayoutY(startY);

                pane.getChildren().add(boxEnd);
                boxEnd.setMaterial(material);

            }

        });

    }


}