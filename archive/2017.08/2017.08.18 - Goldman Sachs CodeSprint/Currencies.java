package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Math.log;
import static java.lang.System.arraycopy;
import static net.egork.io.IOUtils.readIntTable;
import static net.egork.misc.MiscUtils.MOD7;

public class Currencies {
    int[][] a;

    static class Matrix {
        double[][] d;
        long[][] l;

        Matrix(int n) {
            d = new double[n][n];
            l = new long[n][n];
        }
    }

    void multiply(Matrix c, Matrix a, Matrix b) {
        for (int i = 0; i < c.d.length; i++) {
            for (int j = 0; j < c.d.length; j++) {
                int at = -1;
                double max = -1;
                for (int k = 0; k < c.d.length; k++) {
                    double current = a.d[i][k] + b.d[k][j];
                    if (current > max) {
                        max = current;
                        at = k;
                    }
                }
                c.d[i][j] = a.d[i][at] + b.d[at][j];
                c.l[i][j] = a.l[i][at] * b.l[at][j] % MOD7;
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int x = in.readInt();
        int s = in.readInt();
        int f = in.readInt();
        int m = in.readInt();
        int[][] a = readIntTable(in, n, n);
        Matrix matrix = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix.l[i][j] = a[i][j];
                if (i == j) {
                    matrix.d[i][j] = NEGATIVE_INFINITY;
                } else {
                    matrix.d[i][j] = log(a[i][j]);
                }
            }
        }
        Matrix result = new Matrix(n);
        Matrix temp = new Matrix(n);
        power(m, result, temp, matrix);
        out.printLine(x * result.l[s][f] % MOD7);
    }

    private void power(int n, Matrix result, Matrix temp, Matrix matrix) {
        if (n == 1) {
            for (int i = 0; i < result.d.length; i++) {
                arraycopy(matrix.d[i], 0, result.d[i], 0, result.d.length);
                arraycopy(matrix.l[i], 0, result.l[i], 0, result.l.length);
            }
            return;
        }
        if ((n & 1) == 0) {
            power(n >> 1, temp, result, matrix);
            multiply(result, temp, temp);
        } else {
            power(n - 1, temp, result, matrix);
            multiply(result, temp, matrix);
        }
    }
}
