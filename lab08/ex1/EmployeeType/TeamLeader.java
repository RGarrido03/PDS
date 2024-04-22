package ex1.EmployeeType;

import ex1.Employee;
import ex1.EmployeeDecorator;
import ex1.EmployeeInterface;

public class TeamLeader extends EmployeeDecorator {

    public TeamLeader(EmployeeInterface emp) {
        super(emp);
    }

    public void plan(){
        System.out.println("Plan TeamLeader");
    }
}