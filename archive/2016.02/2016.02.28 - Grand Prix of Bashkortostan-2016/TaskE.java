package net.egork;

import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskE {
    int n;
    int[] p;

    long[] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        p = readIntArray(in, n);
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0 && p[i] != 0 && p[i] <= n / 2) {
                out.printLine(0);
                return;
            }
            if (i % 2 == 1 && p[i] > n / 2) {
                out.printLine(0);
                return;
            }
        }
        answer = createArray(1 << (n / 2), -1L);
        out.printLine(go(n / 2, 0));
    }

    private long go(int current, int mask) {
        if (answer[mask] != -1) {
            return answer[mask];
        }
        if (current == 0) {
            return answer[mask] = 1;
        }
        int from = 0;
        int to = n / 2 - 1;
        for (int i = 1; i < n; i += 2) {
            if (p[i] == current) {
                from = i / 2;
                to = i / 2;
            }
        }
        answer[mask] = 0;
        for (int i = from; i <= to; i++) {
            if ((mask >> i & 1) == 1) {
                continue;
            }
            if (p[2 * i] != 0 && p[2 * i] < 2 * current) {
                continue;
            }
            if (p[2 * i + 2] != 0 && p[2 * i + 2] < 2 * current) {
                continue;
            }
            int remaining = min(n - 2 * current + 1, n / 2 + 1);
            for (int j = 0; j <= n / 2; j++) {
                if (p[2 * j] >= 2 * current || (mask >> j & 1) == 1 || j > 0 && (mask >> (j - 1) & 1) == 1) {
                    remaining--;
                }
            }
            int times = 2;
            if (i != 0 && (mask >> (i - 1) & 1) == 1 || p[2 * i] >= 2 * current) {
                times--;
            }
            if ((mask >> (i + 1) & 1) == 1 || p[2 * i + 2] >= 2 * current) {
                times--;
            }
            if (remaining >= times) {
                long result = go(current - 1, mask + (1 << i));
                if (times > 0) {
                    result *= remaining;
                }
                if (times > 1) {
                    result *= remaining - 1;
                }
                answer[mask] += result;
            }
        }
        return answer[mask];
    }

}
