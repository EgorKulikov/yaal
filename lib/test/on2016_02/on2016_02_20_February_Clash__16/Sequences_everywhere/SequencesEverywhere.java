package on2016_02.on2016_02_20_February_Clash__16.Sequences_everywhere;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class SequencesEverywhere {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.readLong();
        long b = in.readLong();
        out.printLine(calculate(b) - calculate(a - 1));
    }

    private long calculate(long upTo) {
        long lcm = 1;
        long answer = 0;
        for (int i = 2; upTo > 1; i++) {
            long nLcm = IntegerUtils.lcm(i, lcm);
            long times = nLcm / lcm;
            if (times != 1) {
                long nUpTo = upTo / times;
                answer += (upTo - nUpTo - 1) * (1 + go(i));
                if (nUpTo >= 1) {
                    answer += go(nLcm);
                }
                lcm = nLcm;
                upTo = nUpTo;
            }
        }
        return answer;
    }

    static int go(long x) {
        int answer = 1;
        while (x != 2) {
            answer++;
            for (int i = 2; ; i++) {
                if (x % i != 0) {
                    x = i;
                    break;
                }
            }
        }
        return answer;
    }
}
