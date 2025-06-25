package Daily;

import java.util.Arrays;
import java.util.Arrays;

public class p2040_KthSmallestProduct {
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("Example 1: Should be 8 -> " + kthSmallestProduct(new int[]{2, 5}, new int[]{3, 4}, 2));
        System.out.println("Example 2: Should be 0 -> " + kthSmallestProduct(new int[]{-4, -2, 0, 3}, new int[]{2, 4}, 6));
        System.out.println("Example 3: Should be -6 -> " + kthSmallestProduct(new int[]{-2, -1, 0, 1, 2}, new int[]{-3, -1, 2, 4, 5}, 3));
        System.out.println("Failing Test Case: Should be 54 -> " + kthSmallestProduct(new int[]{-6}, new int[]{-9}, 1));
    }

    public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // The search space for the product's value is between -10^10 and 10^10.
        long left = -10000000001L; // A value lower than any possible product
        long right = 10000000001L; // A value higher than any possible product

        while (left < right) {
            long mid = left + (right - left) / 2;

            // Count how many products are less than or equal to 'mid'.
            if (countLessEqual(nums1, nums2, mid) < k) {
                // If the count is too small, the kth product must be larger than mid.
                left = mid + 1;
            } else {
                // If the count is sufficient, mid could be the answer, or the answer is smaller.
                right = mid;
            }
        }

        return left;
    }

    /**
     * Counts how many products nums1[i] * nums2[j] are less than or equal to x.
     */
    private static long countLessEqual(int[] nums1, int[] nums2, long x) {
        long count = 0;
        for (int a : nums1) {
            if (a > 0) {
                // We need b <= x/a, which is b <= floor(x/a).
                long target = x / a;
                // Java's division `/` truncates toward zero.
                // For negative x and positive a, this is ceil, not floor (e.g., -7 / 2 = -3).
                // We must adjust it downward to get the correct floor.
                if (x < 0 && x % a != 0) {
                    target--;
                }
                count += upperBound(nums2, target);
            } else if (a < 0) {
                // We need b >= x/a, which is b >= ceil(x/a).
                // The inequality sign flips because we are dividing by a negative number.
                long target = ceilDiv(x, a);
                count += nums2.length - lowerBound(nums2, target);
            } else { // a == 0
                // The product is 0. We need 0 <= x.
                if (x >= 0) {
                    count += nums2.length;
                }
            }
        }
        return count;
    }

    /**
     * BUG FIX: Correctly calculates the ceiling of x/a for all integer values.
     * The previous implementation was flawed for certain sign combinations.
     * Using Math.ceil with a double cast is the most robust solution.
     */
    private static long ceilDiv(long x, long a) {
        return (long) Math.ceil((double) x / a);
    }

    // Binary search: find first index where nums[i] > target (upper bound)
    // This correctly counts the number of elements <= target
    private static int upperBound(int[] nums, long target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // Binary search: find first index where nums[i] >= target (lower bound)
    private static int lowerBound(int[] nums, long target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

//public class p2040_KthSmallestProduct {
//    // Main method for testing
//    public static void main(String[] args) {
//        System.out.println("Example 1: Should be 8 -> " + kthSmallestProduct(new int[]{2, 5}, new int[]{3, 4}, 2));
//        System.out.println("Example 2: Should be 0 -> " + kthSmallestProduct(new int[]{-4, -2, 0, 3}, new int[]{2, 4}, 6));
//        System.out.println("Example 3: Should be -6 -> " + kthSmallestProduct(new int[]{-2, -1, 0, 1, 2}, new int[]{-3, -1, 2, 4, 5}, 3));
//    }
//
//    public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
//        // The search space for the product's value is between -10^10 and 10^10.
//        long left = -10000000001L; // A value lower than any possible product
//        long right = 10000000001L; // A value higher than any possible product
//
//        while (left < right) {
//            long mid = left + (right - left) / 2;
//
//            // Count how many products are less than or equal to 'mid'.
//            if (countLessEqual(nums1, nums2, mid) < k) {
//                // If the count is too small, the kth product must be larger than mid.
//                left = mid + 1;
//            } else {
//                // If the count is sufficient, mid could be the answer, or the answer is smaller.
//                right = mid;
//            }
//        }
//
//        return left;
//    }
//
//    /**
//     * Counts how many products nums1[i] * nums2[j] are less than or equal to x.
//     * This is the corrected version of the function.
//     */
//    private static long countLessEqual(int[] nums1, int[] nums2, long x) {
//        long count = 0;
//        for (int a : nums1) {
//            if (a > 0) {
//                // We need b <= x/a, which is b <= floor(x/a).
//                long target = x / a;
//                // BUG FIX: Java's division `/` truncates toward zero.
//                // For negative x and positive a, this is ceil, not floor (e.g., -7 / 2 = -3).
//                // We must adjust it downward to get the correct floor.
//                if (x < 0 && x % a != 0) {
//                    target--;
//                }
//                count += upperBound(nums2, target);
//            } else if (a < 0) {
//                // We need b >= x/a, which is b >= ceil(x/a).
//                // The inequality sign flips because we are dividing by a negative number.
//                long target = ceilDiv(x, a);
//                count += nums2.length - lowerBound(nums2, target);
//            } else { // a == 0
//                // The product is 0. We need 0 <= x.
//                if (x >= 0) {
//                    count += nums2.length;
//                }
//            }
//        }
//        return count;
//    }
//
//    // Integer ceiling division of x by a. Your original implementation was correct.
//    private static long ceilDiv(long x, long a) {
//        if (x % a == 0) return x / a;
//        // This clever XOR check correctly determines if signs are the same
//        return (x / a) + ((x > 0) == (a > 0) ? 0 : 1);
//    }
//
//    // A slightly more readable version of your correct ceilDiv logic
//    private static long ceilDiv_readable(long x, long a) {
//        // For a < 0
//        return (long) Math.ceil((double) x / a);
//    }
//
//
//    // Binary search: find first index where nums[i] > target (upper bound)
//    // This correctly counts the number of elements <= target
//    private static int upperBound(int[] nums, long target) {
//        int left = 0, right = nums.length;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] <= target) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return left;
//    }
//
//    // Binary search: find first index where nums[i] >= target (lower bound)
//    private static int lowerBound(int[] nums, long target) {
//        int left = 0, right = nums.length;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] < target) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return left;
//    }
//}