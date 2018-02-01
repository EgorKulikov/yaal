package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class P6 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        int[] val = new int[n + m];
        for (int i = 0; i < m; i++) {
            val[i + n] = 1 - val[a[i]] * val[b[i]];
        }
        int val0 = val[n + m - 1];
        fill(val, 0, n, 1);
        for (int i = 0; i < m; i++) {
            val[i + n] = 1 - val[a[i]] * val[b[i]];
        }
        int val1 = val[n + m - 1];
        if (val0 == val1) {
            out.printLine(createArray(n, '0'));
            return;
        }
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int middle = (left + right + 1) >> 1;
            fill(val, 0, middle, 1);
            fill(val, middle, n, 0);
            for (int i = 0; i < m; i++) {
                val[i + n] = 1 - val[a[i]] * val[b[i]];
            }
            if (val[n + m - 1] == val0) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        out.print(createArray(left, '1'));
        out.print('x');
        out.printLine(createArray(n - left - 1, '0'));
    }
}
