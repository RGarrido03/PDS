package lab3.ex2;

import java.util.List;
import java.util.stream.Collectors;

public record Reservation(int id, ClassType type, List<Integer[]> seats) {
    public String toString() {
        return id + " = " + seats.stream()
                                 .map(object -> object[0] + "" + ('A' + object[1]))
                                 .collect(Collectors.joining("|"));
    }
}
