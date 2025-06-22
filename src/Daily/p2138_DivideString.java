package Daily;

import java.util.Arrays;

public class p2138_DivideString {
    public static void main(String[] args) {
        String s = "abcdefghi";
        int k = 3;
        char fill = 'x';
        System.out.println(Arrays.toString(divideString(s, k, fill)));

        s = "abcdefghij";
        System.out.println(Arrays.toString(divideString(s, k, fill)));
    }

    public static String[] divideString(String s, int k, char fill) {
        int n = 0;
        if (s.length() % k == 0) {
            n = s.length() / k;
        } else {
            n = s.length() / k + 1;
        }
        String[] result = new String[n];
        int i = 0;
        while (s.length() > k - 1) {
            result[i] = s.substring(0, k);
            s = s.substring(k);
            i++;
        }
        StringBuilder sBuilder = new StringBuilder(s);
        if (!sBuilder.isEmpty()) {
            while (sBuilder.length() < k) {
                sBuilder.append(fill);
            }
            s = sBuilder.toString();
            result[i] = s;
        }
        return result;
    }
}
