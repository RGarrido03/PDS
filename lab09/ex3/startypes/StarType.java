package ex3.startypes;

import java.awt.*;

public class StarType {
    private final int size;
    private final Color color;
    protected String description;
    protected Float[] physicalProperties;

    public StarType(int size, Color color, String description, Float[] physicalProperties) {
        this.size = size;
        this.color = color;
        this.description = description;
        this.physicalProperties = physicalProperties;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(this.getColor());
        g.fillOval(x, y, this.getSize(), this.getSize());
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}
