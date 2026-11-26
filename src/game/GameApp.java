package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameApp extends Application {
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

        Rectangle rectangle = new Rectangle();
        rectangle.setX(635);
        rectangle.setY(350);
        rectangle.setWidth(50);
        rectangle.setHeight(165);
        rectangle.setStroke(Color.valueOf("#00ffff"));
        rectangle.setFill(Color.grayRgb(1,0.5));

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        scene.setCursor(Cursor.OPEN_HAND);

        rectangle.setOnMouseEntered(e -> {scene.setCursor(Cursor.HAND);});
        rectangle.setOnMouseExited(e -> {scene.setCursor(Cursor.OPEN_HAND);});

        HBox hbox = new HBox();
        hbox.getChildren().add(iv);
        root.getChildren().add(hbox);
        root.getChildren().add(rectangle);

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