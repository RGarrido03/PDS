package ex2;

import ex2.FacadeClasses.Insurance;
import ex2.FacadeClasses.Parking;
import ex2.FacadeClasses.SocialSecurity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Company {

    public static User user;
    private List<Employee> emps = new ArrayList<>();
    private Person person;
    private double totalSalaries;

    // adapted for Facade design pattern
    public void admitEmployee(Person person, double salary) {
        Employee e = new Employee(person, salary);
        emps.add(e);
        SocialSecurity.regist(person);
        Insurance.regist(person);
        createEmplyeeCard(person);
        System.out.println();

        // Add salary Employee to total of salaries
        totalSalaries += salary;
        int nEmployees = emps.size();

        if (salary > totalSalaries / emps.size()){
            Parking.allow(person);
        } else {
            System.err.println( person.getName() + " don't have enough salary to have access to the parking lot");
        }
    }

    private void createEmplyeeCard(Person person) {
        System.out.println("Emplyee card created for " + person.getName());
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