package Daily;

import java.util.*;

public class p2200_FindKDistantIndices {
    public static void main(String[] args) {
        int[] nums = {3,4,9,1,3,9,5};
        int key = 9;
        int k = 1;
        System.out.println(findKDistantIndices(nums, key, k));
    }

    // use map
    public static List<Integer> findKDistantIndices1(int[] nums, int key, int k) {
        HashMap<Integer, Boolean> indexMap = new HashMap<>();
        int n = nums.length;

        // Step 1: Find all positions where nums[i] == key
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                int start = Math.max(0, i - k);
                int end = Math.min(n - 1, i + k);
                for (int j = start; j <= end; j++) {
                    indexMap.put(j, true);  // Mark index j as valid
                }
            }
        }

        // Step 2: Extract keys (indices) and sort them
        List<Integer> result = new ArrayList<>(indexMap.keySet());
        Collections.sort(result);
        return result;
    }

    // use boolean flag
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        boolean[] marked = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                int start = Math.max(0, i - k);
                int end = Math.min(n - 1, i + k);
                for (int j = start; j <= end; j++) {
                    marked[j] = true;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (marked[i]) {
                result.add(i);
            }
        }

        return result;
    }

    // Use Set
    public static List<Integer> findKDistantIndices2(int[] nums, int key, int k) {
        HashSet<Integer> resultSet = new HashSet<>();
        int n = nums.length;

        // Find all indices where nums[i] == key
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                // Add all indices in the range [i-k, i+k]
                int start = Math.max(0, i - k);
                int end = Math.min(n - 1, i + k);
                for (int j = start; j <= end; j++) {
                    resultSet.add(j);
                }
            }
        }

        List<Integer> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }
}
