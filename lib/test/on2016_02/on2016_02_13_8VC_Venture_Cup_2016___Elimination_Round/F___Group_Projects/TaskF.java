package on2016_02.on2016_02_13_8VC_Venture_Cup_2016___Elimination_Round.F___Group_Projects;



import net.egork.generated.collections.comparator.IntComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
    private static final long MOD = (long) (1e9 + 7);
    long[][][] answer;
    int[] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        a = readIntArray(in, n);
        sort(a, IntComparator.DEFAULT);
        answer = new long[n + 1][n + 1][k + 1];
        fill(answer, -1);
        long result = go(0, 0, k, 0);
        out.printLine(result);
    }

    private long go(int current, int open, int remaining, int last) {
        if (answer[current][open][remaining] != -1) {
            return answer[current][open][remaining];
        }
        if (a.length - current < open) {
            return answer[current][open][remaining] = 0;
        }
        if (current == a.length) {
            return answer[current][open][remaining] = 1;
        }
        int realRemaining = remaining - (a[current] - last) * open;
        if (realRemaining < 0) {
            return answer[current][open][remaining] = 0;
        }
        answer[current][open][remaining] = go(current + 1, open, realRemaining, a[current]);
        if (open > 0) {
            answer[current][open][remaining] += go(current + 1, open, realRemaining, a[current]) * open;
            answer[current][open][remaining] += go(current + 1, open - 1, realRemaining, a[current]) * open;
        }
        answer[current][open][remaining] += go(current + 1, open + 1, realRemaining, a[current]);
        return answer[current][open][remaining] %= MOD;
    }
}
