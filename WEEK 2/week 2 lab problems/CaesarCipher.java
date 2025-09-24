import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to encrypt: ");
        String input = scanner.nextLine();

        System.out.print("Enter shift value (integer): ");
        int shift = scanner.nextInt();

        String encrypted = encryptText(input, shift);
        String decrypted = decryptText(encrypted, shift);

        System.out.println("\n=== Encryption System ===");

        System.out.println("\nOriginal Text and ASCII:");
        displayAsciiValues(input);

        System.out.println("\nEncrypted Text and ASCII:");
        displayAsciiValues(encrypted);

        System.out.println("\nDecrypted Text:");
        System.out.println(decrypted);

        boolean isValid = validateDecryption(input, decrypted);
        System.out.println("Decryption Valid: " + isValid);

        scanner.close();
    }

    public static String encryptText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char encrypted = (char) (((c - 'A' + shift) % 26 + 26) % 26 + 'A');
                result.append(encrypted);
            } else if (c >= 'a' && c <= 'z') {
                char encrypted = (char) (((c - 'a' + shift) % 26 + 26) % 26 + 'a');
                result.append(encrypted);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decryptText(String text, int shift) {
        return encryptText(text, -shift);
    }

    public static void displayAsciiValues(String text) {
        for (char c : text.toCharArray()) {
            System.out.printf("'%c' -> %d%n", c, (int) c);
        }
    }

    public static boolean validateDecryption(String original, String decrypted) {
        return original.equals(decrypted);
    }
}
