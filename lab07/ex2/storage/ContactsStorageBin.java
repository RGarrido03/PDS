package ex2.storage;

import ex2.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactsStorageBin implements ContactsStorageInterface {
    @Override
    public List<Contact> loadContacts() {
        try {
            List<Contact> contacts = new ArrayList<>();

            FileInputStream fis = new FileInputStream("contacts.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    contacts.add((Contact) ois.readObject());
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("Class not found" + e.getMessage());
                }
            }

            return contacts;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try {
            FileOutputStream fos = new FileOutputStream("contacts.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Contact contact : list) {
                oos.writeObject(contact);
            }

            oos.flush();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
