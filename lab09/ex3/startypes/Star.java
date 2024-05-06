package ex3.startypes;

import java.awt.*;

public class Star {
    private final int x;
    private final int y;
    private final StarType starType;

    public Star(StarType starType, int x, int y) {
        this.starType = starType;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        starType.draw(g, x, y);
    }
}
