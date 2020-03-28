package edu.ahnu.controller.module;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.ahnu.util.ImagePathUtil;
import edu.ahnu.util.RegexUtil;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import lombok.Data;

@Data
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

    private PhongMaterial material;


    //flag用于判断鼠标释放之前的状态
    int flag = 0;


    @FXML
    private JFXTextField radiusText;

    @FXML
    private JFXTextField layoutXText;

    @FXML
    private JFXTextField layoutYText;

    @FXML
    private JFXButton createBtn;


    public void drawBall(final Pane pane, ColorPicker colorPicker) {
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

                //移除之前的球体
                pane.getChildren().remove(sphere);


                endLengthX = event.getX() - startX;
                endLengthY = event.getY() - startY;
                currentRadius = Math.sqrt(endLengthX * endLengthX + endLengthY * endLengthY) / 2;

                //绘制的时候显示的数据
                /*if (currentRadius <= 0) {
                    radiusText.setText("0.0");
                } else {
                    radiusText.setText(String.format("%.1f", currentRadius));
                    //radiusText.textProperty().bindBidirectional(new SimpleStringProperty(String.valueOf(currentRadius)));

                }
*/
                sphere = new Sphere(currentRadius);
                //设置位置
                sphere.setLayoutX(startX);
                sphere.setLayoutY(startY);

                pane.getChildren().add(sphere);
                sphere.setMaterial(material);
            }
        });


        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (flag == 2 && endLengthX >= 0 && endLengthY >= 0) {
                    flag = 1;
                    pane.getChildren().remove(sphere);
                    Sphere sphereEnd = new Sphere(currentRadius);

                    sphereEnd.setLayoutX(startX);
                    sphereEnd.setLayoutY(startY);

                    //设置id
                    sphereEnd.setId("ball");

                    pane.getChildren().add(sphereEnd);
                    sphereEnd.setMaterial(material);
                }
            }
        });

    }

    public void createBall2(Pane pane, ColorPicker colorPicker){
        radiusText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)){
                    radiusText.setText(oldValue);
                }
            }
        });

        layoutXText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)){
                    layoutXText.setText(oldValue);
                }
            }
        });


        layoutYText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (RegexUtil.judgeNum(newValue)){
                    layoutYText.setText(oldValue);
                }
            }
        });



        createBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //不合法的数据
                if (radiusText.getText().isEmpty() || layoutXText.getText().isEmpty() || layoutYText.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("数据填写不完整哦 \n" + "✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧");
                    alert.setGraphic(new ImageView(ImagePathUtil.getImagePath()));
                    alert.show();
                }else {
                    double radius = Double.parseDouble(radiusText.getText());
                    double layX = Double.parseDouble(layoutXText.getText());
                    double layY = Double.parseDouble(layoutYText.getText());

                    Sphere sphere2 = new Sphere(radius);
                    sphere2.setLayoutX(layX);
                    sphere2.setLayoutY(layY);

                    pane.getChildren().add(sphere2);

                    //创建材质
                    Color value = colorPicker.valueProperty().getValue();
                    material = new PhongMaterial();
                    material.setDiffuseColor(value);

                    sphere2.setMaterial(material);

                }
            }
        });

    }
}
