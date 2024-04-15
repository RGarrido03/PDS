package ex1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();
        Registos registos = new Registos();


        Employee employee1 = new Employee("Bruno", 15, 1400);
        Employee employee2 = new Employee("Lopes", 12, 145);
        Employee employee3 = new Employee("Rafael", 16, 600);

        Empregado empregado1 = new Empregado("Lopes", "Rafael", 400, 1600);
        Empregado empregado2 = new Empregado("issop", "Rafael1", 500, 1600);
        Empregado empregado3 = new Empregado("prota", "Rafael2", 600, 1600);


        database.addEmployee(employee1);
        database.addEmployee(employee2);
        database.addEmployee(employee3);

        registos.insere(empregado1);
        registos.insere(empregado2);
        registos.insere(empregado3);

        registos.isEmpregado(650);


        // Adapter
        database.addEmployee(new Adapter(empregado1));
        database.addEmployee(new Adapter(empregado2));
        database.addEmployee(new Adapter(empregado3));

        System.out.println("\n------------Registos------------");
        System.out.println(registos.listaDeEmpregados());

        System.out.println("\n------------Database------------");
        System.out.println(Arrays.toString(database.getAllEmployees()));

        System.out.println("\n------------Registos atualizado------------");
        registos.remove(400);
        System.out.println(registos.listaDeEmpregados());

        System.out.println("\n------------Database atualizada------------");
        database.deleteEmployee(12);
        database.deleteEmployee(new Adapter(empregado2).getEmpNum());

        System.out.println(registos.listaDeEmpregados());
        System.out.println(Arrays.toString(database.getAllEmployees()));



    }
}
