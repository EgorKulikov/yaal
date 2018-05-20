package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.createArray;

public class CLift {
    int[] a;
    int[] b;
    int[][][][][][] result;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        a = new int[n];
        b = new int[n];
        in.readIntArrays(a, b);
        result = new int[9][n + 1][10][][][];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < 10; k++) {
                    result[i][j][k] = new int[k + 1][][];
                    for (int l = 0; l <= k; l++) {
                        result[i][j][k][l] = new int[l + 1][];
                        for (int m = 0; m <= l; m++) {
                            result[i][j][k][l][m] = createArray(m + 1, -1);
                        }
                    }
                }
            }
        }
        out.printLine(go(0, 0, 0, 0, 0, 0) + 2 * n);
    }

    private int go(int floor, int id, int e1, int e2, int e3, int e4) {
        if (result[floor][id][e1][e2][e3][e4] != -1) {
            return result[floor][id][e1][e2][e3][e4];
        }
        if (id == a.length && e1 == 0) {
            return result[floor][id][e1][e2][e3][e4] = 0;
        }
        result[floor][id][e1][e2][e3][e4] = MAX_VALUE;
        if (e1 != 0) {
            result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(e1 - 1, id, e2, e3,
                    e4, 0) + abs(floor + 1 - e1));
            if (e2 != 0) {
                result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(e2 - 1, id, e1, e3,
                        e4, 0) + abs(floor + 1 - e2));
                if (e3 != 0) {
                    result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(e3 - 1, id,
                            e1, e2, e4, 0) + abs(floor + 1 - e3));
                    if (e4 != 0) {
                        result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(e4 - 1, id,
                                e1, e2, e3, 0) + abs(floor + 1 - e4));
                    }
                }
            }
        }
        if (id != a.length && e4 == 0) {
            if (b[id] >= e1) {
                result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(a[id] - 1, id + 1,
                        b[id], e1, e2, e3) + abs(floor + 1 - a[id]));
            } else if (b[id] >= e2) {
                result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(a[id] - 1, id + 1,
                        e1, b[id], e2, e3) + abs(floor + 1 - a[id]));
            } else if (b[id] >= e3) {
                result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(a[id] - 1, id + 1,
                        e1, e2, b[id], e3) + abs(floor + 1 - a[id]));
            } else {
                result[floor][id][e1][e2][e3][e4] = Math.min(result[floor][id][e1][e2][e3][e4], go(a[id] - 1, id + 1,
                        e1, e2, e3, b[id]) + abs(floor + 1 - a[id]));
            }
        }
        return result[floor][id][e1][e2][e3][e4];
    }
}
