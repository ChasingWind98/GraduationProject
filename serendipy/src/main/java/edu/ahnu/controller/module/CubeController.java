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

    private Box box;

    @FXML
    private TextField lengthText;


    //flag用于判断鼠标释放之前的状态
    int flag = 0;

    public void drawCube(final Pane pane) {

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                flag = 1;
                startX = event.getX();
                startY = event.getY();
            }
        });


        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


                flag = 2;
                pane.getChildren().remove(box);
                endX = event.getX() - startX;

                if (endX <= 0) {
                    lengthText.setText("0.0");
                } else {

                    lengthText.setText(String.format("%.1f", endX));
                }


                box = new Box(endX, endX, endX);

                //TODO Materia材质设置颜色 ColorPicker

                box.setLayoutX(startX);
                box.setLayoutY(startY);

                pane.getChildren().add(box);
            }


        });


        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flag == 2 && endX >= 0) {
                    flag = 1;
                    pane.getChildren().remove(box);

                    final Box boxEnd = new Box(endX, endX, endX);

                    //设置位置
                    boxEnd.setLayoutX(startX);
                    boxEnd.setLayoutY(startY);

                    pane.getChildren().add(boxEnd);

                }

            }
        });

    }


}