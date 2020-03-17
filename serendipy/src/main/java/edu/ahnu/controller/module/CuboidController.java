package edu.ahnu.controller.module;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;

public class CuboidController {
    private double startX;
    private double startY;


    private double endLengthX;
    private double endLengthY;


    private Box box;

    @FXML
    private TextField lengthText;

    @FXML
    private TextField widthText;

    @FXML
    private TextField heightText;


    public void drawCuboid(final Pane pane){
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

                endLengthX = event.getX() - startX;
                endLengthY = event.getY() - startY;

                if (endLengthX <= 0 || endLengthY <= 0){
                    lengthText.setText("0.0");
                    widthText.setText("0.0");
                    heightText.setText("0.0");
                }else {
                    lengthText.setText(String.format("%.1f", endLengthX));
                    widthText.setText(String.format("%.1f", endLengthY));
                    heightText.setText(String.format("%.1f", endLengthY));

                }

                box = new Box(endLengthX, endLengthY, endLengthX);
                //设置边框颜色以及填充透明

                //设置位置
                box.setLayoutX(startX);
                box.setLayoutY(startY);

                pane.getChildren().add(box);

            }
        });

        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO 存储最终的形状 防止在Drag的时候删除了之前的

            }
        });


    }
}
