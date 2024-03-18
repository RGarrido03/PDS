package ex2;

import ex2.container.*;

public class ContainerFactory {

    public static Container create(Portion portion) {

        return switch (portion.getState() + portion.getTemperature().toString()) {
            case "LiquidCOLD" -> new PlasticBottle();
            case "SolidWARM" -> new TermicBottle();
            case "LiquidWARM" -> new Tupperware();
            case "SolidCOLD" -> new PlasticBag();
            default -> null;
        };

    }
}
