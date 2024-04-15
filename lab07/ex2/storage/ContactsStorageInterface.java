package ex2.storage;

import ex2.Contact;

import java.util.List;

public interface ContactsStorageInterface {
    List<Contact> loadContacts();

    boolean saveContacts(List<Contact> list);
}
