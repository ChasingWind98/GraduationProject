package edu.ahnu.controller.module;

import edu.ahnu.util.CustomTriangleMesh;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import lombok.Data;

@Data
public class PyramidController {
    private double startX;
    private double startY;


    private double endLengthX;
    private double endLengthY;


    private MeshView pyramid;

    private PhongMaterial material;

    //flag用于判断鼠标释放之前的状态
    int flag = 0;

    public void drawPyramid(Pane pane, ColorPicker colorPicker){
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

            pane.getChildren().remove(pyramid);

            endLengthX = event.getX() - startX;
            endLengthY = event.getY() - startY;



            pyramid = CustomTriangleMesh.generatePyramid(endLengthX, endLengthY);
            //设置边框颜色以及填充透明

            //设置位置
            pyramid.setLayoutX(startX);
            pyramid.setLayoutY(startY);

            pane.getChildren().add(pyramid);
            pyramid.setMaterial(material);

        });


        pane.setOnMouseReleased(event -> {

            if (flag == 2 && endLengthX >= 0 && endLengthY >= 0) {
                flag = 1;

                pane.getChildren().remove(pyramid);

                MeshView pyramidEnd = CustomTriangleMesh.generatePyramid(endLengthX, endLengthY);
                pyramidEnd.setId("pyramid");

                pyramidEnd.setLayoutX(startX);
                pyramidEnd.setLayoutY(startY);
                pane.getChildren().add(pyramidEnd);
                pyramidEnd.setMaterial(material);
            }
        });
    }
}
