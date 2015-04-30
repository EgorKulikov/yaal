package on2015_04.on2015_04_28_Single_Round_Match_657.PolynomialGCD;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class PolynomialGCD {
    private static final long MOD = (long) (1e9 + 7);

    public int gcd(String s) {
        int[] primes = IntegerUtils.generatePrimes(s.length() + 1);
        long answer = 1;
        for (int i : primes) {
            answer *= IntegerUtils.power(i, get(0, 1, i, s), MOD);
            answer %= MOD;
        }
		return (int)answer;
    }

    private int get(int shift, int current, int p, String s) {
        if (shift + current * (p - 1) > s.length()) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int i = shift; i < current * p; i += current) {
            int value = get(i, current * p, p, s);
            for (int j = i; j < s.length(); j += current * p) {
                value += s.charAt(j) - '0';
            }
            result = Math.min(result, value);
        }
        return result;
    }
}
