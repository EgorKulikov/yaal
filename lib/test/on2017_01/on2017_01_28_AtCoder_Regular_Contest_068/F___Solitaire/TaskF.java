package on2017_01.on2017_01_28_AtCoder_Regular_Contest_068.F___Solitaire;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.power;

public class TaskF {
    long[][] answer;
    long[][] sum;
    int base;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        base = n - k + 1;
        answer = new long[n + 1][n + 1];
        sum = new long[n + 1][n + 1];
        fill(answer, -1);
        fill(sum, -1);
        long answer = go(n, n) * power(2, max(n - k - 1, 0), MOD7) % MOD7;
        out.printLine(answer);
    }

    private long go(int n, int lim) {
        lim = Math.min(lim, n);
        if (answer[n][lim] != -1) {
            return answer[n][lim];
        }
        if (n == base) {
            return answer[n][lim] = 1;
        }
        return answer[n][lim] = (go(n - 1, lim) + goSum(n - 1, lim - 1)) % MOD7;
    }

    private long goSum(int n, int lim) {
        if (sum[n][lim] != -1) {
            return sum[n][lim];
        }
        if (lim <= 1) {
            return sum[n][lim] = 0;
        }
        return sum[n][lim] = (go(n, lim) + goSum(n, lim - 1)) % MOD7;
    }
}
