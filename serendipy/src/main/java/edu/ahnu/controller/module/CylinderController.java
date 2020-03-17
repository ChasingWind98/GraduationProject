package edu.ahnu.controller.module;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Cylinder;

public class CylinderController {


    private double startX;
    private double startY;

    private double endLengthX;
    private double endLengthY;

    private Cylinder cylinder;


    @FXML
    private TextField radiusText;

    @FXML
    private TextField heightText;

    public void drawCylinder(final Pane pane){
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
                pane.getChildren().remove(cylinder);

                endLengthX = event.getX() - startX;
                endLengthY = event.getY() - startY;

                double radius = endLengthX /2;

                if (radius <= 0 || endLengthY <= 0){
                    radiusText.setText("0.0");
                    heightText.setText("0.0");
                }else {
                    radiusText.setText(String.format("%.1f", radius));
                    heightText.setText(String.format("%.1f", endLengthY));
                }



                cylinder = new Cylinder(radius, endLengthY);
                cylinder.setLayoutX(startX);
                cylinder.setLayoutY(startY);

                pane.getChildren().add(cylinder);
            }
        });

        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }
}
