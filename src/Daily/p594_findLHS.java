package Daily;

import java.util.HashMap;
import java.util.Map;

public class p594_findLHS {
    public static void main(String[] args) {
        System.out.println(findLHS(new int[] {1,3,2,2,5,2,3,7})); // 5
        System.out.println(findLHS(new int[] {1,2,3,4})); // 2
        System.out.println(findLHS(new int[] {1,1,1,1})); // 0
    }

    public static int findLHS(int[] nums) {
        int result = 0;

        // Use a frequency map to help
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        // loop through the map to get the pair that their difference is 1
        for (int key : freqMap.keySet()) {
            // Check if the next key is exactly 1 different from a current key.
            if (freqMap.containsKey(key + 1)) {
                int currentLength = freqMap.get(key) + freqMap.get(key + 1);
                result = Math.max(result, currentLength);
            }
        }
        return result;
    }
}
