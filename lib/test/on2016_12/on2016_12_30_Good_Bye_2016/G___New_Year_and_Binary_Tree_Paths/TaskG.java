package on2016_12.on2016_12_30_Good_Bye_2016.G___New_Year_and_Binary_Tree_Paths;



import net.egork.collections.map.EHashMap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class TaskG {
    Map<Long, Long>[][] result = new Map[51][51];
    long[][] max = new long[51][51];

    {
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                result[i][j] = new EHashMap<>();
                max[i][j] = (1L << i) - 1 - i + (1L << j) - 1 - j;
            }
        }
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long s = in.readLong();
        long answer = 0;
        for (int i = 0; (1L << (i + 1)) - 2 <= s; i++) {
            for (int j = 0; (1L << (j + 1)) + (1L << (i + 1)) - 3 <= s; j++) {
                long maxDelta = max[i][j];
                long by = (1L << (i + 1)) + (1L << (j + 1)) - 3;
                for (long v = s / by; v > 0; v--) {
                    long remaining = s - v * by - ((1L << j) - 1);
                    if (remaining < 0) {
                        continue;
                    }
                    if (remaining > maxDelta) {
                        break;
                    }
                    answer += query(max(max(i, j), 0), max(min(i, j), 0), remaining);
                }
            }
        }
        out.printLine(answer);
    }

    private long query(int mx, int mn, long val) {
        if (val > max[mx][mn]) {
            return 0;
        }
        if (mx <= 1) {
            return 1;
        }
        if (result[mx][mn].containsKey(val)) {
            return result[mx][mn].get(val);
        }
        long by = (1L << (mx - 1)) - 1;
        if (mx == mn) {
            long result = query(mx - 1, mn - 1, val);
            if (val >= by) {
                result += query(mx - 1, mn - 1, val - by) * 2;
            }
            if (val >= 2 * by) {
                result += query(mx - 1, mn - 1, val - 2 * by);
            }
            this.result[mx][mn].put(val, result);
            return result;
        }
        long result = query(mx - 1, mn, val);
        if (val >= by) {
            result += query(mx - 1, mn, val - by);
        }
        this.result[mx][mn].put(val, result);
        return result;
    }
}
