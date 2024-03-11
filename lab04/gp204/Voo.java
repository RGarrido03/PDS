package lab03;

import java.util.*;;

public class Voo {
    private String codigo;
    private Aviao aviao;
    private int reservas;

    public Voo(String codigo, Aviao aviao) {
        this.codigo = codigo;
        this.aviao = aviao;
        this.reservas = 0;
    }

    public int getNumReservas() {
        return reservas;
    }

    public String getCodigo() {
        return codigo;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public boolean reservar(String classe, int numlugares) {
        int[][] seats = null;
        if (classe.equals("Turistic")) {
            if (numlugares > aviao.numSeatsAvailableTuristic()) {
                return false;
            }
            seats = aviao.getSeatsTuristic();
        } else if (classe.equals("Executive")) {
            if (numlugares > aviao.numSeatsAvailableExecutive()) {
                return false;
            }
            seats = aviao.getSeatsExecutive();
        } else {
            return false;
        }
        int filaVazia = -1;
        int filas = seats.length;
        int bancosporFila = seats[0].length;
        for (int fila = 0; fila < filas; fila++) {
            boolean vazia = true;
            for (int banco = 0; banco < bancosporFila; banco++) {
                if (seats[fila][banco] != 0) {
                    vazia = false;
                    break;
                }
            }

            if (vazia) {
                filaVazia = fila;
                break;
            }
        }

        int count = 0;
        if (filaVazia != -1) {
            for (int fila = filaVazia; fila < filas; fila++) {
                for (int banco = 0; banco < bancosporFila; banco++) {
                    if (seats[fila][banco] == 0) {
                        seats[fila][banco] = this.reservas + 1;
                        count++;
                    }
                    if (count == numlugares) {
                        break;
                    }
                }
                if (count == numlugares) {
                    break;
                }
            }
        } else {
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j] == 0) {
                        seats[i][j] = this.reservas + 1;
                        count++;
                    }
                    if (count == numlugares) {
                        break;
                    }
                }
                if (count == numlugares) {
                    break;
                }
            }
            if (count < numlugares) {
                return false;
            }
        }
        if (classe.equals("Turistic")) {
            aviao.setSeatsTuristic(seats);
        } else {
            aviao.setSeatsExecutive(seats);
        }
        this.reservas += 1;
        return true;
    }

    public void mapaReserva(String codigovoo) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[][] seatsTuristic = aviao.getSeatsTuristic();
        int[][] seatsExecutive = aviao.getSeatsExecutive();
        int filasTuristic = seatsTuristic.length;
        int filasExecutive = seatsExecutive.length;
        int bancosTuristic = seatsTuristic[0].length;
        int bancosExecutive=0;
        if (filasExecutive != 0) {
            bancosExecutive = seatsExecutive[0].length;
        } else {
            bancosExecutive = 0;
        }

        System.out.print("\t");
        for (int num = 1; num <= filasExecutive + filasTuristic; num++) {
            System.out.print(num + "\t");
        }
        System.out.print("\n");
        for (int banco = 0; banco < Math.max(bancosTuristic, bancosExecutive); banco++) {
            System.out.print(abc.charAt(banco));
            for (int fila = 0; fila < filasExecutive + filasTuristic; fila++) {
                if (fila < filasExecutive) {
                    if (banco < bancosExecutive) {
                        System.out.print("\t" + seatsExecutive[fila][banco]);
                    } else {
                        System.out.print("\t");
                    }
                } else {
                    if (filasExecutive > 0) {
                        System.out.print("\t" + seatsTuristic[fila - filasExecutive][banco]);
                    } else {
                        System.out.print("\t" + seatsTuristic[fila][banco]);
                    }

                }

            }
            System.out.println();
        }
    }

    public boolean cancelarReserva(String codvoo, int reserva) {
        int[][] seatsTuristic = aviao.getSeatsTuristic();
        int[][] seatsExecutive = aviao.getSeatsExecutive();
        boolean encontrado=false;
        if (reserva > this.reservas) {
            return false;
        }
        // Procurar o assento e cancelar a reserva se encontrado
        for (int i = 0; i < seatsExecutive.length; i++) {
            for (int j = 0; j < seatsExecutive[i].length; j++) {
                if (seatsExecutive[i][j] == reserva) {
                    seatsExecutive[i][j] = 0; // Marca o assento como disponível (0 representa disponível
                    encontrado=true;
                }
            }
        }
        for (int i = 0; i < seatsTuristic.length; i++) {
            for (int j = 0; j < seatsTuristic[i].length; j++) {
                if (seatsTuristic[i][j] == reserva) {
                    seatsTuristic[i][j]=0;
                    encontrado=true;
                }
            }
        }

        if (!encontrado) {
            return false;
        } else return true;
    }

    @Override
    public String toString() {
        return "Código de voo: " + this.codigo;
    }
}
