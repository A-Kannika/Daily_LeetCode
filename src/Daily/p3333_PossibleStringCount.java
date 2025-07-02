package Daily;

import java.util.ArrayList;
import java.util.List;
public class p3333_PossibleStringCount {
    public static void main(String[] args) {

        System.out.println(countPossibleStrings("aabbccdd", 7)); // 5
        System.out.println(countPossibleStrings("aabbccdd", 8)); // 1
        System.out.println(countPossibleStrings("aaabbb", 3)); // 8
    }
    public static int countPossibleStrings(String word, int k) {
        final int MOD = 1_000_000_007;

        // Find the lengths of all consecutive character groups.
        List<Integer> groups = new ArrayList<>();
        int i = 0;
        while (i < word.length()) {
            int j = i;
            while (j < word.length() && word.charAt(j) == word.charAt(i)) {
                j++;
            }
            groups.add(j - i);
            i = j;
        }

        // Calculate the total number of possible original strings without any length constraint.
        long totalWays = 1;
        for (int len : groups) {
            totalWays = (totalWays * len) % MOD;
        }

        // Use DP to find the number of ways to form a string of length LESS THAN k.
        // dp[j] = number of ways to form an original string of length j.
        long[] dp = new long[k];
        dp[0] = 1; // Base case: 1 way to form a string of length 0 (by picking nothing).

        for (int groupLen : groups) {
            long[] newDp = new long[k];
            // Use a sliding window sum to optimize the DP transition.
            long windowSum = 0;
            for (int j = 1; j < k; j++) {
                // Add the new element to the window
                windowSum = (windowSum + dp[j - 1]) % MOD;
                // Remove the old element that's now out of the window's range [1, groupLen]
                if (j - 1 - groupLen >= 0) {
                    windowSum = (windowSum - dp[j - 1 - groupLen] + MOD) % MOD;
                }
                newDp[j] = windowSum;
            }
            // The new DP state for length 0 is always 1 (representing the empty choice from the new group of groups)
            // but we are calculating for strings of length > 0, so we can ignore dp[0] in the newDp calculation.
            // However, the base dp[0]=1 is needed for the first windowSum.
            dp = newDp;
        }

        // Sum up all ways where the length is less than k.
        long waysLessThanK = 0;
        for (long count : dp) {
            waysLessThanK = (waysLessThanK + count) % MOD;
        }

        // The final answer is (Total Ways) - (Ways where sum < k).
        long result = (totalWays - waysLessThanK + MOD) % MOD;
        return (int) result;
    }

}
