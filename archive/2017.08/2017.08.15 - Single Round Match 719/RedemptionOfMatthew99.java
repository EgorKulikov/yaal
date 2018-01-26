package net.egork;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Long.signum;
import static java.lang.Math.hypot;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.createOrder;

public class RedemptionOfMatthew99 {
    public double bestTraversal(int[] X1, int[] Y1, int[] X2, int[] Y2) {
        int n = X1.length;
        double answer = POSITIVE_INFINITY;
        for (int t = 0; t < 2; t++) {
            int[] left = new int[n];
            int[] right = new int[n];
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    left[i] = left[i - 1];
                    right[i] = right[i - 1];
                }
                while (left[i] < n && intersect(X1[i], Y1[i], X2[left[i]], Y2[left[i]], X1, Y1, X2, Y2)) {
                    left[i]++;
                }
                right[i] = Math.max(right[i], left[i]);
                while (right[i] < n && !intersect(X1[i], Y1[i], X2[right[i]], Y2[right[i]], X1, Y1, X2, Y2)) {
                    right[i]++;
                }
            }
            double[][] distance = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = left[i]; j < right[i]; j++) {
                    distance[i][j] = hypot(Y1[i] - Y2[j], X1[i] - X2[j]);
                }
            }
            int[] o1 = createOrder(n);
            int[] o2 = createOrder(n);
            boolean good = true;
            double current = 0;
            for (int i = 0; i < n; i++) {
                if (left[i] > i || right[i] <= i) {
                    good = false;
                    break;
                }
                current += distance[i][i];
            }
            for (int i = 1; i < n; i++) {
                if (left[i] > i - 1 || right[i] <= i - 1) {
                    good = false;
                    break;
                }
                current += distance[i][i - 1];
            }
            if (good) {
                while (true) {
                    boolean updated = false;
                    for (int i = 1; i < n; i++) {
                        if ((i == 1 || left[o1[i]] <= o2[i - 2] && right[o1[i]] > o2[i - 2]) && left[o1[i - 1]] <=
                                o2[i] && right[o2[i - 1]] > o2[i])
                        {
                            double delta = distance[o1[i - 1]][o2[i]] - distance[o1[i]][o2[i]];
                            if (i != 1) {
                                delta += distance[o1[i]][o2[i - 2]] - distance[o1[i - 1]][o2[i - 2]];
                            }
                            if (delta < 0) {
                                current += delta;
                                updated = true;
                                int temp = o1[i];
                                o1[i] = o1[i - 1];
                                o1[i - 1] = temp;
                            }
                        }
                        if ((i == n - 1 || left[o1[i + 1]] <= o2[i - 1] && right[o1[i + 1]] > o2[i - 1]) && left[o1[i -
                                1]] <= o2[i] && right[o1[i - 1]] > o2[i])
                        {
                            double delta = distance[o1[i - 1]][o2[i]] - distance[o1[i - 1]][o2[i - 1]];
                            if (i != n - 1) {
                                delta += distance[o1[i + 1]][o2[i - 1]] - distance[o1[i + 1]][o2[i]];
                            }
                            if (delta < 0) {
                                current += delta;
                                updated = true;
                                int temp = o2[i];
                                o2[i] = o2[i - 1];
                                o2[i - 1] = temp;
                            }
                        }
                    }
                    if (!updated) {
                        break;
                    }
                }
                answer = Math.min(answer, current);
            }
            int[] temp = X1;
            X1 = X2;
            X2 = temp;
            temp = Y1;
            Y1 = Y2;
            Y2 = temp;
        }
        if (answer == POSITIVE_INFINITY) {
            answer = -1;
        }
        return answer;
    }

    private boolean intersect(int x1, int y1, int x2, int y2, int[] x0, int[] y0, int[] x, int[] y) {
        for (int i = 0; i < x.length; i++) {
            if (contain(x1, y1, x2, y2, x[i], y[i])) {
                return true;
            }
            if (contain(x1, y1, x2, y2, x0[i], y0[i])) {
                return true;
            }
        }
        for (int i = 1; i < x.length; i++) {
            if (intersect(x1, y1, x2, y2, x[i - 1], y[i - 1], x[i], y[i])) {
                return true;
            }
            if (intersect(x1, y1, x2, y2, x0[i - 1], y0[i - 1], x0[i], y0[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean intersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        if (x1 == x3 && y1 == y3 || x1 == x4 && y1 == y4 || x2 == x3 && y2 == y3 || x2 == x4 && y2 == y4) {
            return false;
        }
        long a = y1 - y2;
        long b = x2 - x1;
        long c = -a * x1 - b * y1;
        long a0 = y3 - y4;
        long b0 = x4 - x3;
        long c0 = -a0 * x3 - b0 * y3;
        return getSign(x1, y1, a0, b0, c0) == -getSign(x2, y2, a0, b0, c0) && getSign(x3, y3, a, b, c) == -getSign
                (x4, y4, a, b, c);
    }

    private boolean contain(int x1, int y1, int x2, int y2, int x, int y) {
        if (x == x1 && y == y1 || x == x2 && y == y2) {
            return false;
        }
        long a = y1 - y2;
        long b = x2 - x1;
        long c = -a * x1 - b * y1;
        return getSign(x, y, a, b, c) == 0 && x >= min(x1, x2) && x <= max(x1, x2) && y >= min(y1, y2) && y <= max
                (y1, y2);
    }

    private int getSign(long x, long y, long a, long b, long c) {
        return signum(x * a + y * b + c);
    }
}
