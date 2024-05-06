package ex1.b;

class Employee {

    private double salary;
    private Person person;
    private BankAccount bankAccount;

    public Employee(Person person, double s) {
        this.person = person;
        salary = s;
        bankAccount = new ProxyBankAccount(new ex1.b.BankAccountImpl("PeDeMeia", 0));
    }

    public double getSalary() {
        return salary;
    }

    public void deposit() {
        bankAccount.deposit(salary);
    }
}