package Array;

import java.util.Arrays;

public class p268_MissingNumber {
    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber(nums));
        nums = new int[]{3, 0, 1};
        System.out.println(missingNumber(nums));
        nums = new int[]{0,1,2,3,4,5,6,7,9,10,11,12,13,14,15};
        System.out.println(missingNumber(nums));
        nums = new int[]{0, 1};
        System.out.println(missingNumber(nums));
        nums = new int[]{1};
        System.out.println(missingNumber(nums));
    }

    // sigma i when i = 0 to n
    // n * (n + 1)/ 2
    // then subtract all elements from the array to get the missing value
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for (int i = 0; i < n; i++) {
            sum -= nums[i];
        }
        return sum;
    }
}
