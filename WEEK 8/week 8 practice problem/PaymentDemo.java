// PaymentDemo.java
// Problem 3: Interface for Payment Gateway in one file

interface PaymentGateway {
    void pay(double amount);
    void refund(double amount);
}

class CreditCardPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to Credit Card");
    }
}

class UPIPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to UPI");
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        // PaymentGateway reference -> CreditCardPayment
        PaymentGateway pg1 = new CreditCardPayment();
        pg1.pay(5000);
        pg1.refund(2000);

        // PaymentGateway reference -> UPIPayment
        PaymentGateway pg2 = new UPIPayment();
        pg2.pay(3000);
        pg2.refund(1000);
    }
}
