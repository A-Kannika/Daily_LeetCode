package Daily;

public class p3405_CountGoodArrays {
    public static void main(String[] args) {
        System.out.println(countGoodArrays(3, 2, 1));
        System.out.println(countGoodArrays(4, 2, 2));
        System.out.println(countGoodArrays(5, 2, 0));
        System.out.println(countGoodArrays(10, 9, 0));
    }

    static final int MOD = 1_000_000_007;
    static long[] fact;
    static long[] invFact;
    public static int countGoodArrays(int n, int m, int k) {
        // number of groups
        int g = n - k - 1;
        if (g < 0 || g > n - 1) return 0;

        // Precompute the factorials and the inverse factorials
        // (n)
        // (k) = (n! / k! * (n-k)!) mod p = n! * (k!) ^ -1 * ((n-k)!) ^ -1 mod p
        if (fact == null || fact.length < n) {
            fact = new long[n];
            invFact = new long[n];

            fact[0] = 1;
            for (int i = 1; i < n; i++) {
                fact[i] = fact[i - 1] * i % MOD;
            }

            long base = fact[n - 1];
            int exp = MOD - 2;
            long result = 1;
            base %= MOD;
            while (exp > 0) {
                if ((exp & 1) == 1) {
                    result = (result * base) % MOD;
                }
                base = (base * base) % MOD;
                exp >>= 1;
            }
            invFact[n - 1] = result;

            for (int i = n - 2; i >= 0; i--) {
                invFact[i] = invFact[i + 1] * (i + 1) % MOD;
            }
        }

        // m
        long binom = fact[n - 1];
        binom = (binom * invFact[g]) % MOD;
        binom = (binom * invFact[n - 1 - g]) % MOD;
        long mVal = m % MOD;

        // (m-1) ^ g
        long base = m - 1;
        int exp = g;
        long powResult = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                powResult = (powResult * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        // total possibilities
        long result = (binom * mVal % MOD) * powResult % MOD;
        return (int) result;
    }
}
