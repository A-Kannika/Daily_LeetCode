package Array;

import java.util.*;

public class p217_ContainDuplicate {
    public static void main(String[] args) {
        int[] nums = new int[] {2,7,11,15, 2};
        System.out.println(containsDuplicate(nums));
    }

    // Unsorted Array --> don't need the key & value pair --> don't use HashMap
    // Use HashSet
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }
}
