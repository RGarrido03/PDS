package ex1.EmployeeType;

import ex1.Employee;
import ex1.EmployeeDecorator;
import ex1.EmployeeInterface;

public class TeamMember extends EmployeeDecorator {

    public TeamMember(EmployeeInterface emp) {
        super(emp);
    }

    public void colaborate(){
        System.out.println("Colaborating Employee");
    }
}
