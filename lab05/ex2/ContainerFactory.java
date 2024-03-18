package ex2;

public class ContainerFactory {

    public static Container create(State state, Temperature temperature) {

        return switch(state + temperature){
            case "BeverageCOLD" -> new FruitJuice("Orange");
            case "BeverageWARM" -> new Milk();
            case "MeatCOLD" -> new Tuna();
            case "MeatWORM" -> new Pork();
            default -> null;

    }
