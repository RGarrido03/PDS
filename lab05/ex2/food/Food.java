package ex2.food;

import ex2.Portion;
import ex2.State;
import ex2.Temperature;

public class Food implements Portion {

    private final State state;
    private final Temperature temperature;


    public Food(State state, Temperature temperature) {
        this.state = state;
        this.temperature = temperature;
    }

    public State getState() {
        return state;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public String toString() {
        return getClass().getSimpleName() + ": State " +state.toString() + ", Temperature " + temperature;
    }
}
