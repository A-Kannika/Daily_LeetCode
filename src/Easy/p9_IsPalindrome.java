package Easy;

public class p9_IsPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(1234321));
    }
    public static boolean isPalindrome(int x) {
        // if x is negative
        if (x < 0) {
            return false;
        }
        // convert to String
        String num = String.valueOf(x);

        // check each pair from out to in
        int left = 0;
        int right = num.length() - 1;
        while (left < right) {
            if (num.charAt(left) != num.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
