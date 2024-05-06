package ex3.startypes;

import java.awt.*;

public class OStar extends StarType {

    public OStar(int x, int y) {
        super(StarFactory.getCommonProps(5, new Color(225, 250, 250)), x, y);
        this.description = "This is a long description of the O type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
