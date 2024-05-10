package ex2;

class Employee {

    private double salary;
    private Person person;
    private BankAccount bankAccount;

    public Employee(Person person, double s) {
        this.person = person;
        salary = s;
        bankAccount = new ProxyBankAccount(new BankAccountImpl("PeDeMeia", 0));
    }



    public double getSalary() {
        return salary;
    }

    public void deposit() {
        bankAccount.deposit(salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", person=" + person +
                ", bankAccount=" + bankAccount +
                '}';
    }
}