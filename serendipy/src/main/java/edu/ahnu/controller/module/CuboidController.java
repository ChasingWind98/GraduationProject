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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import lombok.Data;
import lombok.NonNull;

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
    private JFXTextField lengthText;

    @FXML
    private JFXTextField layoutXText;

    @FXML
    private JFXTextField layoutYText;

    @FXML
    private JFXTextField widthText;

    @FXML
    private JFXTextField heightText;

    @FXML
    private JFXButton createBtn;


    public void drawCuboid(final Pane pane, ColorPicker colorPicker) {
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


    public void createCuboid2(Pane pane, ColorPicker colorPicker) {
        lengthText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    lengthText.setText(oldValue);
                }
            }
        });

        widthText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    widthText.setText(oldValue);
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

                if (lengthText.getText().isEmpty() || widthText.getText().isEmpty() || heightText.getText().isEmpty() || layoutXText.getText().isEmpty() || layoutYText.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("数据填写不完整哦 \n" + "✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧");
                    alert.setGraphic(new ImageView(ImagePathUtil.getImagePath()));
                    alert.show();
                } else {
                    double length = Double.parseDouble(lengthText.getText());
                    double width = Double.parseDouble(widthText.getText());
                    double height = Double.parseDouble(heightText.getText());
                    double layX = Double.parseDouble(layoutXText.getText());
                    double layY = Double.parseDouble(layoutYText.getText());

                    Box box2 = new Box(length, width, height);
                    box2.setLayoutX(layX);
                    box2.setLayoutY(layY);

                    pane.getChildren().add(box2);

                    //创建材质
                    Color value = colorPicker.valueProperty().getValue();
                    material = new PhongMaterial();
                    material.setDiffuseColor(value);

                    box2.setMaterial(material);

                }
            }
        });


    }
}
