import java.util.Scanner;

public class VowelConsonantCounter {

    public static String checkChar(char ch) {
                if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + ('a' - 'A'));
        }
              if (ch < 'a' || ch > 'z') {
            return "NotLetter";
        }
               if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return "Vowel";
        }
        return "Consonant";
    }

    public static int[] countVowelsConsonants(String str) {
        int vowels = 0, consonants = 0;
        int i = 0;
        try {
            while (true) {
                char ch = str.charAt(i++);
                String res = checkChar(ch);
                if (res.equals("Vowel")) vowels++;
                else if (res.equals("Consonant")) consonants++;
            }
        } catch (IndexOutOfBoundsException e) {
                    }
        return new int[]{vowels, consonants};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        int[] counts = countVowelsConsonants(input);
        System.out.println("Vowels: " + counts[0]);
        System.out.println("Consonants: " + counts[1]);
    }
}
