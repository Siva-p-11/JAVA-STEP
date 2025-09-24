tamilselvan
import java.util.Scanner;

public class WordLength2D {
    public static int getLength(String s) {
        int i = 0;
        try { while (true) s.charAt(i++); } catch (Exception e) {}
        return i;
    }

    public static String[] splitWords(String s) {
        int len = getLength(s), count = 0;
        for (int i = 0; i < len; i++) if (s.charAt(i) == ' ') count++;
        int[] idx = new int[count + 2]; idx[0] = -1; idx[count + 1] = len;
        for (int i = 0, j = 1; i < len; i++) if (s.charAt(i) == ' ') idx[j++] = i;
        String[] words = new String[count + 1];
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = idx[i] + 1; j < idx[i + 1]; j++) sb.append(s.charAt(j));
            words[i] = sb.toString();
        }
        return words;
    }

    public static String[][] wordLengthArray(String[] words) {
        String[][] res = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            res[i][0] = words[i];
            res[i][1] = String.valueOf(getLength(words[i]));
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();
        String[][] arr = wordLengthArray(splitWords(input));
        System.out.println("\nWord\tLength\n-------------");
        for (String[] row : arr)
            System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
    }
}
