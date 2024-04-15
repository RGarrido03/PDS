package ex2;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private final Integer id;
    private String name;
    private Integer phone;
    private String email;

    public Contact(Integer id, String name, Integer phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getId(), contact.getId()) && Objects.equals(getName(), contact.getName())
                && Objects.equals(getPhone(), contact.getPhone()) && Objects.equals(getEmail(), contact.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPhone(), getEmail());
    }

    @Override
    public String toString() {
        return String.join("\t",
                           this.getId().toString(), this.getName(), this.getPhone().toString(), this.getEmail());
    }

    public String toCsv() {
        return String.join(",", this.getId().toString(), this.getName(), this.getPhone().toString(), this.getName());
    }
}
