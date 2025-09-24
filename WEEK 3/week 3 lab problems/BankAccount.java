class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int accountCounter = 0;

    public BankAccount(String name, double initialDeposit) {
        this.accountHolderName = name;
        this.balance = (initialDeposit >= 0) ? initialDeposit : 0;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("ACC%03d", accountCounter);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } 
    }

    public double checkBalance() {
        return balance;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name   : " + accountHolderName);
        System.out.println("Balance       : $" + balance);
        System.out.println("----------------------------");
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];

        accounts[0] = new BankAccount("Alice", 500);
        accounts[1] = new BankAccount("Bob", 1000);
        accounts[2] = new BankAccount("Charlie", 750);

        accounts[0].deposit(200);
        accounts[1].withdraw(300);
        accounts[2].deposit(100);
        accounts[2].withdraw(50);

        for (int i = 0; i < accounts.length; i++) {
            accounts[i].displayAccountInfo();
        }

        System.out.println("Total Bank Accounts: " + BankAccount.getTotalAccounts());
    }
}
