package edu.ahnu.controller.module;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;


public class CubeController {

    private double startX;
    private double startY;


    private double endX;


    private double length;


    private Box box;


    @FXML
    private TextField lengthText;


    public void drawCube(final Pane pane) {

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startX = event.getX();
                startY = event.getY();
            }
        });


        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                pane.getChildren().remove(box);
                endX = event.getX() - startX;

                if (endX <= 0) {
                    lengthText.setText("0.0");
                } else {

                    lengthText.setText(String.format("%.1f", endX));
                }


                box = new Box(endX, endX, endX);

                //设置边框颜色以及填充透明

                //设置位置
                box.setLayoutX(startX);
                box.setLayoutY(startY);


                //显示参数

                pane.getChildren().add(box);

            }
        });

        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO 存储最终的形状 防止在Drag的时候删除了之前的 使用MainController中的List存储 然后清除的时候需要清空List
            }
        });


        //选中图形的时候 参数显示


    }


}