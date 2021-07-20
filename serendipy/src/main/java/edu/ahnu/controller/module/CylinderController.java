package edu.ahnu.controller.module;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.ahnu.util.ImagePathUtil;
import edu.ahnu.util.RegexUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private JFXTextField radiusText;

    @FXML
    private JFXTextField heightText;

    @FXML
    private JFXTextField layoutXText;

    @FXML
    private JFXTextField layoutYText;

    @FXML
    private JFXButton createBtn;


    public void drawCylinder(final Pane pane, ColorPicker colorPicker) {
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

                double radius = endLengthX / 2;

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

    public void createCylinder2(Pane pane, ColorPicker colorPicker) {
        radiusText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    radiusText.setText(oldValue);
                }
            }
        });

        heightText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    heightText.setText(oldValue);
                }
            }
        });

        layoutXText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    layoutXText.setText(oldValue);
                }
            }
        });


        layoutYText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    layoutYText.setText(oldValue);
                }
            }
        });


        createBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //不合法的数据
                if (radiusText.getText().isEmpty() || heightText.getText().isEmpty() || layoutXText.getText().isEmpty() || layoutYText.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("数据填写不完整哦 \n" + "✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧");
                    alert.setGraphic(new ImageView(ImagePathUtil.getImagePath()));
                    alert.show();
                } else {
                    double radius = Double.parseDouble(radiusText.getText());
                    double height = Double.parseDouble(heightText.getText());
                    double layX = Double.parseDouble(layoutXText.getText());
                    double layY = Double.parseDouble(layoutYText.getText());

                    Cylinder cylinder2 = new Cylinder(radius, height);
                    cylinder2.setLayoutX(layX);
                    cylinder2.setLayoutY(layY);

                    pane.getChildren().add(cylinder2);

                    //创建材质
                    Color value = colorPicker.valueProperty().getValue();
                    material = new PhongMaterial();
                    material.setDiffuseColor(value);

                    cylinder2.setMaterial(material);

                }
            }
        });
    }
}
