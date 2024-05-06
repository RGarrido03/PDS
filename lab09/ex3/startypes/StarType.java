package ex3.startypes;

import java.awt.*;

public abstract class StarType {

    private final CommonProperties commonProperties;
    private final int x;
    private final int y;
    protected String description;
    protected Float[] physicalProperties;

    protected StarType(CommonProperties commonProperties, int x, int y) {
        this.commonProperties = commonProperties;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(commonProperties.getColor());
        g.fillOval(x, y, commonProperties.getSize(), commonProperties.getSize());
    }

}
