import java.util.*;

public class EmailAnalyzer {

    static class EmailInfo {
        String email;
        String username;
        String domain;
        String domainName;
        String extension;
        boolean isValid;

        EmailInfo(String email, String username, String domain, String domainName, String extension, boolean isValid) {
            this.email = email;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
            this.isValid = isValid;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<EmailInfo> emailInfos = new ArrayList<>();

        System.out.println("Enter emails (type 'done' to finish):");
        while (true) {
            String email = scanner.nextLine().trim();
            if (email.equalsIgnoreCase("done")) break;
            boolean valid = validateEmail(email);
            EmailInfo info = extractEmailComponents(email, valid);
            emailInfos.add(info);
        }

        analyzeAndDisplay(emailInfos);
        scanner.close();
    }

    public static boolean validateEmail(String email) {
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');

        if (atIndex == -1 || atIndex != lastAtIndex) return false;

        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);

        if (username.isEmpty() || domain.isEmpty()) return false;

        int dotAfterAt = domain.indexOf('.');
        if (dotAfterAt == -1) return false;

        return true;
    }

    public static EmailInfo extractEmailComponents(String email, boolean isValid) {
        if (!isValid) {
            return new EmailInfo(email, "-", "-", "-", "-", false);
        }
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);

        int dotIndex = domain.lastIndexOf('.');
        String domainName = (dotIndex != -1) ? domain.substring(0, dotIndex) : domain;
        String extension = (dotIndex != -1) ? domain.substring(dotIndex + 1) : "-";

        return new EmailInfo(email, username, domain, domainName, extension, true);
    }

    public static void analyzeAndDisplay(List<EmailInfo> emailInfos) {
        int validCount = 0, invalidCount = 0;
        Map<String, Integer> domainFreq = new HashMap<>();
        int totalUsernameLength = 0;

        for (EmailInfo info : emailInfos) {
            if (info.isValid) {
                validCount++;
                totalUsernameLength += info.username.length();
                domainFreq.put(info.domain, domainFreq.getOrDefault(info.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }

        String mostCommonDomain = "-";
        int maxFreq = 0;
        for (var entry : domainFreq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }

        double avgUsernameLen = (validCount == 0) ? 0.0 : (double) totalUsernameLength / validCount;

        System.out.println("\n=== Email Analysis ===");
        System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s%n", "Email", "Username", "Domain", "Domain Name", "Extension", "Valid?");
        System.out.println("----------------------------------------------------------------------------------------------");
        for (EmailInfo info : emailInfos) {
            System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s%n",
                    info.email, info.username, info.domain, info.domainName, info.extension, info.isValid ? "Yes" : "No");
        }

        System.out.println("\nStatistics:");
        System.out.println("Total Valid Emails  : " + validCount);
        System.out.println("Total Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain  : " + mostCommonDomain);
        System.out.printf("Average Username Length: %.2f%n", avgUsernameLen);
    }
}
