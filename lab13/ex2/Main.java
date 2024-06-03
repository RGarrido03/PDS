package ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static PrintStream printer; // to avoid duplicated prints in console

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream fl = new PrintStream("pds2022.txt");
        test(System.out); // executa e escreve na consola
        fl.println(System.getProperty("user.dir"));
        fl.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        test(fl); // executa e escreve no ficheiro
        fl.close();
    }

    private static void test(PrintStream out) {
        printer = out;
        question1(out);
        question2(out);
        question3(out);
    }

    // Nas restantes classes, para fazerem um "print" nao usam o System.out.print(...).
    // Usam antes um XIII1.printer.print(...)

    private static void question2(PrintStream out) {
        out.println("\nAlínea b) ----------------------------------\n");

        ShipOfSmallShips c1 = new ShipOfSmallShips("XX45", "Meio cheio", 6);
        c1.add(new PassengerShip("B899", "Bora", 4));
        c1.add(new PassengerShip("B878", "Riacho", 2));
        c1.add(new PassengerShip("B785", "Turista", 8));
        av.add("C01", c1);
        av.add("X01", new CargoShip("S45", "Beirão", 81));
        out.println(av.remove("C02").name() + " removed");

        for (String s : av) {
            out.println(s);
        }

    }

    // alínea c)
    private static void question3(PrintStream out) {
        out.println("\nAlínea c) ----------------------------------\n");

        RiverPort p = (RiverPort) PortFactory.getRiverPort();

        p.add("C02", new CargoShip("S101", "Quebra Molas", 1.55));
        p.add("C11", new CargoShip("S732", "SoPingas", 20.2));
        p.add("C03", new CargoShip("S923", "Madalena", 8.8));
        p.add("P54", new PassengerShip("S199", "Bananeiros", 5));
        p.add("P35", new PassengerShip("S185", "PDS All aboard", 80));
        p.add("P06", new PassengerShip("S078", "Costeiro", 9));

        p.getRiverLogger().printCurrentLogs();

        System.out.println("\nShips:");
        Iterator<String> it = p.iterator();
        while (it.hasNext()) {
            out.println(it.next());
        }
    }
}
