package net.egork.numbers;

/**
 * @author egor@egork.net
 */
public class Interpolation {
    private long[] values;
    private long[] coef;
    private long mod;

    public Interpolation(long[] values, long mod) {
        this(values, mod, IntegerUtils.generateReverseFactorials(values.length, mod));
    }

    public Interpolation(long[] values, long mod, long[] revFact) {
        this.values = values.clone();
        this.mod = mod;
        coef = new long[values.length];
        for (int i = 0; i < values.length; i++) {
            coef[i] = values[i] * revFact[i] % mod * revFact[values.length - i - 1] % mod;
            if (((values.length - i - 1) & 1) == 1) {
                coef[i] = mod - coef[i];
            }
        }
    }

    public long calculate(long x) {
        x %= mod;
        if (x < 0) {
            x += mod;
        }
        if (x < values.length) {
            return values[(int) x];
        }
        long product = 1;
        for (int j = 0; j < values.length; j++) {
            product *= x - j;
            product %= mod;
        }
        long answer = 0;
        for (int i = 0; i < values.length; i++) {
            answer += coef[i] * product % mod * IntegerUtils.reverse(x - i, mod) % mod;
        }
        return answer % mod;
    }
}
