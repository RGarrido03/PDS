package ex3.startypes;

import java.awt.*;

public class AStar extends StarType {

    public AStar(int x, int y) {
        super(StarFactory.getCommonProps(2, Color.WHITE), x, y);
        this.description = "This is a long description of the A type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
