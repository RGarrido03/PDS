package ex2.container;

import ex2.Portion;
import ex2.State;
import ex2.Temperature;

public class PlasticBag extends Container{

    private final Portion portion;

    public PlasticBag(Portion portion){
        super(State.Solid, Temperature.COLD);
        this.portion = portion;
    }

    @Override
    public String toString() {
        return super.toString() + portion.toString();
    }
}
