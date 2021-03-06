package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
    long[][] answer;
    int[] a;
    int smallPart;
    int bigPart;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        a = IOUtils.readIntArray(in, n);
        ArrayUtils.sort(a, IntComparator.DEFAULT);
        smallPart = n / k;
        bigPart = smallPart + 1;
        int bigCount = n % k;
        int smallCount = k - bigCount;
        answer = new long[smallCount + 1][bigCount + 1];
        ArrayUtils.fill(answer, -1);
        answer[0][0] = 0;
        out.printLine(calculate(smallCount, bigCount));
    }

    private long calculate(int smallCount, int bigCount) {
        if (answer[smallCount][bigCount] != -1) {
            return answer[smallCount][bigCount];
        }
        int at = smallPart * smallCount + bigPart * bigCount;
        if (smallCount == 0) {
            return answer[smallCount][bigCount] = calculate(smallCount, bigCount - 1) + a[at - 1] - a[at - bigPart];
        }
        if (bigCount == 0) {
            return answer[smallCount][bigCount] = calculate(smallCount - 1, bigCount) + a[at - 1] - a[at - smallPart];
        }
        return answer[smallCount][bigCount] = Math.min(calculate(smallCount - 1, bigCount) + a[at - 1] - a[at - smallPart],
            calculate(smallCount, bigCount - 1) + a[at - 1] - a[at - bigPart]);
    }
}
