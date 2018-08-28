package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.fill;
import static net.egork.numbers.IntegerUtils.gcd;

public class DBST {
    boolean[][] ok;
    int[][] right;
    int[][] left;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        ok = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ok[i][j] = gcd(a[i], a[j]) != 1;
            }
        }
        left = new int[n][n];
        fill(left, -1);
        right = new int[n][n];
        fill(right, -1);
        for (int i = 0; i < n; i++) {
            if (goLeft(0, i - 1) && goRight(i + 1, n - 1)) {
                out.printLine("Yes");
                return;
            }
        }
        out.printLine("No");
    }

    private boolean goLeft(int from, int to) {
        if (to < from) {
            return true;
        }
        if (left[from][to] != -1) {
            return left[from][to] == 1;
        }
        for (int i = from; i <= to; i++) {
            if (ok[i][to + 1] && goLeft(from, i - 1) && goRight(i + 1, to)) {
                left[from][to] = 1;
                return true;
            }
        }
        left[from][to] = 0;
        return false;
    }

    private boolean goRight(int from, int to) {
        if (to < from) {
            return true;
        }
        if (right[from][to] != -1) {
            return right[from][to] == 1;
        }
        for (int i = from; i <= to; i++) {
            if (ok[i][from - 1] && goLeft(from, i - 1) && goRight(i + 1, to)) {
                right[from][to] = 1;
                return true;
            }
        }
        right[from][to] = 0;
        return false;
    }
}
