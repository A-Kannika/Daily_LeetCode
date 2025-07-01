package Daily;

public class p3300_PossibleStringCount {
    public static void main(String[] args) {
        System.out.println(possibleStringCount("abbcccc")); // 5
        System.out.println(possibleStringCount("abcd")); // 1
        System.out.println(possibleStringCount("aaaa")); // 4
    }

    public static int possibleStringCount(String word) {
        // Edge case
        if (word == null || word.isEmpty()) {
            return 0;
        }

        // This will store the sum of possibilities where a long press occurred.
        int longPressPossibilities = 0;
        int i = 0;
        int n = word.length();

        // Iterate through the string to find groups of consecutive identical characters.
        while (i < n) {
            char currentChar = word.charAt(i);
            int j = i;
            // Find the end of the current group.
            while (j < n && word.charAt(j) == currentChar) {
                j++;
            }

            // The length of the group is (j - i).
            int groupLength = j - i;
            if (groupLength > 1) {
                longPressPossibilities += (groupLength - 1);
            }

            // Move the main pointer to the start of the next group.
            i = j;
        }

        return longPressPossibilities + 1;
    }
}
