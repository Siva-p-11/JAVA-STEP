// File: PaymentGateway.java

class Payment {
    public void pay() {
        System.out.println("Generic payment");
    }
}

class CreditCardPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Payment done using Credit Card.");
    }
}

class WalletPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Payment done using Wallet.");
    }
}

public class 
 {
    public static void main(String[] args) {
        // 1. Create array of Payment references
        Payment[] payments = {
            new CreditCardPayment(),
            new WalletPayment(),
            new Payment()
        };

        // 2. Loop through array
        for (Payment p : payments) {
            // Get actual runtime class name
            System.out.println("Processing: " + p.getClass().getSimpleName());
            // Call overridden pay()
            p.pay();
            System.out.println("-----");
        }
    }
}
