package ex2.storage;

import ex2.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ContactsStorageTxt implements ContactsStorageInterface {
    @Override
    public List<Contact> loadContacts() {
        try (Scanner scanner = new Scanner(new File("contacts.txt"))) {
            List<Contact> contacts = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String[] contact = scanner.nextLine().split("\t");
                contacts.add(
                        new Contact(Integer.parseInt(contact[0]), contact[1], Integer.parseInt(contact[2]),
                                    contact[3]));
            }

            return contacts;
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try (PrintWriter writer = new PrintWriter("contacts.txt")) {
            list.forEach(contact -> writer.println(contact.toString()));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
