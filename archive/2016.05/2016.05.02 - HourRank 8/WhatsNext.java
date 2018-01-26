package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.copyOf;
import static net.egork.io.IOUtils.readLongArray;

public class WhatsNext {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] a = readLongArray(in, n);
        long[] result;
        if (a.length <= 2) {
            result = new long[3];
            result[0] = 1;
            result[1] = 1 + (a.length == 1 ? 0 : a[1]);
            result[2] = a[0] - 1;
        } else {
            result = copyOf(a, n + 2);
            int last = a.length - 1;
            if ((last & 1) == 1) {
                last--;
            }
            result[last - 1]--;
            result[last] = 1;
            result[last + 1] = 1 + (last + 1 < a.length ? a[last + 1] : 0);
            result[last + 2] = a[last] - 1;
        }
        while (result[result.length - 1] == 0) {
            result = copyOf(result, result.length - 1);
        }
        for (int i = 1; i < result.length - 1; i++) {
            if (result[i] == 0) {
                result[i - 1] += result[i + 1];
                System.arraycopy(result, i + 2, result, i, result.length - i - 2);
                result = copyOf(result, result.length - 2);
            }
        }
        out.printLine(result.length);
        out.printLine(result);
    }
}
