import java.util.*;

public class SpellChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample dictionary, can be replaced or extended
        String[] dictionary = {"hello", "world", "sample", "text", "demonstrate", "string", "distance", "calculation", "spell", "checker"};

        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        String[] words = splitSentenceIntoWords(sentence);

        System.out.printf("%-15s %-15s %-10s %-12s%n", "Original", "Suggestion", "Distance", "Status");
        System.out.println("--------------------------------------------------------");

        for (String word : words) {
            String cleanWord = word.toLowerCase();
            if (cleanWord.isEmpty()) continue;

            if (isCorrect(cleanWord, dictionary)) {
                System.out.printf("%-15s %-15s %-10s %-12s%n", word, "-", 0, "Correct");
            } else {
                String suggestion = findClosestWord(cleanWord, dictionary);
                int dist = suggestion.equals("-") ? -1 : calculateDistance(cleanWord, suggestion);
                System.out.printf("%-15s %-15s %-10s %-12s%n", word, suggestion, dist == -1 ? "N/A" : dist, "Misspelled");
            }
        }

        scanner.close();
    }

    public static String[] splitSentenceIntoWords(String sentence) {
        List<String> words = new ArrayList<>();
        int start = 0;

        for (int i = 0; i <= sentence.length(); i++) {
            if (i == sentence.length() || !Character.isLetter(sentence.charAt(i))) {
                if (start < i) {
                    words.add(sentence.substring(start, i));
                }
                start = i + 1;
            }
        }

        return words.toArray(new String[0]);
    }

    public static boolean isCorrect(String word, String[] dictionary) {
        for (String dictWord : dictionary) {
            if (word.equals(dictWord)) return true;
        }
        return false;
    }

    public static int calculateDistance(String w1, String w2) {
        int len1 = w1.length();
        int len2 = w2.length();

        if (len1 == len2) {
            // Count character differences
            int diff = 0;
            for (int i = 0; i < len1; i++) {
                if (w1.charAt(i) != w2.charAt(i)) diff++;
            }
            return diff;
        } else {
            // Use insertion/deletion distance (difference in lengths)
            // plus char differences on the min length part
            int diff = Math.abs(len1 - len2);
            int minLen = Math.min(len1, len2);
            for (int i = 0; i < minLen; i++) {
                if (w1.charAt(i) != w2.charAt(i)) diff++;
            }
            return diff;
        }
    }

    public static String findClosestWord(String word, String[] dictionary) {
        int minDistance = Integer.MAX_VALUE;
        String closestWord = "-";

        for (String dictWord : dictionary) {
            int dist = calculateDistance(word, dictWord);
            if (dist < minDistance) {
                minDistance = dist;
                closestWord = dictWord;
            }
        }
        if (minDistance <= 2) return closestWord;
        return "-";
    }
}
