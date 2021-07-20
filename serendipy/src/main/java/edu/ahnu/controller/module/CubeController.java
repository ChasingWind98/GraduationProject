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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import lombok.Data;

@Data
public class CubeController {

    private double startX;
    private double startY;

    private double endX;

    private Box box;

    private PhongMaterial material;


    @FXML
    private JFXTextField sideText;


    @FXML
    private JFXTextField layoutXText;

    @FXML
    private JFXTextField layoutYText;

    @FXML
    private JFXButton createBtn;


    //flag用于判断鼠标释放之前的状态
    int flag = 0;

    public void drawCube(final Pane pane, ColorPicker colorPicker) {


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
            endX = event.getX() - startX;


            //绘制的时候显示数据

            box = new Box(endX, endX, endX);

            box.setLayoutX(startX);
            box.setLayoutY(startY);

            pane.getChildren().add(box);
            box.setMaterial(material);
        });


        pane.setOnMouseReleased(event -> {
            if (flag == 2 && endX >= 0) {
                flag = 1;
                pane.getChildren().remove(box);

                final Box boxEnd = new Box(endX, endX, endX);
                boxEnd.setId("cube");

                //设置位置 以及材质

                boxEnd.setLayoutX(startX);
                boxEnd.setLayoutY(startY);

                pane.getChildren().add(boxEnd);
                boxEnd.setMaterial(material);

            }

        });

    }


    public void createCube2(Pane pane, ColorPicker colorPicker) {
        sideText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)) {
                    sideText.setText(oldValue);
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
                if (sideText.getText().isEmpty() || layoutXText.getText().isEmpty() || layoutYText.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("数据填写不完整哦 \n" + "✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧");
                    alert.setGraphic(new ImageView(ImagePathUtil.getImagePath()));
                    alert.show();
                } else {
                    double sideLen = Double.parseDouble(sideText.getText());
                    double layX = Double.parseDouble(layoutXText.getText());
                    double layY = Double.parseDouble(layoutYText.getText());

                    Box box2 = new Box(sideLen, sideLen, sideLen);
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