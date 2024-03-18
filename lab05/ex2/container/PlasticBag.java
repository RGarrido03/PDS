package ex2.container;

import ex2.State;
import ex2.Temperature;

public class PlasticBag extends Container{
    public PlasticBag(){
        super(State.Solid, Temperature.COLD);
    }
}
