package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.order;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class StrangeTransform {
    private static final int BUBEN1 = 1024;
    private static final int BUBEN2 = 256;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = in.readIntArray(n);
        int[] k = new int[q];
        int[] x = new int[q];
        in.readIntArrays(k, x);
        decreaseByOne(x);
        int[] step = new int[q];
        for (int i = 0; i < q; i++) {
            step[i] = k[i] & (BUBEN1 - 1);
        }
        boolean[][] has = new boolean[BUBEN2][BUBEN2];
        has[0][0] = true;
        for (int i = 1; i < BUBEN2; i++) {
            has[i][0] = true;
            for (int j = 1; j < BUBEN2; j++) {
                has[i][j] = has[i - 1][j] ^ has[i - 1][j - 1];
            }
        }
        int[] order = order(step);
        int[] answer = new int[q];
        int curStep = 0;
        for (int i : order) {
            while (curStep < step[i]) {
                for (int j = 0; j < n - 1; j++) {
                    a[j] ^= a[j + 1];
                }
                curStep++;
            }
            int at = (k[i] / BUBEN1) & (BUBEN2 - 1);
            for (int j = 0, l = x[i]; l < n; j++, l += BUBEN1) {
                if (has[at][j]) {
                    answer[i] ^= a[l];
                }
            }
        }
        for (int i : answer) {
            out.printLine(i);
        }
    }
}
