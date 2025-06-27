package Medium;
import java.util.*;
public class p567_PermutationInString {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2)); // true

        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println(checkInclusion(s1, s2)); // false

        s1 = "hello";
        s2 = "ooolleoooleh";
        System.out.println(checkInclusion(s1, s2)); // false
    }
    public static boolean checkInclusion(String s1, String s2) {
        // if s1 is longer than s2 --> return false
        if (s1.length() > s2.length()) {
            return false;
        }

        // Hint 5: Both strings must have same character frequencies, if one is permutation of another.
        // Which data structure should be used to store frequencies?
        // Hint 6: What about hash table? An array of size 26?
        // Create frequency maps for the characters 'a' through 'z'.
        int[] s1Map = new int[26];
        int[] s2Map = new int[26];

        // Populates the frequency maps for the initial window.
        for (int i = 0; i < s1.length(); i++) {
            s1Map[s1.charAt(i) - 'a']++;
            s2Map[s2.charAt(i) - 'a']++;
        }
//        System.out.println(Arrays.toString(s1Map));
//        System.out.println(Arrays.toString(s2Map));

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            // Check if the frequency maps of the current window match.
            if (Arrays.equals(s1Map, s2Map)) {
                return true;
            }
            s2Map[s2.charAt(i + s1.length()) - 'a']++;
            s2Map[s2.charAt(i) - 'a']--;
//            System.out.println(Arrays.toString(s2Map));
        }

//        System.out.println(Arrays.toString(s1Map));
//        System.out.println(Arrays.toString(s2Map));

        // Check the last window of s2
        if (Arrays.equals(s1Map, s2Map)) {
            return true;
        }

        return false;
    }
}
