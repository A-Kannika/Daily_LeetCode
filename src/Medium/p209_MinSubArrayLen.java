package Medium;

public class p209_MinSubArrayLen {
    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(target, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {

        // check if nums is empty
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int count = Integer.MAX_VALUE;
        int first = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                count = Math.min(count, i - first + 1);
                sum -= nums[first];
                first++;
            }
        }
        if (count == Integer.MAX_VALUE) {
            return 0;
        } else {
            return count;
        }
    }
}
