package animation_app;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

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


        Rectangle rect = new Rectangle(350, 250, 100, 100);
        rect.setFill(Color.DARKRED);


        root.getChildren().add(rect);


        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
//        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(2500),
                new KeyValue(rect.translateXProperty(), 300)));


        scene.setOnMouseClicked(e -> {
            if (rect.contains(e.getX(), e.getY())) {
                System.out.println("Attempting to play animation...");
                timeline.play();
            }
        });


        stage.setTitle("Basic Animation Exercise");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
