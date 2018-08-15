package on2018_08.on2018_08_15_TopCoder_SRM__736.Subpolygon;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.PrimeFastFourierTransform;

import static java.util.Arrays.copyOf;
import static net.egork.misc.MiscUtils.MODF;
import static net.egork.numbers.IntegerUtils.generatePowers;
import static net.egork.numbers.IntegerUtils.reverse;

public class Subpolygon {
    public int sumOfAreas(int n) {
        long[] x = new long[4 * n];
        long[] y = new long[4 * n];
        x[0] = (long)n * (n - 1) / 2;
        for (int i = 0; i < n; i++) {
            x[i + 1] = x[i] + n - i;
            y[i + 1] = y[i] + i;
        }
        for (int i = 0; i < n; i++) {
            x[n + i + 1] = x[n + i] - i;
            y[n + i + 1] = y[n + i] + n - i;
        }
        for (int i = 0; i < n; i++) {
            x[2 * n + i + 1] = x[2 * n + i] - (n - i);
            y[2 * n + i + 1] = y[2 * n + i] - i;
        }
        for (int i = 0; i < n - 1; i++) {
            x[3 * n + i + 1] = x[3 * n + i] + i;
            y[3 * n + i + 1] = y[3 * n + i] - (n - i);
        }
        for (int i = 0; i < 4 * n; i++) {
            x[i] %= MODF;
            y[i] %= MODF;
        }
        PrimeFastFourierTransform fft = new PrimeFastFourierTransform(MODF);
        ArrayUtils.reverse(y);
        x = copyOf(x, 8 * n);
        y = copyOf(y, 8 * n);
        long[] result = new long[8 * n];
        fft.multiply(result, x, y, 8 * n);
        long[] p2 = generatePowers(2, 4 * n, MODF);
        long answer = 0;
        for (int i = 0; i < 4 * n - 1; i++) {
            answer += result[i] * (p2[4 * n - i - 2] - p2[i]) % MODF;
        }
        for (int i = 1; i < 4 * n; i++) {
            answer += result[4 * n + i - 1] * (p2[4 * n - i - 1] - p2[i - 1]) % MODF;
        }
        answer = -answer;
        answer %= MODF;
        answer += MODF;
        answer %= MODF;
        answer *= reverse(2, MODF);
        answer %= MODF;
        return (int) answer;
    }
}
