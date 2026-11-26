package game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SceneView extends StackPane implements WindowParams {

    private final String sceneId;
    private final List<ClickableArea> hotspots = new ArrayList<>();
    private final Pane playfield;  // where background + hotspots go
    private final Label messageBar; // simple feedback at bottom

    public SceneView(String sceneId) {
        this.sceneId = sceneId;

        // main layout is a stack: [playfield] + [message bar at bottom]
        this.playfield = new Pane();
        this.playfield.setPrefSize(WIDTH, HEIGHT);

        // load background (fallback to a solid color if not found)
        ImageView bg = createBackground();
        playfield.getChildren().add(bg);

        // define some clickable areas for this scene
        defineHotspots();

        // add hotspot nodes (for debugging we can show them semi-transparent)
        for (ClickableArea area : hotspots) {
            Rectangle debugRect = area.makeDebugNode();
            playfield.getChildren().add(debugRect);
        }

        // message bar
        messageBar = new Label("Click on something...");
        messageBar.setTextFill(Color.WHITE);
        messageBar.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-padding: 6px;");
        StackPane.setAlignment(messageBar, Pos.BOTTOM_CENTER);

        // handle clicks on the whole scene
        playfield.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            handleClick(x, y);
        });

        this.getChildren().addAll(playfield, messageBar);
    }

    private ImageView createBackground() {
        // try to load /scenes/<sceneId>.png
        Image img;
        try {
            // for scene loading
            String path = "/scenes/" + sceneId + ".png";
            var url = getClass().getResource(path);
            assert url != null;
            img = new Image(url.toExternalForm());
        } catch (Exception e) {
            System.out.println("Error loading image " + sceneId + ".png");
            e.printStackTrace();
            img = null;
        }

        ImageView iv = new ImageView();
        if (img != null && !img.isError()) {
            iv.setImage(img);
            iv.setFitWidth(WIDTH);
            iv.setFitHeight(HEIGHT);
            iv.setPreserveRatio(false);
        } else {
            // no image? just a colored rectangle behind everything
            Rectangle r = new Rectangle(WIDTH, HEIGHT, BACKGROUND_COLOR);
            // we can't return a Rectangle here, so wrap
            iv.setFitWidth(0); // invisible, but we added main bg to pane above
            playfield.getChildren().addFirst(r);
        }
        return iv;
    }

    private void defineHotspots() {
        // example: a door on the right
        hotspots.add(new ClickableArea(
                820, 200, 100, 180, "door", () -> {
            setMessage("The door is locked.");
        }));

        // example: table in the middle
        hotspots.add(new ClickableArea(
                380, 300, 180, 100, "table", () -> {
            setMessage("You found a note on the table.");
        }));
    }

    private void handleClick(double x, double y) {
        for (ClickableArea area : hotspots) {
            if (area.contains(x, y)) {
                area.trigger();
                return;
            }
        }
        setMessage("Nothing interesting here.");
    }

    private void setMessage(String msg) {
        messageBar.setText(msg);
    }
}
