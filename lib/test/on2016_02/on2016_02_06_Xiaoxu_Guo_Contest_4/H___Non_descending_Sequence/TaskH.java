package on2016_02.on2016_02_06_Xiaoxu_Guo_Contest_4.H___Non_descending_Sequence;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
//import static net.egork.io.IOUtils.*;
//import static net.egork.misc.MiscUtils.*;
//import static net.egork.misc.ArrayUtils.*;
//import static java.lang.Math.*;
//import static java.util.Arrays.*;

public class TaskH {
    private static final long MOD = 2017;

    private long[] fact = IntegerUtils.generateFactorial(2017, MOD);
    private long[] rev = IntegerUtils.generateReverseFactorials(2017, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = IOUtils.readIntArray(in, n);
        for (int i = n - 2; i >= 0; i--) {
            a[i] = Math.min(a[i], a[i + 1]);
        }
        long[] d = new long[n];
        for (int i = 0; i < n; i++) {
            d[i] = c(a[i] + i + 1, a[i]);
            for (int j = 0; j < i; j++) if (a[j] + 1 <= a[i]) {
                long prev = (j == 0 ? 1 : d[j - 1]);
                d[i] -= prev * c(a[i] - a[j] - 1 + (i - j) + 1, a[i] - a[j] - 1);
            }
            d[i] %= MOD;
            if (d[i] < 0) d[i] += MOD;
        }
        out.printLine(d[n - 1]);
    }

    private long c(int n, int m) {
        if (m < 0 || m > n) {
            return 0;
        }
        if (pow(n) > pow(m) + pow(n - m)) {
            return 0;
        }
        return factor(n, fact) * factor(m, rev) * factor(n - m, rev) % MOD;
    }

    private long factor(int n, long[] fact) {
        int times = n / 2017;
        long answer = fact[n % 2017];
        if ((times & 1) == 1) {
            answer = MOD - answer;
        }
        if (times == 0) {
            return answer;
        }
        return answer * factor(times, fact) % MOD;
    }

    private int pow(int n) {
        int answer = 0;
        do {
            n /= 2017;
            answer += n;
        } while (n >= 2017);
        return answer;
    }
}
