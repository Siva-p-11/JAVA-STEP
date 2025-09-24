import java.util.Scanner;

public class CharFrequencyWithUnique {

    public static int getLength(String text) {
        int count = 0;
        try {
            while(true) {
                text.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {}
        return count;
    }

    public static char[] uniqueCharacters(String text) {
        int length = getLength(text);
        char[] temp = new char[length];
        int uniqueCount = 0;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            boolean isUnique = true;

<<<<<<< HEAD
            for (int j = 0; j < uniqueCount; j++) {CharFrequencyWithUnique
=======
            for (int j = 0; j < uniqueCount; j++) {
>>>>>>> 8b25fbe99c9273580dd29ecc2c7caa798f1bd791
                if (temp[j] == c) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                temp[uniqueCount++] = c;
            }
        }

        char[] uniqueChars = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            uniqueChars[i] = temp[i];
        }
        return uniqueChars;
    }

    public static String[][] findFrequency(String text) {
        int length = getLength(text);
        int[] freq = new int[256];

        for (int i = 0; i < length; i++) {
            freq[text.charAt(i)]++;
        }

        char[] uniqueChars = uniqueCharacters(text);
        String[][] result = new String[uniqueChars.length][2];

        for (int i = 0; i < uniqueChars.length; i++) {
            result[i][0] = String.valueOf(uniqueChars[i]);
            result[i][1] = String.valueOf(freq[uniqueChars[i]]);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] freqArray = findFrequency(input);

        System.out.println("Character : Frequency");
        for (String[] pair : freqArray) {
            System.out.println(pair[0] + " : " + pair[1]);
        }

        sc.close();
    }
}
