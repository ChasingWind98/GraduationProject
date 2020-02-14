package edu.ahnu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class MainController {

    //注册组件
    @FXML
    private Button fxmlButton;

    @FXML
    private AnchorPane anchorLeft;

    @FXML
    private AnchorPane anchorRight;

    @FXML
    private TextField textField;

    //无参构造
    public MainController(){

    }

    @FXML
    public void initialize(){
        System.out.println("初始化");
    }

    //注册按钮的单击事件对应的方法
    @FXML
    private void fxmlAction(){
        System.out.println("单击事件");
    }



}
