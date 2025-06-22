package Daily;

public class p2016_maximumDifference {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,1};
        System.out.println(maximumDifference(nums));
    }
    // Bruce force
    public static int maximumDifference(int[] nums) {
        int min = nums[0];
        int maxDifference = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > min) {
                maxDifference = Math.max(maxDifference, nums[i] - min);
            } else {
                min = nums[i];
            }
        }
        return maxDifference;
    }
}