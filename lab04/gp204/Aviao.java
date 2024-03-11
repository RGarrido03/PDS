package lab03;

public class Aviao {
    private int[][] seatsTuristic;
    private int[][] seatsExecutive;

    public Aviao(int seatsTuristic[][], int seatsExecutive[][]) {
        this.seatsTuristic = seatsTuristic;
        this.seatsExecutive = seatsExecutive;
    }

    // Getters
    // ------------------------------------------------------------------------------
    public int[][] getSeatsTuristic() {
        return seatsTuristic;
    }

    public int[][] getSeatsExecutive() {
        return seatsExecutive;
    }

    // Setters
    // ------------------------------------------------------------------------------
    public void setSeatsTuristic(int[][] seatsTuristic) {
        this.seatsTuristic = seatsTuristic;
    }

    public void setSeatsExecutive(int[][] seatsExecutive) {
        this.seatsExecutive = seatsExecutive;
    }

    // Methods
    // ------------------------------------------------------------------------------

    // Número de lugares na classe turística
    public int numSeatsTuristic() {
        // 3x2 corresponde a 3 filas com 2 lugares em cada fila
        return seatsTuristic.length * seatsTuristic[0].length;
    }

    // Número de lugares na classe executiva
    public int numSeatsExecutive() {
        // Classe executiva é opcional
        if (seatsExecutive == new int[0][0]) {
            return 0;
        }
        return seatsExecutive.length * seatsExecutive[0].length;
    }

    // Número total de lugares no avião
    public int numSeats() {
        return numSeatsTuristic() + numSeatsExecutive();
    }

    // Número total de lugares disponíveis na classe turística
    public int numSeatsAvailableTuristic() {
        int count = 0;
        for (int i = 0; i < seatsTuristic.length; i++) {
            for (int j = 0; j < seatsTuristic[i].length; j++) {
                if (seatsTuristic[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // Número total de lugares disponíveis na classe executiva
    public int numSeatsAvailableExecutive() {
        if (seatsExecutive == new int[0][0]) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < seatsExecutive.length; i++) {
            for (int j = 0; j < seatsExecutive[i].length; j++) {
                if (seatsExecutive[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // Número total de lugares disponíveis no avião
    public int numSeatsAvailable() {
        return numSeatsAvailableTuristic() + numSeatsAvailableExecutive();
    }

    // Número de lugares reservados na classe turística
    public int occupiedSeatsTuristic() {
        return numSeatsTuristic() - numSeatsAvailableTuristic();
    }

    // Número de lugares reservados na classe executiva
    public int occupiedSeatsExecutive() {
        return numSeatsExecutive() - numSeatsAvailableExecutive();
    }

    // Número de lugares reservados no avião
    public int occupiedSeats() {
        return numSeats() - numSeatsAvailable();
    }

    // toString
    public String toString() {
        return "Aviao [seatsExecutive=" + seatsExecutive + ", seatsTuristic=" + seatsTuristic + "]";
    }
}
