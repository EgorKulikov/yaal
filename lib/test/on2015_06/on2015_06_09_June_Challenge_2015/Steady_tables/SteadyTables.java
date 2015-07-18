package on2015_06.on2015_06_09_June_Challenge_2015.Steady_tables;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SteadyTables {
    private static final long MOD = 1000000000;
    long[][] c = IntegerUtils.generateBinomialCoefficients(4000, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        long[] answer = new long[columnCount + 1];
        answer[0] = 1;
        long[] next = new long[columnCount + 1];
        for (int i = 0; i < rowCount; i++) {
            Arrays.fill(next, 0);
            next[0] = answer[0];
            for (int j = 1; j <= columnCount; j++) {
                next[j] = answer[j] + next[j - 1];
            }
            for (int j = 0; j <= columnCount; j++) {
                next[j] %= MOD;
                next[j] *= c[j + columnCount - 1][columnCount - 1];
                next[j] %= MOD;
            }
            long[] temp = answer;
            answer = next;
            next = temp;
        }
        long result = 0;
        for (long l : answer) {
            result += l;
        }
        out.printLine(result % MOD);
    }
}
