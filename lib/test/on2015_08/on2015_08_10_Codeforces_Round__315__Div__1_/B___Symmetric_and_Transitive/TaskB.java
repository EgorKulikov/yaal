package on2015_08.on2015_08_10_Codeforces_Round__315__Div__1_.B___Symmetric_and_Transitive;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    static final long MOD = (long) (1e9 + 7);
    long[][] c;

    long[] result;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long answer = 0;
        result = ArrayUtils.createArray(n, -1L);
        c = IntegerUtils.generateBinomialCoefficients(n + 1, MOD);
        for (int i = 1; i <= n; i++) {
            answer += c[n][i] * calculate(n - i) % MOD;
        }
        out.printLine(answer % MOD);
    }

    private long calculate(int n) {
        if (result[n] != -1) {
            return result[n];
        }
        result[n] = 1;
        for (int i = 1; i < n; i++) {
            result[n] += calculate(n - i) * c[n - 1][i - 1] % MOD;
        }
        return result[n] %= MOD;
    }
}
