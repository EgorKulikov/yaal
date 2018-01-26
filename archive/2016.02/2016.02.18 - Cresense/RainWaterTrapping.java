package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class RainWaterTrapping {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int[] left = new int[n];
        left[0] = a[0];
        for (int i = 1; i < n; i++) {
            left[i] = max(left[i - 1], a[i]);
        }
        int[] right = new int[n];
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = max(right[i + 1], a[i]);
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += min(left[i], right[i]) - a[i];
        }
        out.printLine(answer);
    }
}
