package ex1;

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
        out.println("\nQuestion 2 (output example) ----------------------------------\n");

        ToShare market = new ToShare();

        market.setProductsReader(new ProductsReaderHardcodedProducts());
        market.importProductsFromProductsReader();

        market.setProductsReader(new ProductsReaderFromTXTFile("products.txt"));
        market.importProductsFromProductsReader();

        out.println("--- All Products ---\nTotal: " + market.totalProducts());
        for (Product item : market)
            out.println("\t"+item);
    }


    private static void question3(PrintStream out) {
        out.println("\nQuestion 3) ----------------------------------\n");
        // Completar
        Client u1 = new Client("187", "Peter Pereira");
        Client u2 = new Client("957", "Anne Marques");
        Client u3 = new Client("9257", "Anneadsfas Marques");

        ToShare shareIt = new ToShare();

        Product[] cars = {
                new Car("ZA11ZB", "Tesla, Grey, 2021", 100),
                new Van("AA22BB", "Chevrolet Chevy, 2020", 180),
                new Motorcycle("ZA33ZB", "Touring, 750, 2022", 85),
                new Car("BB44ZB", "Ford Mustang, Red, 2021", 150),
        };
        for (Product item : cars) {
            shareIt.add(item);
        }

        out.println("Shared products: " + shareIt.totalSharedProducts());
        shareIt.share("ZA11ZB", u1); 	// true
        shareIt.share("ZA11ZB", u2);  	// false (já está emprestada)

        out.println("Shared products: " + shareIt.totalSharedProducts());
        shareIt.giveBack("ZA11ZB");  	// true
        out.println("Shared products: " + shareIt.totalSharedProducts());
        shareIt.giveBack("ZA11ZB");  	// true
        out.println("Shared products: " + shareIt.totalSharedProducts());
    }
}

