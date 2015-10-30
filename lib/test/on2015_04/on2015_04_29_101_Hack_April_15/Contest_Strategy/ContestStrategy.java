package on2015_04.on2015_04_29_101_Hack_April_15.Contest_Strategy;


import net.egork.generated.collections.comparator.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ContestStrategy {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int explanation = in.readInt();
        int[] time = IOUtils.readIntArray(in, count);
        ArrayUtils.sort(time, IntComparator.REVERSE);
        long left = time[0];
        long right = time[0] + (long)(count - 1) * explanation;
        while (left < right) {
            long middle = (left + right) >> 1;
            long curTime = 0;
            int explained = 1;
            int solved = 0;
            while (solved < explained) {
                while (solved < explained && time[solved] + curTime + explanation > middle) {
                    solved++;
                }
                curTime += explanation;
                explained += explained - solved;
                explained = Math.min(explained, count);
            }
            if (solved == count) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine(left);
    }
}
