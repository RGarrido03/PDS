package ex1.EmployeeType;

import ex1.Employee;
import ex1.EmployeeDecorator;
import ex1.EmployeeInterface;

public class Manager extends EmployeeDecorator {

    public Manager(EmployeeInterface emp) {
        super(emp);
    }

    public void manage() {
        System.out.println("Manager manage");
    }
}
