package edu.ahnu.controller.module;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;

import javax.swing.event.DocumentEvent;

public class BallController {
    private double startX;
    private double startY;

    private double endLengthX;
    private double endLengthY;

    //半径
    private double currentRadius;

    //球半径
    private double ballRadius;

    //球
    private Sphere sphere;


    @FXML
    private TextField radiusText;

    public void drawBall(final Pane pane) {
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

                //移除之前的球体
                pane.getChildren().remove(sphere);


                endLengthX = event.getX() - startX;
                endLengthY = event.getY() - startY;
                currentRadius = Math.sqrt(endLengthX * endLengthX + endLengthY * endLengthY) / 2;

                if (currentRadius <= 0) {
                    radiusText.setText("0.0");
                } else {

                    radiusText.setText(String.format("%.1f", currentRadius));
                }

                sphere = new Sphere(currentRadius);
                //设置位置
                sphere.setLayoutX(startX);
                sphere.setLayoutY(startY);

                pane.getChildren().add(sphere);
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
