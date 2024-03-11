package lab03;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ex02Main {
    public static void main(String[] args) {
        String input;
        String[] opt;
        ArrayList<Voo> voos = new ArrayList<Voo>();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Escolha uma opção: (H para ajuda)");
            input = sc.nextLine();
            opt = input.split(" ");
            handleOption(opt, voos);

        } while (opt[0].charAt(0) != 'Q');
        sc.close();
    }

    public static void handleOption(String[] list, ArrayList<Voo> voos) {
        String opt = list[0].toUpperCase();
        switch (opt) {
            case "H":
                System.out.println("I <filename> - Inserir um novo voo a partir de um ficheiro");
                System.out.println("M <flight_code> - Exibe mapa de reservas");
                System.out.println("F <flight_code num_seats_executive num_seats_tourist> - Acrescentar novo voo");
                System.out.println("R <flight_code class(T or E) number_seats>- Acrescentar reserva a um voo");
                System.out.println("C <reservation_code> - Cancelar reserva de um voo");
                System.out.println("Q - Sair do programa");
                break;

            case "I":
                if (list.length != 2) {
                    System.out.println("Numero de argumentos invalido");
                    break;
                }
                String filename = list[1];
                String codigovoo = "";
                try {
                    File file = new File(filename);
                    Scanner sc = new Scanner(file);
                    String[] firstline = sc.nextLine().split(" ");
                    int[][] seatsTuristice, seatsExecutive;
                    codigovoo = firstline[0].substring(1);
                    if (firstline.length == 2) {
                        seatsTuristice = new int[Integer.parseInt(firstline[1].split("x")[0])][Integer
                                .parseInt(firstline[1].split("x")[1])];
                        seatsExecutive = new int[0][0];
                    } else {
                        seatsExecutive = new int[Integer.parseInt(firstline[1].split("x")[0])][Integer
                                .parseInt(firstline[1].split("x")[1])];
                        seatsTuristice = new int[Integer.parseInt(firstline[2].split("x")[0])][Integer
                                .parseInt(firstline[2].split("x")[1])];
                    }

                    Aviao aviao = new Aviao(seatsTuristice, seatsExecutive);
                    Voo voo = new Voo(codigovoo, aviao);
                    String failedres = "";
                    while (sc.hasNextLine()) {
                        ;
                        String data = sc.nextLine();
                        String[] line = data.split(" ");
                        if (line[0].equals("T")) {
                            if (!voo.reservar("Turistic", Integer.parseInt(line[1]))) {
                                failedres = "T " + line[1];
                            }
                        } else if (line[0].equals("E")) {
                            if (!voo.reservar("Executive", Integer.parseInt(line[1]))) {
                                failedres = "E " + line[1];
                            }
                        }
                    }
                    if (firstline.length == 2) {
                        System.out.println("Codigo de voo " + codigovoo + ". Lugares disponiveis: "
                                + aviao.numSeatsTuristic() + " em classe Turistica.");
                        System.out.println("Classe executiva nao disponivel neste voo");
                        if (failedres.length() > 0) {
                            System.out.println("Nao foi possivel efetuar a reserva: " + failedres);
                        }
                    } else {
                        System.out.println("Codigo de voo " + codigovoo + ". Lugares disponiveis: "
                                + aviao.numSeatsExecutive() + " em classe Executiva; " + aviao.numSeatsTuristic()
                                + " em classe Turistica.");
                        if (failedres.length() > 0) {
                            System.out.println("Nao foi possivel efetuar a reserva: " + failedres);
                        }
                    }
                    voos.add(voo);
                    sc.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;

            case "M":
                if (list.length != 2) {
                    System.out.println("Apenas um codigo de voo deve ser fornecido");
                    break;
                }
                String code = list[1];
                for (Voo voo : voos) {
                    System.out.println(voo.getCodigo());
                }
                Voo flight = getVoo(code, voos);
                if (flight == null) {
                    System.out.println("Codigo de voo invalido");
                    break;
                }
                flight.mapaReserva(code);

                break;

            case "F":
                String codvoo = list[1];
                int[][] seatsTuristic, seatsExecutive;
                if (list.length == 3) {
                    seatsTuristic = new int[Integer.parseInt(list[2].split("x")[0])][Integer
                            .parseInt(list[2].split("x")[1])];
                    seatsExecutive = new int[0][0];
                } else {
                    seatsExecutive = new int[Integer.parseInt(list[2].split("x")[0])][Integer
                            .parseInt(list[2].split("x")[1])];
                    seatsTuristic = new int[Integer.parseInt(list[3].split("x")[0])][Integer
                            .parseInt(list[3].split("x")[1])];
                }
                Aviao aviao = new Aviao(seatsTuristic, seatsExecutive);
                Voo voo = new Voo(codvoo, aviao);
                voos.add(voo);
                System.out.println("Voo " + codvoo + " criado com sucesso");
                break;

            case "R":
                String codvooR = list[1];
                Voo flightR = getVoo(codvooR, voos);
                
                if (flightR == null) {
                    System.out.println("Codigo de voo invalido");
                    break;
                }
                int reservas=0;
                if (list[2].equals("T")) {
                    if (!flightR.reservar("Turistic", Integer.parseInt(list[3]))) {
                        System.out.println("Não foi possível efetuar a reserva");
                        break;
                    }
                } else if (list[2].equals("E")) {
                    if (!flightR.reservar("Executive", Integer.parseInt(list[3]))) {
                        System.out.println("Não foi possível efetuar a reserva");
                        break;
                    }
                }
                reservas = flightR.getNumReservas();
                /// a funçao reservar já verifica se ha lugares disponiveis
                int[][] seats = (list[2].equals("T")) ? flightR.getAviao().getSeatsTuristic(): flightR.getAviao().getSeatsExecutive();
                String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                        "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

                System.out.print(codvooR + ":" + reservas + " = ");

                for (int fila = 0; fila < seats.length; fila++) {
                    for (int banco = 0; banco < seats[0].length; banco++) {
                        if (seats[fila][banco] == reservas) {
                            System.out.print((fila + 1) + abc[banco] + " | ");
                        }
                    }
                }

                System.out.println();
                
                break;
            case "C":
                String codvooC = list[1].split(":")[0];
                int reserva = Integer.parseInt(list[1].split(":")[1]);
                Voo flightC = getVoo(codvooC, voos);
                if (flightC == null) {
                    System.out.println("Codigo de voo invalido");
                    break;
                }

                boolean cancelado = flightC.cancelarReserva(codvooC,reserva);
                if (cancelado) {
                    System.out.println("Reserva cancelada com sucesso no voo " + codvooC);
                } else {
                    System.out.println("Não foi possível cancelar a reserva no voo " + codvooC);
                }
                break;
            case "Q":
                break;
        }
    }

    public static Voo getVoo(String code, ArrayList<Voo> voos) {
        for (Voo voo : voos) {
            if (voo.getCodigo().equals(code)) {
                return voo;
            }
        }
        return null;
    }
}
