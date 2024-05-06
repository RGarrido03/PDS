package ex1.a;

import java.util.List;

public class SharkCompany {
    public static void main(String[] args) {

        Company shark = new Company();
        Company.user = User.OWNER;


        shark.admitPerson("Maria Silva", 1000);
        shark.admitPerson("Manuel Pereira", 900);
        shark.admitPerson("Aurora Machado", 1200);
        shark.admitPerson("Augusto Lima", 1100);
        List<Employee> sharkEmps = shark.employees();

        Company.user = User.COMPANY;

        for (Employee e : sharkEmps)
            // "talking to strangers", but this is not a normal case
            try {
                System.out.println(e.getBankAccount().balance());
            } catch (IllegalAccessException exception){
                System.out.println("No Permissions");
            }
        shark.paySalaries(1);
        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);

            try {
                System.out.println(e.getBankAccount().balance());
            } catch (IllegalAccessException exception){
                System.out.println("No Permissions");
            }
        }
    }
}