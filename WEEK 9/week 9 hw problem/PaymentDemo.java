// File: PaymentDemo.java
interface Discount {
    double apply(double amount);
}

class Payment {
    void processTransaction(double amount) {
        // Local inner class
        class Validator {
            boolean isValid() {
                return amount > 0;
            }
        }

        Validator validator = new Validator();
        if (!validator.isValid()) {
            System.out.println("Invalid Payment Amount!");
            return;
        }

        // Anonymous inner class for discount
        Discount discount = new Discount() {
            @Override
            public double apply(double amt) {
                return amt * 0.9; // 10% discount
            }
        };

        double finalAmount = discount.apply(amount);
        System.out.println("Original Amount: " + amount);
        System.out.println("After Discount: " + finalAmount);
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.processTransaction(1000);
        payment.processTransaction(-500); // Invalid
    }
}
