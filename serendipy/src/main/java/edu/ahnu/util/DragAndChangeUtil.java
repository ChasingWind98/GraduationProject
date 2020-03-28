package edu.ahnu.util;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

public class DragAndChangeUtil {

    /**
     * 表示节点位置的内部类
     */
    private static class Position {
        double x;
        double y;
    }

    /**
     * 当节点可拖动的时候改变鼠标的状态  以及添加鼠标监听事件
     *
     * @param node
     */
    public static void draggable(Node node) {
        final Position pos = new Position();


        // 提示用户该结点可点击
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            node.setCursor(Cursor.HAND);

        });
        node.addEventHandler(MouseEvent.MOUSE_EXITED, event -> node.setCursor(Cursor.DEFAULT));


        // 提示用户该结点可拖拽
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            node.setCursor(Cursor.HAND);

            // 当按压事件发生时，缓存事件发生的位置坐标
            pos.x = event.getX();
            pos.y = event.getY();

        });


        // 实现拖拽功能
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {

            node.setCursor(Cursor.MOVE);


            double distanceX = event.getX() - pos.x;
            double distanceY = event.getY() - pos.y;


            double x = node.getLayoutX() + distanceX;
            double y = node.getLayoutY() + distanceY;


            // 计算出 x、y 后将结点重定位到指定坐标点 (x, y)
            node.relocate(x, y);

        });

        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> node.setCursor(Cursor.DEFAULT));
    }

    /**
     * 当节点选中的时候 实现变换的功能
     * 对于不同的形状的变换 需要单独处理 使用switch
     *
     * @param node 图形节点
     */
    public static void changeable(Node node) {
        //通过触控板手势实现缩放
        node.setOnZoom(new EventHandler<ZoomEvent>() {
            @Override
            public void handle(ZoomEvent event) {
                node.setScaleX(event.getTotalZoomFactor());
                node.setScaleY(event.getTotalZoomFactor());
                node.setScaleZ(event.getTotalZoomFactor());
            }
        });

        //通过鼠标滚轮实现缩放
        node.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                final double SCALE_DELTA = 1.1;
                if (event.getDeltaY() == 0) {
                    return;
                }
                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
                node.setScaleX(node.getScaleX() * scaleFactor);
                node.setScaleY(node.getScaleY() * scaleFactor);
                node.setScaleZ(node.getScaleZ() * scaleFactor);

            }
        });

        //触控板旋转手势实现节点的旋转 TODO绕轴旋转
        node.setOnRotate(new EventHandler<RotateEvent>() {
            @Override
            public void handle(RotateEvent event) {
                double totalAngle = event.getTotalAngle();
                node.setRotate(node.getRotate() + event.getAngle());
            }
        });

    }


    public static void colorful(Node node, ColorPicker colorPicker, Pane pane) {

        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //双击改变颜色
                if (event.getClickCount() == 2) {
                    Color value = colorPicker.valueProperty().getValue();
                    PhongMaterial material = new PhongMaterial();
                    material.setDiffuseColor(value);
                    Shape3D shape3D = null;
                    try {
                        shape3D = (Shape3D) node;
                        shape3D.setMaterial(material);
                    } catch (Exception e) {

                    }
                }

                //三击 删除节点
                if (event.getClickCount() == 3){
                    pane.getChildren().remove(node);
                }

            }
        });


    }



}
