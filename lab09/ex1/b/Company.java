package ex1.b;

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