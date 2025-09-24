import java.util.Scanner;

public class ShortestLongestWord {

    public static int getLength(String s) {
        int i = 0;
        try {
            while (true) s.charAt(i++);
        } catch (Exception e) {}
        return i;
    }

    public static String[] customSplit(String s) {
        int len = getLength(s), spaceCount = 0;
        for (int i = 0; i < len; i++) if (s.charAt(i) == ' ') spaceCount++;
        int[] spaceIdx = new int[spaceCount + 2];
        spaceIdx[0] = -1; spaceIdx[spaceCount + 1] = len;
        for (int i = 0, j = 1; i < len; i++) if (s.charAt(i) == ' ') spaceIdx[j++] = i;
        String[] words = new String[spaceCount + 1];
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = spaceIdx[i] + 1; j < spaceIdx[i + 1]; j++) sb.append(s.charAt(j));
            words[i] = sb.toString();
        }
        return words;
    }

    public static String[][] wordsWithLength(String[] words) {
        String[][] arr = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            arr[i][0] = words[i];
            arr[i][1] = String.valueOf(getLength(words[i]));
        }
        return arr;
    }

    public static String[] findShortestLongest(String[][] wordLenArr) {
        int shortestLen = Integer.MAX_VALUE, longestLen = Integer.MIN_VALUE;
        String shortestWord = "", longestWord = "";
        for (String[] pair : wordLenArr) {
            int len = Integer.parseInt(pair[1]);
            if (len < shortestLen) {
                shortestLen = len;
                shortestWord = pair[0];
            }
            if (len > longestLen) {
                longestLen = len;
                longestWord = pair[0];
            }
        }
        return new String[]{shortestWord, longestWord};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();

        String[] words = customSplit(input);
        String[][] wordLenArr = wordsWithLength(words);
        String[] shortestLongest = findShortestLongest(wordLenArr);

        System.out.println("Shortest word: " + shortestLongest[0]);
        System.out.println("Longest word: " + shortestLongest[1]);
    }
}
