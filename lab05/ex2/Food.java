package ex2;

public class Food implements Portion{

    private State state;
    private Temperature temperature;

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
        return getClass().getSimpleName() + state.toString() + " " + temperature;
    }
}
