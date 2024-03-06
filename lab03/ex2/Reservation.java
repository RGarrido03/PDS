package lab3.ex2;

import java.util.List;

public record Reservation(int id, ClassType type, List<Integer[]> seats) {
}
