import java.util.Scanner;

public class FrequencyNestedLoops {

    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int length = chars.length;
        int[] freq = new int[length];

        for (int i = 0; i < length; i++) {
            if (chars[i] == '0') continue;
            freq[i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0';                 }
            }
        }

        int count = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] != '0') count++;
        }

        String[] result = new String[count];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] != '0') {
                result[index++] = chars[i] + " : " + freq[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[] freqArray = findFrequency(input);

        for (String s : freqArray) {
            System.out.println(s);
        }

        sc.close();
    }
}
