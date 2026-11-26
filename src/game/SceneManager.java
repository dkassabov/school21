package game;

import javafx.scene.Scene;

public class SceneManager {

    // in the future I can keep a Map<String, RoomDefinition> here

    public Scene loadScene(String sceneId) {
        // for now, hardcode 1 scene
        SceneView scene = new SceneView(sceneId);
        return new Scene(scene);
    }

    // TODO: Scene Navigation
    public void changeScene(String sceneId) {

    }
}
