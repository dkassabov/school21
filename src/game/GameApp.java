package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application implements WindowParams {

    @Override
    public void start(Stage stage) {
        // create the scene manager
        SceneManager sceneManager = new SceneManager();

        // load the first scene
        Scene scene = sceneManager.loadScene("test_scene");

        stage.setTitle("School 21");
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
