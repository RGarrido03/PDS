package ex1;

import java.time.LocalDateTime;
import java.util.Locale;

public class Employee implements EmployeeInterface{

    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean work;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void start(LocalDateTime date) {
        this.start = date;
    }


    @Override
    public void terminate(LocalDateTime date) {
        this.end = date;
    }

    @Override
    public boolean work() {
        return work = true;
    }
}
