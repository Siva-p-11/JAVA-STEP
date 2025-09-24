import java.util.ArrayList;
import java.util.Scanner;

public class SubstringReplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the main text: ");
        String mainText = scanner.nextLine();

        System.out.print("Enter the substring to find: ");
        String toFind = scanner.nextLine();

        System.out.print("Enter the replacement substring: ");
        String toReplace = scanner.nextLine();

        ArrayList<Integer> positions = findAllOccurrences(mainText, toFind);
        String manuallyReplaced = manualReplace(mainText, toFind, toReplace, positions);
        boolean isSame = compareWithBuiltIn(mainText, toFind, toReplace, manuallyReplaced);

        System.out.println("\nOriginal Text: " + mainText);
        System.out.println("Manually Replaced Text: " + manuallyReplaced);
        System.out.println("Built-in Replaced Text: " + mainText.replace(toFind, toReplace));
        System.out.println("Do both methods produce the same result? " + isSame);

        scanner.close();
    }

    public static ArrayList<Integer> findAllOccurrences(String text, String toFind) {
        ArrayList<Integer> positions = new ArrayList<>();
        int index = text.indexOf(toFind);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(toFind, index + toFind.length());
        }
        return positions;
    }

    public static String manualReplace(String text, String toFind, String toReplace, ArrayList<Integer> positions) {
        StringBuilder result = new StringBuilder();
        int i = 0, posIndex = 0;
        while (i < text.length()) {
            if (posIndex < positions.size() && i == positions.get(posIndex)) {
                result.append(toReplace);
                i += toFind.length();
                posIndex++;
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareWithBuiltIn(String original, String toFind, String toReplace, String manualResult) {
        return original.replace(toFind, toReplace).equals(manualResult);
    }
}
