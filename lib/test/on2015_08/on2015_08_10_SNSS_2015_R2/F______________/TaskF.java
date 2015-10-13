package on2015_08.on2015_08_10_SNSS_2015_R2.F______________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    private static final long MOD = (long) (1e9 + 9);

    long[] factorial = IntegerUtils.generateFactorial(100001, MOD);
    long[] reverse = IntegerUtils.generateReverseFactorials(100001, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] x = IOUtils.readIntArray(in, n);
        for (int i = 0; i < n; i++) {
            x[i] = Math.min(x[i], m - x[i]);
        }
        long sum = 0;
        for (int i : x) {
            sum += i;
        }
        long min = 2 * ArrayUtils.maxElement(x) - sum;
        if (min <= 0) {
            min = sum & 1;
        }
        long max = sum;
        if (max >= m) {
            if ((m & 1) == (sum & 1)) {
                max = m;
            } else {
                max = m - 1;
            }
        }
        long answer = 0;
        for (long i = min; i <= max; i += 2) {
            answer += c(m, (int)i);
        }
        answer %= MOD;
        out.printLine(answer);
    }

    private long c(int n, int k) {
        return factorial[n] * reverse[k] % MOD * reverse[n - k] % MOD;
    }
}
