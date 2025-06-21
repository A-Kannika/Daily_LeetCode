package Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class p1_TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[] {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
    // we can use the hash map to solve this problem
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2]; // the return size is 2
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }
}

