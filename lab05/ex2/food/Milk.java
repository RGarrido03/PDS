package ex2.food;

import ex2.State;
import ex2.Temperature;

public class Milk extends Food {

    public Milk(){
        super(State.Liquid, Temperature.WARM);
    }
}
