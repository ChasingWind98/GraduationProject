package edu.ahnu.controller.module;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.ahnu.util.CustomTriangleMesh;
import edu.ahnu.util.ImagePathUtil;
import edu.ahnu.util.RegexUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
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

    @FXML
    private JFXTextField sideText;

    @FXML
    private JFXTextField heightText;

    @FXML
    private JFXButton createBtn;

    @FXML
    private JFXTextField layoutXText;

    @FXML
    private JFXTextField layoutYText;


    public void drawPyramid(Pane pane, ColorPicker colorPicker) {
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


    public void createPyramid2(Pane pane, ColorPicker colorPicker) {

        sideText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    sideText.setText(oldValue);
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
                if (sideText.getText().isEmpty() || heightText.getText().isEmpty() || layoutXText.getText().isEmpty() || layoutYText.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("数据填写不完整哦 \n" + "✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧");
                    alert.setGraphic(new ImageView(ImagePathUtil.getImagePath()));
                    alert.show();
                } else {
                    double side = Double.parseDouble(sideText.getText());
                    double height = Double.parseDouble(heightText.getText());
                    double layX = Double.parseDouble(layoutXText.getText());
                    double layY = Double.parseDouble(layoutYText.getText());

                    MeshView pyramid2 = CustomTriangleMesh.generatePyramid(side, height);

                    pyramid2.setLayoutX(layX);
                    pyramid2.setLayoutY(layY);

                    pane.getChildren().add(pyramid2);

                    //创建材质
                    Color value = colorPicker.valueProperty().getValue();
                    material = new PhongMaterial();
                    material.setDiffuseColor(value);

                    pyramid2.setMaterial(material);

                }
            }
        });

    }
}
