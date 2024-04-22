package ex1;

import ex1.EmployeeType.Manager;
import ex1.EmployeeType.TeamLeader;
import ex1.EmployeeType.TeamMember;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Employee employee1 = new Employee("Bruno");
        Employee employee2 = new Employee("Lopes");
        Employee employee3 = new Employee("Ruben");

        employee1.start(LocalDateTime.now().minusYears(5));
        employee1.terminate(LocalDateTime.now().plusYears(5));

        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee3);

        employee2.start(LocalDateTime.now().minusYears(5));
        employee2.terminate(LocalDateTime.now().plusYears(5));

        TeamMember teamMember = new TeamMember(new TeamLeader(new Manager(employee1)));
        TeamLeader teamLeader = new TeamLeader(employee2);
        Manager manager = new Manager(employee3);

        TeamLeader teamLeader1 = new TeamLeader(employee2);

        teamMember.colaborate();
        teamLeader1.plan();
        manager.manage();

        teamLeader.plan();


    }
}