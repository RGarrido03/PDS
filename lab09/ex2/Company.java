package ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Company {

    public static User user;
    private List<Employee> emps = new ArrayList<>();
    private Person person;

    public void admitEmployee(Person person, double salary) {
        Employee e = new Employee(person, salary);
        emps.add(e);
        SocialSecurity.regist(person);
        Insurance.regist(person);
        createEmplyeeCard(person);

        if (salary > averageSalaries()){
            Parking.allow(person);
        }
    }

    private double averageSalaries() {
        int nEmployees = emps.size();
        double sum = 0;

        for (Employee e : emps) {
            sum += e.getSalary();
        }

        if (nEmployees > 0) {
            return sum / nEmployees;
        } else {
            return 0;
        }
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            e.deposit();
        }
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}