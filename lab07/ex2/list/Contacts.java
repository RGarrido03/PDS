package ex2.list;

import ex2.Contact;
import ex2.storage.ContactsStorageInterface;

import java.util.ArrayList;
import java.util.List;

public class Contacts implements ContactsInterface {
    private List<Contact> contactsList = new ArrayList<>();
    private ContactsStorageInterface store;

    public Contacts() {
    }

    public Contacts(ContactsStorageInterface store) {
        this.store = store;
    }

    @Override
    public void openAndLoad(ContactsStorageInterface store) {
        if (this.store == null) {
            this.store = store;
        }
        List<Contact> contacts = store.loadContacts();
        contactsList.addAll(contacts);
    }

    @Override
    public void saveAndClose() {
        this.store.saveContacts(contactsList);
        contactsList = new ArrayList<>();
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(contactsList);
        contactsList = new ArrayList<>();
    }

    @Override
    public boolean exist(Contact contact) {
        return contactsList.contains(contact);
    }

    @Override
    public Contact getByName(String name) {
        return contactsList.stream().filter(contact -> contact.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public boolean add(Contact contact) {
        return contactsList.add(contact);
    }

    @Override
    public boolean remove(Contact contact) {
        return contactsList.remove(contact);
    }
}
