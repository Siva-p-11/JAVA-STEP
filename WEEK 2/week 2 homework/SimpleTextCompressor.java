import java.util.*;

public class SimpleTextCompressor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text to compress:");
        String text = scanner.nextLine();

        CharFreqResult freqResult = countCharFrequency(text);
        char[] chars = freqResult.chars;
        int[] freqs = freqResult.freqs;

        CodeMappingResult mappingResult = createCompressionCodes(chars, freqs);

        String compressed = compressText(text, mappingResult);
        String decompressed = decompressText(compressed, mappingResult);

        displayAnalysis(text, chars, freqs, mappingResult, compressed, decompressed);

        scanner.close();
    }

    static class CharFreqResult {
        char[] chars;
        int[] freqs;
    }

    static class CodeMappingResult {
        char[] chars;
        String[] codes;
    }

    public static CharFreqResult countCharFrequency(String text) {
        List<Character> charList = new ArrayList<>();
        List<Integer> freqList = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = charList.indexOf(c);
            if (index == -1) {
                charList.add(c);
                freqList.add(1);
            } else {
                freqList.set(index, freqList.get(index) + 1);
            }
        }

        CharFreqResult result = new CharFreqResult();
        result.chars = new char[charList.size()];
        result.freqs = new int[freqList.size()];
        for (int i = 0; i < charList.size(); i++) {
            result.chars[i] = charList.get(i);
            result.freqs[i] = freqList.get(i);
        }
        return result;
    }

    public static CodeMappingResult createCompressionCodes(char[] chars, int[] freqs) {
        for (int i = 0; i < freqs.length - 1; i++) {
            for (int j = 0; j < freqs.length - 1 - i; j++) {
                if (freqs[j] < freqs[j + 1]) {
                    int tempF = freqs[j];
                    freqs[j] = freqs[j + 1];
                    freqs[j + 1] = tempF;

                    char tempC = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = tempC;
                }
            }
        }

        String[] shortCodes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "*"};
        CodeMappingResult result = new CodeMappingResult();
        result.chars = new char[chars.length];
        result.codes = new String[chars.length];

        for (int i = 0; i < chars.length; i++) {
            result.chars[i] = chars[i];
            if (i < shortCodes.length) {
                result.codes[i] = shortCodes[i];
            } else {
                result.codes[i] = "~" + (i - shortCodes.length);
            }
        }
        return result;
    }

    public static String compressText(String text, CodeMappingResult mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int idx = -1;
            for (int j = 0; j < mapping.chars.length; j++) {
                if (mapping.chars[j] == c) {
                    idx = j;
                    break;
                }
            }
            sb.append(mapping.codes[idx]);
        }
        return sb.toString();
    }

    public static String decompressText(String compressed, CodeMappingResult mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < compressed.length();) {
            boolean matched = false;
            for (int j = 0; j < mapping.codes.length; j++) {
                String code = mapping.codes[j];
                if (compressed.startsWith(code, i)) {
                    sb.append(mapping.chars[j]);
                    i += code.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                sb.append('?');
                i++;
            }
        }
        return sb.toString();
    }

    public static void displayAnalysis(String original, char[] chars, int[] freqs, CodeMappingResult mapping, String compressed, String decompressed) {
        System.out.println("\nCharacter Frequency Table:");
        System.out.printf("%-10s %-10s %-10s%n", "Char", "ASCII", "Frequency");
        for (int i = 0; i < chars.length; i++) {
            System.out.printf("%-10s %-10d %-10d%n", "'" + chars[i] + "'", (int) chars[i], freqs[i]);
        }

        System.out.println("\nCompression Mapping (Char -> Code):");
        System.out.printf("%-10s %-10s%n", "Char", "Code");
        for (int i = 0; i < mapping.chars.length; i++) {
            System.out.printf("%-10s %-10s%n", "'" + mapping.chars[i] + "'", mapping.codes[i]);
        }

        System.out.println("\nOriginal text:");
        System.out.println(original);

        System.out.println("\nCompressed text:");
        System.out.println(compressed);

        System.out.println("\nDecompressed text:");
        System.out.println(decompressed);

        boolean valid = original.equals(decompressed);
        System.out.println("\nDecompression valid: " + (valid ? "YES" : "NO"));

        int originalSize = original.length() * 16;
        int compressedSize = compressed.length() * 8;

        double efficiency = 100.0 * (originalSize - compressedSize) / originalSize;
        System.out.printf("Compression efficiency: %.2f%%\n", efficiency);
    }
}
