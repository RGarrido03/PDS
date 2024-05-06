package ex1.a;

interface BankAccount {
    void deposit(double amount);

    boolean withdraw(double amount);

    double balance() throws IllegalAccessException;
}

