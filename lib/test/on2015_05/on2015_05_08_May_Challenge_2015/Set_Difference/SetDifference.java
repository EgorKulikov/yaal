package on2015_05.on2015_05_08_May_Challenge_2015.Set_Difference;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SetDifference {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] set = IOUtils.readIntArray(in, count);
        Arrays.sort(set);
        long[] tp = IntegerUtils.generatePowers(2, count, MOD);
        long answer = 0;
        for (int i = 0; i < count; i++) {
            answer += set[i] * (tp[i] - tp[count - i - 1]) % MOD;
        }
        answer %= MOD;
        if (answer < 0) {
            answer += MOD;
        }
        out.printLine(answer);
    }
}
