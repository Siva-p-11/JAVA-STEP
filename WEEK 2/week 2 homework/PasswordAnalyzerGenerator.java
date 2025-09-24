import java.util.*;

public class PasswordAnalyzerGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of passwords to analyze:");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String[] passwords = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter password %d: ", i + 1);
            passwords[i] = scanner.nextLine();
        }

        System.out.printf("%-20s %-6s %-8s %-8s %-6s %-13s %-6s %-8s%n", 
                "Password", "Length", "Upper", "Lower", "Digits", "SpecialChars", "Score", "Strength");
        System.out.println("--------------------------------------------------------------------------------");

        for (String pwd : passwords) {
            AnalysisResult res = analyzePassword(pwd);
            System.out.printf("%-20s %-6d %-8d %-8d %-6d %-13d %-6d %-8s%n",
                    pwd, pwd.length(), res.upperCount, res.lowerCount, res.digitCount, res.specialCount, res.score, res.strength);
        }

        System.out.println("\nEnter length for generated strong password:");
        int length = scanner.nextInt();

        String strongPwd = generateStrongPassword(length);
        System.out.println("Generated strong password: " + strongPwd);

        scanner.close();
    }

    static class AnalysisResult {
        int upperCount, lowerCount, digitCount, specialCount, score;
        String strength;
    }

    public static AnalysisResult analyzePassword(String pwd) {
        AnalysisResult res = new AnalysisResult();
        for (int i = 0; i < pwd.length(); i++) {
            int ch = pwd.charAt(i);
            if (ch >= 65 && ch <= 90) res.upperCount++;
            else if (ch >= 97 && ch <= 122) res.lowerCount++;
            else if (ch >= 48 && ch <= 57) res.digitCount++;
            else if (ch >= 33 && ch <= 126) res.specialCount++; // printable specials excluding space
        }

        res.score = 0;

        if (pwd.length() > 8) {
            res.score += 2 * (pwd.length() - 8);
        }

        int variety = 0;
        if (res.upperCount > 0) variety++;
        if (res.lowerCount > 0) variety++;
        if (res.digitCount > 0) variety++;
        if (res.specialCount > 0) variety++;

        res.score += variety * 10;

        // Deduct points for common patterns
        String lowerPwd = pwd.toLowerCase();
        if (lowerPwd.contains("123") || lowerPwd.contains("abc") || lowerPwd.contains("qwerty")) {
            res.score -= 15;
        }

        if (res.score < 0) res.score = 0;

        if (res.score <= 20) res.strength = "Weak";
        else if (res.score <= 50) res.strength = "Medium";
        else res.strength = "Strong";

        return res;
    }

    public static String generateStrongPassword(int length) {
        if (length < 8) length = 8; // minimum length for strong password

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{}|;:',.<>?/";

        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);

        // Ensure at least one from each category
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));

        String all = upper + lower + digits + special;

        // Fill remaining positions
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }

        // Shuffle characters for randomness
        char[] pwdArr = sb.toString().toCharArray();
        for (int i = pwdArr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = pwdArr[i];
            pwdArr[i] = pwdArr[j];
            pwdArr[j] = temp;
        }

        return new String(pwdArr);
    }
}
