package Daily;

public class p2311_LongestSubsequence {
    public static void main(String[] args) {
        System.out.println(longestSubsequence("1001010", 5)); // expected output 5
        System.out.println();
        System.out.println(longestSubsequence("00101001", 1)); // expected output 6
        System.out.println();
        System.out.println(longestSubsequence("00101001", 22)); // expected output 7
        System.out.println();
    }

    public static int longestSubsequence(String s, int k) {
        if (s.isEmpty()) {
            return 0;
        }
        int length = 0;
        long current = 0;
        long powerOfTwo = 1;
        // We iterate from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            // take every 0 from string
            if (c == '0') {
                length++;
            } else {
                if (current + powerOfTwo <= k) {
                    // System.out.println(current);
                    current += powerOfTwo;
                    // System.out.println(current);
                    length++;
                }
            }
            // System.out.println(powerOfTwo);
            if (powerOfTwo <= k) {
                powerOfTwo *= 2;
            }
        }
        return length;
    }
}
