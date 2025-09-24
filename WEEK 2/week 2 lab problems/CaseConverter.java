import java.util.Scanner;

public class CaseConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        String upperManual = toUpperCaseManual(input);
        String lowerManual = toLowerCaseManual(input);
        String titleManual = toTitleCaseManual(input);

        String upperBuiltIn = input.toUpperCase();
        String lowerBuiltIn = input.toLowerCase();

        boolean upperMatch = upperManual.equals(upperBuiltIn);
        boolean lowerMatch = lowerManual.equals(lowerBuiltIn);

        System.out.println("\n=== Conversion Results ===");
        System.out.printf("%-15s | %s%n", "Original", input);
        System.out.printf("%-15s | %s%n", "Manual Uppercase", upperManual);
        System.out.printf("%-15s | %s%n", "Built-in Uppercase", upperBuiltIn);
        System.out.printf("%-15s | Match", upperMatch);
        System.out.println();
        System.out.printf("%-15s | %s%n", "Manual Lowercase", lowerManual);
        System.out.printf("%-15s | %s%n", "Built-in Lowercase", lowerBuiltIn);
        System.out.printf("%-15s | Match", lowerMatch);
        System.out.println();
        System.out.printf("%-15s | %s%n", "Manual Titlecase", titleManual);

        scanner.close();
    }

    public static String toUpperCaseManual(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                result.append((char)(c - 32));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String toLowerCaseManual(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                result.append((char)(c + 32));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String toTitleCaseManual(String text) {
        StringBuilder result = new StringBuilder();
        boolean newWord = true;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                newWord = true;
                result.append(c);
            } else if (newWord && c >= 'a' && c <= 'z') {
                result.append((char)(c - 32));
                newWord = false;
            } else if (!newWord && c >= 'A' && c <= 'Z') {
                result.append((char)(c + 32));
            } else {
                result.append(c);
                newWord = false;
            }
        }
        return result.toString();
    }
}
