// BankDemo.java
// Problem 2: Bank Account with Abstract Methods in one file

abstract class BankAccount {
    protected double balance;

    // Constructor to set balance
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Abstract method
    public abstract void calculateInterest();

    // Non-abstract method
    public void displayBalance() {
        System.out.println("Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * 0.04;
        System.out.println("Savings Account Interest: " + interest);
    }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * 0.02;
        System.out.println("Current Account Interest: " + interest);
    }
}

public class BankDemo {
    public static void main(String[] args) {
        // BankAccount reference -> SavingsAccount
        BankAccount acc1 = new SavingsAccount(10000);
        acc1.displayBalance();
        acc1.calculateInterest();

        // BankAccount reference -> CurrentAccount
        BankAccount acc2 = new CurrentAccount(20000);
        acc2.displayBalance();
        acc2.calculateInterest();
    }
}
