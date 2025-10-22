// File: NotificationService.java

interface Notifier {
    void send(String message);
}

public class Service {
    public void triggerAlert() {
        // ✅ Anonymous Inner Class implementing Notifier
        Notifier notifier = new Notifier() {
            @Override
            public void send(String message) {
                System.out.println("ALERT: " + message);
            }
        };

        // Use the notifier
        notifier.send("System maintenance scheduled at 10 PM.");
    }

    public static void main(String[] args) {
        new Service().triggerAlert();
    }
}
