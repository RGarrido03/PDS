package ex2;

import ex2.container.*;

public class ContainerFactory {

    public static Container create(Portion portion) {

        return switch (portion.getState() + portion.getTemperature().toString()) {
            case "LiquidCOLD" -> new PlasticBottle(portion);
            case "SolidWARM" -> new TermicBottle(portion);
            case "LiquidWARM" -> new Tupperware(portion);
            case "SolidCOLD" -> new PlasticBag(portion);
            default -> null;
        };

    }
}
