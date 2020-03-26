package edu.ahnu.controller.module;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import lombok.Data;

@Data
public class CuboidController {
    private double startX;
    private double startY;


    private double endLengthX;
    private double endLengthY;


    private Box box;

    private PhongMaterial material;

    //flag用于判断鼠标释放之前的状态
    int flag = 0;

    @FXML
    private TextField lengthText;

    @FXML
    private TextField widthText;

    @FXML
    private TextField heightText;


    public void drawCuboid(final Pane pane, ColorPicker colorPicker){
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

            endLengthX = event.getX() - startX;
            endLengthY = event.getY() - startY;

            //绘制的时候显示的数据
           /* if (endLengthX <= 0 || endLengthY <= 0){
                lengthText.setText("0.0");
                widthText.setText("0.0");
                heightText.setText("0.0");
            }else {
                lengthText.setText(String.format("%.1f", endLengthX));
                widthText.setText(String.format("%.1f", endLengthY));
                heightText.setText(String.format("%.1f", endLengthY));

            }*/

            box = new Box(endLengthX, endLengthY, endLengthX);
            //设置边框颜色以及填充透明

            //设置位置
            box.setLayoutX(startX);
            box.setLayoutY(startY);

            pane.getChildren().add(box);
            box.setMaterial(material);

        });

        pane.setOnMouseReleased(event -> {

            if (flag == 2 && endLengthX >= 0 && endLengthY >= 0) {
                flag = 1;

                pane.getChildren().remove(box);

                Box boxEnd = new Box(endLengthX, endLengthY, endLengthX);
                boxEnd.setId("cuboid");

                boxEnd.setLayoutX(startX);
                boxEnd.setLayoutY(startY);
                pane.getChildren().add(boxEnd);
                boxEnd.setMaterial(material);
            }
        });


    }
}
