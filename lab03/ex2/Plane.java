package lab3.ex2;

public class Plane {
    private final SeatClass executive;
    private final SeatClass touristic;

    public Plane(String touristic) {
        this(touristic, null);
    }

    public Plane(String touristic, String executive) {
        String[] layout;

        layout = touristic.split("x");
        this.touristic = new SeatClass(
                ClassType.TOURISTIC,
                Integer.parseInt(layout[0]),
                Integer.parseInt(layout[1])
        );

        if (executive == null) {
            this.executive = null;
            return;
        }

        layout = executive.split("x");
        this.executive = new SeatClass(
                ClassType.EXECUTIVE,
                Integer.parseInt(layout[0]),
                Integer.parseInt(layout[1])
        );
    }

    public SeatClass getExecutive() {
        return executive;
    }

    public SeatClass getTouristic() {
        return touristic;
    }
}
