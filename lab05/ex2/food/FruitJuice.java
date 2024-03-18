package ex2.food;

import ex2.State;
import ex2.Temperature;

public class FruitJuice extends Food {

    private final String type;

    public FruitJuice(String type){
        super(State.Liquid, Temperature.COLD);
        this.type = type;
    }

    public String getType() {return type;}

    public String toString() {
        return getClass().getSimpleName() + ": " + getType() + " State " + getState() + ", Temperature " + getTemperature();
    }
}
