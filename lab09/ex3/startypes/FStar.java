package ex3.startypes;

import java.awt.*;

public class FStar extends StarType {

    public FStar(int x, int y) {
        super(StarFactory.getCommonProps(2, new Color(255, 255, 245)), x, y);
        this.description = "This is a long description of the F type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
