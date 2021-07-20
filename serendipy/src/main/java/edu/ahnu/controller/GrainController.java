package edu.ahnu.controller;


import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

import java.net.URL;
import java.util.ResourceBundle;

public class GrainController implements Initializable {


    @FXML
    private JFXToggleButton grainBtn1;

    @FXML
    private JFXToggleButton grainBtn2;

    @FXML
    private JFXToggleButton grainBtn3;

    @FXML
    private JFXToggleButton grainBtn4;

    private ToggleGroup group;

    private PhongMaterial phongMaterial;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        grainBtn1.setToggleGroup(group);
        grainBtn2.setToggleGroup(group);
        grainBtn3.setToggleGroup(group);
        grainBtn4.setToggleGroup(group);
    }


    public void addGrain(Node node) {
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                phongMaterial = new PhongMaterial();
                Shape3D shape = null;
                try {
                    shape = (Shape3D) node;
                } catch (Exception e) {

                }
                if (grainBtn1.isSelected()) {
                    //纹理1
                    phongMaterial.setDiffuseMap(new Image("image/grain/muzhi.jpg"));
                    phongMaterial.setBumpMap(new Image("image/grain/muzhi.png"));
                    shape.setMaterial(phongMaterial);

                } else if (grainBtn2.isSelected()) {
                    //纹理2
                    phongMaterial.setDiffuseMap(new Image("image/grain/qiangbi.jpg"));
                    phongMaterial.setBumpMap(new Image("image/grain/qiangbi.png"));
                    shape.setMaterial(phongMaterial);

                } else if (grainBtn3.isSelected()) {
                    //纹理3

                    phongMaterial.setDiffuseMap(new Image("image/grain/fushihui.jpg"));
                    phongMaterial.setBumpMap(new Image("image/grain/fushihui.png"));
                    shape.setMaterial(phongMaterial);

                } else if (grainBtn4.isSelected()) {
                    //纹理4
                    phongMaterial.setDiffuseMap(new Image("image/grain/gezi.jpg"));
                    phongMaterial.setBumpMap(new Image("image/grain/gezi.png"));
                    shape.setMaterial(phongMaterial);

                } else {
                    phongMaterial.setDiffuseMap(null);
                    phongMaterial.setBumpMap(null);
                    shape.setMaterial(phongMaterial);
                }
            }
        });
    }


}
