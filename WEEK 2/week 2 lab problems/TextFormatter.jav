import java.util.*;

public class TextFormatter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = scanner.nextLine();

        System.out.print("Enter line width: ");
        int width = scanner.nextInt();

        String[] words = splitWords(text);

        long startSB = System.nanoTime();
        List<String> justifiedLines = justifyText(words, width);
        long endSB = System.nanoTime();

        long startConcat = System.nanoTime();
        List<String> justifiedLinesConcat = justifyTextConcat(words, width);
        long endConcat = System.nanoTime();

        List<String> centeredLines = centerAlign(justifiedLines, width);

        System.out.println("\n=== Original Text ===");
        System.out.println(text);

        System.out.println("\n=== Left-Justified Text ===");
        displayLines(justifiedLines);

        System.out.println("\n=== Center-Aligned Text ===");
        displayLines(centeredLines);

        System.out.println("\n=== Performance Comparison ===");
        System.out.printf("StringBuilder justify time: %d ns%n", (endSB - startSB));
        System.out.printf("String concat justify time: %d ns%n", (endConcat - startConcat));
        System.out.printf("StringBuilder faster by: %d ns%n", (endConcat - startConcat) - (endSB - startSB));

        scanner.close();
    }

    public static String[] splitWords(String text) {
        List<String> wordList = new ArrayList<>();
        int start = 0, end;
        while (start < text.length()) {
            // Skip spaces
            while (start < text.length() && text.charAt(start) == ' ') start++;
            if (start >= text.length()) break;
            end = start;
            while (end < text.length() && text.charAt(end) != ' ') end++;
            wordList.add(text.substring(start, end));
            start = end;
        }
        return wordList.toArray(new String[0]);
    }

    public static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int lineLen = words[index].length();
            int last = index + 1;

            while (last < words.length) {
                if (lineLen + 1 + words[last].length() > width) break;
                lineLen += 1 + words[last].length();
                last++;
            }

            StringBuilder line = new StringBuilder();
            int gapCount = last - index - 1;

            // If last line or line has only one word -> left align
            if (last == words.length || gapCount == 0) {
                for (int i = index; i < last; i++) {
                    line.append(words[i]);
                    if (i != last - 1) line.append(' ');
                }
                // Fill remaining spaces
                for (int i = line.length(); i < width; i++) line.append(' ');
            } else {
                // Distribute spaces evenly
                int totalSpaces = width - lineLen + gapCount; // add gapCount because lineLen counts only one space per gap
                int spacePerGap = totalSpaces / gapCount;
                int extraSpaces = totalSpaces % gapCount;

                for (int i = index; i < last - 1; i++) {
                    line.append(words[i]);
                    line.append(' ');
                    for (int s = 0; s < spacePerGap; s++) line.append(' ');
                    if (extraSpaces > 0) {
                        line.append(' ');
                        extraSpaces--;
                    }
                }
                line.append(words[last - 1]);
            }
            lines.add(line.toString());
            index = last;
        }
        return lines;
    }

    public static List<String> justifyTextConcat(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int lineLen = words[index].length();
            int last = index + 1;

            while (last < words.length) {
                if (lineLen + 1 + words[last].length() > width) break;
                lineLen += 1 + words[last].length();
                last++;
            }

            String line = "";
            int gapCount = last - index - 1;

            if (last == words.length || gapCount == 0) {
                for (int i = index; i < last; i++) {
                    line += words[i];
                    if (i != last - 1) line += " ";
                }
                while (line.length() < width) line += " ";
            } else {
                int totalSpaces = width - lineLen + gapCount;
                int spacePerGap = totalSpaces / gapCount;
                int extraSpaces = totalSpaces % gapCount;

                for (int i = index; i < last - 1; i++) {
                    line += words[i] + " ";
                    for (int s = 0; s < spacePerGap; s++) line += " ";
                    if (extraSpaces > 0) {
                        line += " ";
                        extraSpaces--;
                    }
                }
                line += words[last - 1];
            }
            lines.add(line);
            index = last;
        }
        return lines;
    }

    public static List<String> centerAlign(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();
        for (String line : lines) {
            int spaces = width - line.trim().length();
            int paddingLeft = spaces / 2;
            int paddingRight = spaces - paddingLeft;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paddingLeft; i++) sb.append(' ');
            sb.append(line.trim());
            for (int i = 0; i < paddingRight; i++) sb.append(' ');
            centered.add(sb.toString());
        }
        return centered;
    }

    public static void displayLines(List<String> lines) {
        int lineNum = 1;
        for (String line : lines) {
            System.out.printf("%2d | (%2d chars) | %s%n", lineNum++, line.length(), line);
        }
    }
}
