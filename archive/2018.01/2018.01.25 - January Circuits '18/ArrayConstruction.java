package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.bitCount;
import static java.lang.System.currentTimeMillis;

public class ArrayConstruction {
    int[][] c;
    int n;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long time = currentTimeMillis();
        n = in.readInt();
        int m = in.readInt();
        c = in.readIntTable(n, n);
        int[] a = new int[n];
        if (n == 31) {
            solve32(a, m % n);
        } else {
            for (int i = 0; i < m % n; i++) {
//            a[i] = m / n + (i < m % n ? 1 : 0);
                a[i] = 1;
            }
            long score = getScore(a);
            while (currentTimeMillis() - time <= 1900) {
                boolean updated = false;
                for (int i = 0; i < n; i++) {
                    if (a[i] == 0) {
                        continue;
                    }
                    for (int j = 0; j < n; j++) {
                        if (a[j] == 1) {
                            continue;
                        }
                        a[i] = 0;
                        a[j] = 1;
                        long candidate = score + getDelta(a, i, j);
                        if (candidate < score) {
                            score = candidate;
                            updated = true;
                            break;
                        } else {
                            a[i] = 1;
                            a[j] = 0;
                        }
                    }
                }
                if (!updated) {
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            a[i] += m / n;
        }
        out.printLine(a);
    }

    private void solve32(int[] a, int m) {
        boolean invert = false;
        if (m > 16) {
            m = 32 - m;
            invert = true;
        }

        int[][] costZero = new int[16][1 << 16];
        int[][] costOne = new int[16][1 << 16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < (1 << 16); j++) {
                for (int k = 0; k < 16; k++) {
                    if ((j >> k & 1) == 0) {
                        costOne[i][j] += c[i][k + 16] + c[k + 16][i];
                    } else {
                        costZero[i][j] += c[i][k + 16] + c[k + 16][i];
                    }
                }
            }
        }

        int[][][] cost = new int[4][16][1 << 16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < (1 << 16); k++) {
                    for (int l = 0; l < 4; l++) {
                        if ((j >> l & 1) == 0) {
                            cost[i][j][k] += costZero[l + 4 * i][k];
                        } else {
                            cost[i][j][k] += costOne[l + 4 * i][k];
                        }
                    }
                }
            }
        }
        int[] internalCostLeft = new int[1 << 16];
        for (int i = 0; i < (1 << 16); i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    if ((i >> j & 1) != (i >> k & 1)) {
                        internalCostLeft[i] += c[j][k];
                    }
                }
            }
        }
        int[] internalCostRight = new int[1 << 16];
        for (int i = 0; i < (1 << 16); i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    if ((i >> j & 1) != (i >> k & 1)) {
                        internalCostRight[i] += c[j + 16][k + 16];
                    }
                }
            }
        }
        int[][] masks = new int[m + 1][];
        int[] at = new int[m + 1];
        int[][] c = new int[33][33];
        for (int i = 0; i <= 16; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        for (int i = 0; i <= m; i++) {
            masks[i] = new int[c[16][i]];
        }
        for (int i = 0; i < (1 << 16); i++) {
            int bits = bitCount(i);
            if (bits <= m) {
                masks[bits][at[bits]++] = i;
            }
        }

        int answer = MAX_VALUE;
        int p1 = -1;
        int p2 = -1;
        for (int i = 0; i <= m; i++) {
            for (int j : masks[i]) {
                for (int k : masks[m - i]) {
                    int cst = internalCostLeft[j] + internalCostRight[k];
                    for (int l = 0; l < 4; l++) {
                        cst += cost[l][j >> (4 * l) & 15][k];
                    }
                    if (cst < answer) {
                        answer = cst;
                        p1 = j;
                        p2 = k;
                    }
                }
            }
        }

        for (int i = 0; i < 16; i++) {
            a[i] = p1 >> i & 1;
            a[i + 16] = p2 >> i & 1;
        }

        if (invert) {
            for (int i = 0; i < n; i++) {
                a[i] = 1 - a[i];
            }
        }
    }

    private long getDelta(int[] a, int i, int j) {
        long score = 0;
        for (int k = 0; k < n; k++) {
            if (k == i || k == j) {
                continue;
            }
            if (a[k] == 1) {
                score += c[i][k] + c[k][i] - c[j][k] - c[k][j];
            } else {
                score += c[j][k] + c[k][j] - c[i][k] - c[k][i];
            }
        }
        return score;
    }

    private long getScore(int[] a) {
        long score = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                score += ((long) a[i] - a[j]) * (a[i] - a[j]) * c[i][j];
            }
        }
        return score;
    }
}
