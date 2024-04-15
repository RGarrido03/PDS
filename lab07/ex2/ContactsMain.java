package ex2;

import ex2.list.Contacts;
import ex2.storage.ContactsStorageBin;
import ex2.storage.ContactsStorageCsv;
import ex2.storage.ContactsStorageInterface;
import ex2.storage.ContactsStorageTxt;

import java.util.List;

public class ContactsMain {
    public static void test(ContactsStorageInterface contactsStorage) {
        Contacts contacts = new Contacts(contactsStorage);

        Contact contact1 = new Contact(1, "Rúben Garrido", 910000000, "rubengarrido@ua.pt");
        Contact contact2 = new Contact(2, "Bruno Lopes", 930000000, "brunorafaellopes@ua.pt");
        Contact contact3 = new Contact(3, "Alice", 960000000, "alice@ua.pt");
        Contact contact4 = new Contact(3, "Bob", 960000000, "bob@ua.pt");

        contacts.add(contact1);
        contacts.add(contact2);

        assert contacts.exist(contact1);
        assert contacts.exist(contact2);
        assert contacts.getByName(contact1.getName()) == contact1;
        assert !contacts.exist(contact3);

        contacts.add(contact3);
        contacts.add(contact4);
        assert contacts.exist(contact3);
        assert contacts.exist(contact4);

        contacts.remove(contact4);
        assert !contacts.exist(contact4);

        contacts.saveAndClose();
    }

    public static void testImport(ContactsStorageInterface contactsStorage) {
        Contacts contacts = new Contacts(contactsStorage);
        contacts.openAndLoad(contactsStorage);

        Contact contact = contacts.getByName("Rúben Garrido");
        assert contact != null;
        assert contact.getId() == 1;
        assert contact.getEmail().equals("rubengarrido@ua.pt");
        assert contact.getPhone() == 910000000;

        contacts.saveAndClose();
    }

    public static void main(String[] args) {
        List<ContactsStorageInterface> list =
                List.of(new ContactsStorageCsv(), new ContactsStorageTxt(), new ContactsStorageBin());

        list.forEach(contactsStorage -> {
            test(contactsStorage);
            testImport(contactsStorage);
        });
    }
}
