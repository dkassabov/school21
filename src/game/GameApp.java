package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;


public class GameApp extends Application {
    private Label messageBar;
    private Scene scene;
    private Rectangle rect;
    private Rectangle movingRect;

    public GameApp() {

    }

    @Override
    public void init() throws Exception {
        System.out.println("Initializing application");
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Starting application");

        Image image = new Image("file:assets/scenes/schoolyard.png");
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setSmooth(true);
        iv.setCache(true);

        rect = new Rectangle();
        rect.setX(635);
        rect.setY(350);
        rect.setWidth(50);
        rect.setHeight(165);
        rect.setStroke(Color.valueOf("#00ffff"));
        rect.setFill(Color.grayRgb(1,0.5));

        Group root = new Group();
        scene = new Scene(root);
        scene.setFill(Color.BLACK);
        scene.setCursor(Cursor.OPEN_HAND);

        rect.setOnMouseEntered(e -> {scene.setCursor(Cursor.HAND);});
        rect.setOnMouseExited(e -> {scene.setCursor(Cursor.OPEN_HAND);});


        // Create a rectangle that moves on the screen
        movingRect = new Rectangle();
        movingRect.setX(940);
        movingRect.setY(540);
        movingRect.setWidth(80);
        movingRect.setHeight(200);
        movingRect.setFill(Color.valueOf("#ffa500"));


        StackPane hbox = new StackPane();
        hbox.getChildren().add(iv);
        root.getChildren().add(hbox);
        root.getChildren().add(rect);
        root.getChildren().add(movingRect);


        messageBar = new Label("Label 1");
        messageBar.setText("Click anywhere");
        messageBar.setStyle("-fx-font: bolder 36px sans-serif; -fx-text-fill: #ffea00");
        StackPane.setAlignment(messageBar, Pos.BOTTOM_CENTER);
        StackPane.setMargin(messageBar, new Insets(0,0,100,0));
        hbox.getChildren().add(messageBar);


        // resets the message bar back to default after 2 seconds
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000),
                event -> {messageBar.setText("Click anywhere");
            System.out.println("Message bar text back to default");}));


        AtomicBoolean objSelected = new AtomicBoolean(false);

        scene.setOnMouseClicked(e -> {
            // stop the message bar reset in case it's still executing
            timeline.stop();

            double x = e.getX();
            double y = e.getY();

            if (rect.contains(x, y)) {
                messageBar.setText("This door is locked");
            } else {
                messageBar.setText("Nothing interesting here");
            }

            if (e.getButton() == MouseButton.PRIMARY) {
                if (movingRect.contains(x, y)) {
                    movingRect.setFill(Color.valueOf("#48eb67"));
                    objSelected.set(true);
                    messageBar.setText("Character selected");
                    System.out.println("objSelected = " +  objSelected);
                } else {
                    movingRect.setFill(Color.valueOf("#ffa500"));
                    objSelected.set(false);
                    System.out.println("objSelected = " + objSelected);
                }
            } else if (e.getButton() == MouseButton.SECONDARY) {
                if (objSelected.get()) {
                    System.out.println("RMB click");
                    movingRect.setX(e.getX()-movingRect.getWidth()/2);
                    movingRect.setY(e.getY()-movingRect.getHeight());
                    messageBar.setText("Moving to " + movingRect.getX() + "," + movingRect.getY());
                }
            }

            // Reset the message bar back to default after 2 seconds
            timeline.play();
        });


        stage.setTitle("School 21");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setResizable(true);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stopping application");
    }

    public static void main(String[] args) {
        launch(args);
    }
}