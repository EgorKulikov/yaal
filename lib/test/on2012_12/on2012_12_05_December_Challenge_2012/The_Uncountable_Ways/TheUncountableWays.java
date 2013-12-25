package on2012_12.on2012_12_05_December_Challenge_2012.The_Uncountable_Ways;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheUncountableWays {
    static final long MOD = (long) (1e9 + 7);
    long[] factorial = IntegerUtils.generateFactorial(800001, MOD);
    long[] reverse = IntegerUtils.generateReverseFactorials(800001, MOD);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        int rowDeleted = in.readInt();
        int columnDeleted = in.readInt();
        long answer = c(columnCount, rowCount);
        for (int i = 0; i < rowDeleted; i++)
            answer -= c(columnCount - columnDeleted, i) * c(columnDeleted - 1, rowCount - i) % MOD;
        answer %= MOD;
        answer += MOD;
        answer %= MOD;
        out.printLine(answer);
	}

    private long c(int n, int m) {
        return factorial[n + m] * reverse[m] % MOD * reverse[n] % MOD;
    }
}
