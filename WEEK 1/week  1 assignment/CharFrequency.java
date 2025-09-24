import java.util.Scanner;

public class CharFrequency {

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

    public static Object[][] findFrequency(String text) {
        int[] freq = new int[256];
        int length = getLength(text);

        for (int i = 0; i < length; i++) {
            freq[text.charAt(i)]++;
        }

        int uniqueCount = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) uniqueCount++;
        }

        Object[][] result = new Object[uniqueCount][2];
        int index = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                result[index][0] = (char) i;
                result[index][1] = freq[i];
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        Object[][] freqArray = findFrequency(input);

        System.out.println("Character : Frequency");
        for (Object[] pair : freqArray) {
            System.out.println(pair[0] + " : " + pair[1]);
        }

        sc.close();
    }
}
