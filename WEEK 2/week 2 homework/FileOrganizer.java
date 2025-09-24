import java.util.*;

public class FileOrganizer {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> extensionCategories = Map.ofEntries(
        Map.entry("txt", "Documents"),
        Map.entry("doc", "Documents"),
        Map.entry("docx", "Documents"),
        Map.entry("pdf", "Documents"),
        Map.entry("jpg", "Images"),
        Map.entry("jpeg", "Images"),
        Map.entry("png", "Images"),
        Map.entry("gif", "Images"),
        Map.entry("mp3", "Audio"),
        Map.entry("wav", "Audio"),
        Map.entry("mp4", "Video"),
        Map.entry("avi", "Video"),
        Map.entry("zip", "Archives"),
        Map.entry("rar", "Archives")
    );

    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String subcategory = "";
        String newName;
        boolean validName = true;
        boolean unknownType = false;
        int priority = 0; // higher means more important

        FileInfo(String originalName, String baseName, String extension) {
            this.originalName = originalName;
            this.baseName = baseName;
            this.extension = extension;
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter file names with extensions (empty line to end):");
        List<String> inputs = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;
            inputs.add(line);
        }

        List<FileInfo> files = new ArrayList<>();
        for (String f : inputs) {
            FileInfo fi = extractFileComponents(f);
            if (fi != null) files.add(fi);
        }

        categorizeFiles(files);
        simulateContentAnalysis(files);
        generateNewNames(files);
        displayReport(files);
        generateBatchRenameCommands(files);
    }

    static FileInfo extractFileComponents(String filename) {
        int lastDot = filename.lastIndexOf('.');
        if (lastDot <= 0 || lastDot == filename.length() - 1) {
            System.out.println("Invalid file name (missing or misplaced extension): " + filename);
            return new FileInfo(filename, filename, "");
        }
        String base = filename.substring(0, lastDot);
        String ext = filename.substring(lastDot + 1).toLowerCase();

        boolean valid = validateFileName(base);
        FileInfo fi = new FileInfo(filename, base, ext);
        fi.validName = valid;
        return fi;
    }

    static boolean validateFileName(String name) {
        if (name.isEmpty()) return false;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_' || c == '-' || c == ' ')) return false;
        }
        return true;
    }

    static void categorizeFiles(List<FileInfo> files) {
        for (FileInfo f : files) {
            if (extensionCategories.containsKey(f.extension)) {
                f.category = extensionCategories.get(f.extension);
            } else {
                f.category = "Unknown";
                f.unknownType = true;
            }
        }
    }

    static void simulateContentAnalysis(List<FileInfo> files) {
        String[] resumeKeywords = {"resume", "cv", "experience", "education"};
        String[] reportKeywords = {"report", "analysis", "summary", "conclusion"};
        String[] codeKeywords = {"int", "void", "class", "function", "return"};

        for (FileInfo f : files) {
            if (!f.category.equals("Documents")) continue;
            String lowBase = f.baseName.toLowerCase();
            int score = 0;

            for (String kw : resumeKeywords) if (lowBase.contains(kw)) {f.subcategory = "Resume"; score+=3;}
            for (String kw : reportKeywords) if (lowBase.contains(kw)) {f.subcategory = "Report"; score+=2;}
            for (String kw : codeKeywords) if (lowBase.contains(kw)) {f.subcategory = "Code"; score+=4;}

            if (f.baseName.matches(".*\\d{4}.*")) score += 1; // name pattern priority
            f.priority = score;
        }
    }

    static void generateNewNames(List<FileInfo> files) {
        Map<String, Integer> nameCount = new HashMap<>();
        String dateStr = java.time.LocalDate.now().toString().replaceAll("-", "");

        for (FileInfo f : files) {
            String cat = f.category.equals("Unknown") ? "Other" : f.category;
            String subcat = f.subcategory.isEmpty() ? "" : "_" + f.subcategory;
            String base = cat + subcat + "_" + dateStr;

            int count = nameCount.getOrDefault(base, 0) + 1;
            nameCount.put(base, count);

            String newBase = base + (count > 1 ? "_" + count : "");
            String newName = sanitizeFileName(newBase) + "." + f.extension;
            f.newName = newName;
        }
    }

    static String sanitizeFileName(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '_' || c == '-') sb.append(c);
            else sb.append('_');
        }
        return sb.toString();
    }

    static void displayReport(List<FileInfo> files) {
        Map<String, Integer> categoryCounts = new HashMap<>();
        List<FileInfo> attentionFiles = new ArrayList<>();

        for (FileInfo f : files) {
            categoryCounts.put(f.category, categoryCounts.getOrDefault(f.category, 0) + 1);
            if (!f.validName || f.unknownType) attentionFiles.add(f);
        }

        System.out.println("\nFile Organization Report:");
        System.out.printf("%-30s %-15s %-30s\n", "Original Filename", "Category", "New Suggested Name");
        System.out.println("------------------------------------------------------------------------------------");
        for (FileInfo f : files) {
            System.out.printf("%-30s %-15s %-30s\n", f.originalName, f.category + (f.subcategory.isEmpty() ? "" : " (" + f.subcategory + ")"), f.newName);
        }

        System.out.println("\nCategory-wise file counts:");
        System.out.printf("%-15s %-10s\n", "Category", "Count");
        System.out.println("-------------------------");
        for (var e : categoryCounts.entrySet()) {
            System.out.printf("%-15s %-10d\n", e.getKey(), e.getValue());
        }

        if (!attentionFiles.isEmpty()) {
            System.out.println("\nFiles needing attention:");
            for (FileInfo f : attentionFiles) {
                System.out.printf("- %s (Invalid name: %s, Unknown type: %s)\n",
                        f.originalName,
                        !f.validName ? "Yes" : "No",
                        f.unknownType ? "Yes" : "No");
            }
        } else {
            System.out.println("\nNo files needing attention.");
        }
    }

    static void generateBatchRenameCommands(List<FileInfo> files) {
        System.out.println("\nBatch Rename Commands (simulate):");
        for (FileInfo f : files) {
            if (!f.originalName.equals(f.newName)) {
                System.out.printf("rename \"%s\" \"%s\"\n", f.originalName, f.newName);
            }
        }
    }
}
