package animation_app;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

class MyRect extends Rectangle {
    private boolean isSelected;
    private final Rectangle rect = new Rectangle();
    private double newX;
    private double newY;

    MyRect(double x, double y, double width, double height) {
        newX = x;
        newY = y;
        rect.setX(newX);
        rect.setY(newY);
        rect.setWidth(width);
        rect.setHeight(height);
        isSelected = false;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setNewX(double x) {
        newX = x;
    }

    public void setNewY(double y) {
        newY = y;
    }

    public double getNewX() {
        return newX;
    }

    public double getNewY() {
        return newY;
    }
}

public class BasicAnimation extends Application {
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;

    public BasicAnimation() {
        System.out.println("Basic Animation Exercise");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);


        MyRect myRect = new MyRect(350, 250, 100, 100);
        myRect.getRect().setFill(Color.DARKRED);


        root.getChildren().add(myRect.getRect());


        scene.setOnMouseClicked(e -> {
            if (myRect.getRect().contains(e.getX(), e.getY())) {
                myRect.setSelected(true);
                myRect.getRect().setFill(Color.RED);
                System.out.println("Rectangle selected");
            }

            if (myRect.isSelected()) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    myRect.setNewX(e.getX());
                    myRect.setNewY(e.getY());
                    Timeline timeline = createRectTimeline(myRect);
                    timeline.play();
                }
            }
        });


        stage.setTitle("Basic Animation Exercise");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }


    private Timeline createRectTimeline(MyRect myRect) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(myRect.getRect().xProperty(),
                                myRect.getNewX() - myRect.getRect().getWidth() / 2),
                        new KeyValue(myRect.getRect().yProperty(),
                                myRect.getNewY() - myRect.getRect().getHeight() / 2)
                )
        );

        return timeline;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
