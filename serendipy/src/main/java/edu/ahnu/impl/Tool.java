package edu.ahnu.impl;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public interface Tool {
    void press(MouseEvent e, Pane pane);
    void drag(MouseEvent e,Pane pane);
    void release(MouseEvent e,Pane pane);
}
