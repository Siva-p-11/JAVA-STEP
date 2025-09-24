public class StringArrays {

    public static String findLongestName(String[] names) {
        String longest = "";
        for (String name : names) {
            if (name.length() > longest.length()) {
                longest = name;
            }
        }
        return longest;
    }

    public static int countNamesStartingWith(String[] names, char letter) {
        int count = 0;
        char target = Character.toLowerCase(letter);
        for (String name : names) {
            if (!name.isEmpty()) {
                char firstChar = Character.toLowerCase(name.charAt(0));
                if (firstChar == target) {
                    count++;
                }
            }
        }
        return count;
    }

    public static String[] formatNames(String[] names) {
        String[] formatted = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String[] parts = names[i].split(" ");
            if (parts.length >= 2) {
                formatted[i] = parts[1] + ", " + parts[0];
            } else {
                formatted[i] = names[i]; // If no space found, leave as is
            }
        }
        return formatted;
    }

    public static void main(String[] args) {
        String[] students = {"John Smith", "Alice Johnson", "Bob Brown", "Carol Davis", "David Wilson"};

        String longestName = findLongestName(students);
        System.out.println("Longest name: " + longestName);

        int countStartingWithD = countNamesStartingWith(students, 'D');
        System.out.println("Number of names starting with 'D': " + countStartingWithD);

        String[] formattedNames = formatNames(students);
        System.out.println("Formatted names:");
        for (String name : formattedNames) {
            System.out.println(name);
        }
    }
}
