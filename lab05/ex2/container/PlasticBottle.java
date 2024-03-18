package ex2.container;

import ex2.State;
import ex2.Temperature;

public class PlasticBottle extends Container{
    public PlasticBottle(){
        super(State.Liquid, Temperature.COLD);
    }
}
