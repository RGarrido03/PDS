package ex3.startypes;

import java.awt.*;

public class CommonProperties {
    private final int size;
    private final Color color;

    CommonProperties(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}
