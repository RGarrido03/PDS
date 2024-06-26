package ex2;

public class ProxyBankAccount implements BankAccount {

    private BankAccount bankAccount;

    public ProxyBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("depositing " + amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (Company.user == User.OWNER){
            return bankAccount.withdraw(amount);
        } else {
            return false;
        }
    }

    @Override
    public double balance() {
        if (Company.user == User.OWNER ){
            return bankAccount.balance();
        } else {
            return 0;
        }
    }
}

