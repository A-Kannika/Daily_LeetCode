package Daily;

import java.util.Arrays;

public class p1498_numSubseq {
    public static void main(String[] args) {
        System.out.println(numSubseq(new int[]{3, 5, 6, 7}, 9)); // 4
        System.out.println(numSubseq(new int[]{3, 3, 6, 8}, 10)); // 6
        System.out.println(numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 12)); // 61
    }

    // Use 2 pointers
    public static int numSubseq(int[] nums, int target) {
        // Define the modulo constant.
        final int MOD = 1_000_000_007;

        // Sort the array. This is crucial for the two-pointer approach.
        Arrays.sort(nums);

        int n = nums.length;
        long count = 0;
        int left = 0;
        int right = n - 1;

        // Pre-compute powers of 2 to avoid recalculating 2^n in the loop.
        // powers[i] will store (2^i) % MOD.
        long[] powers = new long[n];
        powers[0] = 1;
        for (int i = 1; i < n; i++) {
            powers[i] = (powers[i - 1] * 2) % MOD;
        }

        // Use the two-pointer approach.
        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                count = (count + powers[right - left]) % MOD;
                left++;
            }
        }

        return (int) count;
    }
}
