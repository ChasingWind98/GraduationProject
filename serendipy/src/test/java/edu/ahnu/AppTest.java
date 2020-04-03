package edu.ahnu;

import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author blj0011
 */
public class AppTest extends Application
{
    @Override
    public void start(Stage primaryStage) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        JFXToggleButton button = new JFXToggleButton();
        button.setLayoutX(0);
        button.setLayoutY(0);

        JFXToggleButton button2 = new JFXToggleButton();
        button2.setLayoutX(0);
        button2.setLayoutY(100);

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setLayoutX(0);
        colorPicker.setLayoutY(300);

        Rectangle rect1 = new Rectangle(200, 100);
        rect1.setLayoutX(100);
        rect1.setLayoutY(100);
        Rotate rotateX = new Rotate(30, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(30, Rotate.Y_AXIS);
        rect1.getTransforms().addAll(rotateX,rotateY);

        JFXScrollPane scrollPane = new JFXScrollPane();
        scrollPane.setLayoutX(600);
        scrollPane.setLayoutY(300);
        scrollPane.setPrefSize(100, 50);
        scrollPane.getChildren().addAll(rect1,colorPicker,button);


        AnchorPane root = new AnchorPane();

        root.getChildren().addAll(rect1, button, button2, colorPicker, scrollPane);

        button.selectedProperty().addListener(new ChangeListener<Boolean>() {

            Rectangle rect2 = new Rectangle();
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.equals(true)){

                   try {
                        rect2 = (Rectangle) BeanUtils.cloneBean(rect1);


                   } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    root.getChildren().add(rect2);

                    double width = rect1.getLayoutBounds().getWidth();
                    double height = rect1.getLayoutBounds().getHeight();
                    Translate translate = new Translate(width + 10, 0);
                    Scale scale = new Scale(-1, 1, width / 2, height / 2);

                    rect2.getTransforms().addAll(translate, scale);

                }else {
                    root.getChildren().remove(rect2);
                }
            }
        });


        button2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               if (newValue.equals(true)){
                   Reflection reflection = new Reflection();
                   reflection.setTopOpacity(1);
                   reflection.setBottomOpacity(1);
                   reflection.setTopOffset(10);
                   reflection.setFraction(1);

                   rect1.setEffect(reflection);
               }else {
                   rect1.setEffect(null);
               }
            }
        });


        Scene scene = new Scene(root, 1000, 800);


        primaryStage.setScene(scene);
        primaryStage.setTitle("三维图形绘制");
        primaryStage.getIcons().add(new Image("/image/icon.png"));

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}