package ex2.container;

import ex2.State;
import ex2.Temperature;

public class TermicBottle extends Container{

    public TermicBottle(){
        super(State.Liquid, Temperature.WARM);
    }
}
