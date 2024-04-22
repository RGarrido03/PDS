package ex1;

import java.time.LocalDateTime;

public class EmployeeDecorator implements EmployeeInterface{

    private EmployeeInterface employee;

    public EmployeeDecorator(EmployeeInterface emp) {
        this.employee = emp;
    }

    @Override
    public void start(LocalDateTime date) {
        employee.start(date);
    }

    @Override
    public void terminate(LocalDateTime date) {
        employee.terminate(date);
    }

    @Override
    public boolean work() {
        return employee.work();
    }

    @Override
    public String toString() {
        return "EmployeeDecorator{" +
                "employee=" + employee +
                '}';
    }
}
