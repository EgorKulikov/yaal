package net.egork;

import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class Literature {
    double n2;
    double[][][] result;

    public double expectation(int n, int[] Teja, int[] history) {
        n2 = 2 * n;
        result = new double[3][n + 1][n + 1];
        fill(result, -1);
        boolean[] known = new boolean[3 * n];
        decreaseByOne(Teja, history);
        for (int i : Teja) {
            known[i] = true;
        }
        int a = n;
        int b = n;
        for (int i = 0; i < history.length; i++) {
            if (i % 3 == 0) {
                continue;
            }
            if (!known[history[i]]) {
                if (i % 3 == 1) {
                    b--;
                } else {
                    a--;
                }
                known[history[i]] = true;
            }
            if (a == 0 || b == 0) {
                return i + 1;
            }
        }
        return go(history.length % 3, a, b) + history.length;
    }

    private double go(int step, int a, int b) {
        if (result[step][a][b] != -1) {
            return result[step][a][b];
        }
        if (a == 0 || b == 0) {
            return result[step][a][b] = 0;
        }
        if (step == 1) {
            return result[step][a][b] = 1 + go(2, a, b) * (n2 - b) / n2 + go(2, a, b - 1) * b / n2;
        }
        if (step == 2) {
            return result[step][a][b] = 1 + go(0, a, b) * (n2 - a) / n2 + go(0, a - 1, b) * a / n2;
        }
        return result[step][a][b] = (2 + (n2 - b) / n2 + (n2 - b) / n2 * a / n2 * go(0, a - 1, b) + b / n2 * go(2, a,
                b - 1)) / (1 - (n2 - b) / n2 * (n2 - a) / n2);
    }
}
