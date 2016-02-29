package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class HBearAndCookies2 {
    double[] answer;
    int[] a;
    int[] b;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        a = readIntArray(in, n);
        b = readIntArray(in, n);
        answer = new double[1 << n];
        fill(answer, Double.NaN);
        out.printLine(go(0));
    }

    private double go(int mask) {
        if (!Double.isNaN(answer[mask])) {
            return answer[mask];
        }
        answer[mask] = 0;
        for (int i = 0; i < a.length; i++) {
            if ((mask >> i & 1) == 0) {
                answer[mask] = Math.max(answer[mask], opp(mask + (1 << i)) + a[i]);
            }
        }
        return answer[mask];
    }

    private double opp(int mask) {
        if (!Double.isNaN(answer[mask])) {
            return answer[mask];
        }
        int max = 0;
        for (int i = 0; i < b.length; i++) {
            if ((mask >> i & 1) == 0) {
                max = Math.max(max, b[i]);
            }
        }
        answer[mask] = 0;
        if (max == 0) {
            return 0;
        }
        int time = 0;
        for (int i = 0; i < b.length; i++) {
            if ((mask >> i & 1) == 0 && max == b[i]) {
                time++;
                answer[mask] += go(mask + (1 << i));
            }
        }
        answer[mask] /= time;
        return answer[mask];
    }
}
