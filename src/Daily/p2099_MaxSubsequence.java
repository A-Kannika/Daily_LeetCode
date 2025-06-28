package Daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class p2099_MaxSubsequence {
    public static void main(String[] args) {
        int[] nums = {2,1,3,3};
        int k = 3;
        maxSubsequence(nums, k);
        System.out.println(Arrays.toString(maxSubsequence(nums, k)));

    }

    public static int[] maxSubsequence(int[] nums, int k) {
        // From hint2: Could you sort the array while maintaining the index?
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        // Use Map to count the largest set of k elements from sorted array
        Map<Integer, Integer> bigKNumsCounts = new HashMap<>();
        for (int i = nums.length - 1; i >= nums.length - k; i--) {
            int num = sortedNums[i];
            bigKNumsCounts.put(num, bigKNumsCounts.getOrDefault(num, 0) + 1);
        }
        // System.out.println(bigKNumsCounts);

        // Reconstruct the subsequence while maintaining original order.
        int[] result = new int[k];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (bigKNumsCounts.containsKey(num) && bigKNumsCounts.get(num) > 0) {
                result[index] = num;
                index++;
                bigKNumsCounts.put(num, bigKNumsCounts.get(num) - 1);
            }
        }
        return result;
    }
}
