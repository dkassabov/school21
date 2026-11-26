package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ClickableArea {
    private final double x, y, w, h;
    private final String id;
    private final Runnable action;

    public ClickableArea(double x, double y, double w, double h,
                         String id, Runnable action) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.id = id;
        this.action = action;
    }

    public boolean contains(double px, double py) {
        return px >= x && px <= x + w &&
                py >= y && py <= y + h;
    }

    public void trigger() {
        if (action != null) {
            action.run();
        }
    }

    // for debugging: show the area
    public Rectangle makeDebugNode() {
        Rectangle r = new Rectangle(x, y, w, h);
        r.setFill(Color.color(1, 1, 1, 0.15));
        r.setStroke(Color.WHITE);
        r.setStrokeWidth(1.0);
        return r;
    }

    public String getId() {
        return id;
    }
}
