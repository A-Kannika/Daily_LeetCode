package Array;

public class p643_MaximumAverageSubarrayI {
    public static void main(String[] args) {
        int[] nums = {10};
        int k = 1;
        System.out.println(findMaxAverage(nums, k));
    }

    // Use sliding windows
    // find a sum of elements from index 0 to k -1 (k elements)
    // then subtract the first element and add the next element
    // com[are to get the max
    public static double findMaxAverage(int[] nums, int k) {
        double max = (double) Integer.MIN_VALUE;
        double sum = 0;
        int first = 0;
        int div = k;
        if (nums.length == 1) {
            return (double) nums[0];
        }
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        max = sum / k;
        while (k < nums.length) {
            sum -= nums[first++];
            sum += nums[k++];
            max = Math.max(max, sum / div);
        }
        return max;
    }

}
