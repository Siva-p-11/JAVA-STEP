import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        String cleaned = input.trim().replaceAll("\\s+", " ");
        StringBuilder result = new StringBuilder();
        boolean capitalize = true;
        for (char ch : cleaned.toCharArray()) {
            if (capitalize && Character.isLetter(ch)) {
                result.append(Character.toUpperCase(ch));
                capitalize = false;
            } else {
                result.append(Character.toLowerCase(ch));
            }
            if (ch == '.' || ch == '!' || ch == '?') {
                capitalize = true;
            }
        }
        return result.toString();
    }

    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        String[] sentences = text.split("[.!?]+");
        int sentenceCount = 0;
        for (String s : sentences) {
            if (s.trim().length() > 0) sentenceCount++;
        }

        int charCount = text.replaceAll("\\s", "").length();

        String longestWord = "";
        for (String w : words) {
            String cleanWord = w.replaceAll("[^a-zA-Z]", "");
            if (cleanWord.length() > longestWord.length()) {
                longestWord = cleanWord;
            }
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }
        char mostCommonChar = ' ';
        int maxFreq = 0;
        for (var entry : freq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostCommonChar = entry.getKey();
            }
        }

        System.out.println("\n--- Text Analysis ---");
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Characters (excluding spaces): " + charCount);
        System.out.println("Longest word: " + longestWord);
        System.out.println("Most common character: '" + mostCommonChar + "' (" + maxFreq + " times)");
    }

    public static String[] getWordsSorted(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z\\s]", "");
        String[] words = cleanedText.toLowerCase().split("\\s+");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph of text:\n> ");
        String input = scanner.nextLine();

        String cleaned = cleanInput(input);
        System.out.println("\nCleaned Text:\n" + cleaned);

        analyzeText(cleaned);

        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("\nWords in alphabetical order:");
        for (String w : sortedWords) {
            System.out.print(w + " ");
        }
        System.out.println();

        System.out.print("\nEnter a word to search (or type 'exit' to quit): ");
        String search;
        while (!(search = scanner.nextLine().toLowerCase()).equals("exit")) {
            int count = 0;
            for (String w : sortedWords) {
                if (w.equals(search)) count++;
            }
            if (count > 0) {
                System.out.println("'" + search + "' found " + count + " time(s).");
            } else {
                System.out.println("'" + search + "' not found.");
            }
            System.out.print("Search another word or type 'exit' to quit: ");
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}
