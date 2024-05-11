package ex2.FacadeClasses;

import ex2.Person;

public class SocialSecurity {
    public static void regist(Person person) {
        System.out.println("Register " + person.getName() + "in SS.");
    }
}
