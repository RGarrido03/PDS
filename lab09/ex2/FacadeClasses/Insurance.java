package ex2.FacadeClasses;

import ex2.Person;

public class Insurance {
    public static void regist(Person person){
        System.out.println("Insurance Registered of " + person.getName());
    }
}
