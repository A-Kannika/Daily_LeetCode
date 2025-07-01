package Daily;

public class p3300_PossibleStringCount {
    public static void main(String[] args) {
        // Example 1
        String word1 = "abbcccc";
        System.out.println("Input: \"" + word1 + "\"");
        System.out.println("Output: " + possibleStringCount(word1)); // Expected: 5
        System.out.println("--------------------");

        // Example 2
        String word2 = "abcd";
        System.out.println("Input: \"" + word2 + "\"");
        System.out.println("Output: " + possibleStringCount(word2)); // Expected: 1
        System.out.println("--------------------");

        // Example 3
        String word3 = "aaaa";
        System.out.println("Input: \"" + word3 + "\"");
        System.out.println("Output: " + possibleStringCount(word3)); // Expected: 4
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
