package ex2.container;

import ex2.State;
import ex2.Temperature;

public class Tupperware extends Container{
    public Tupperware(){
        super(State.Solid, Temperature.WARM);
    }
}
