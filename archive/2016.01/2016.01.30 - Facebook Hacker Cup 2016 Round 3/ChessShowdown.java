package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class ChessShowdown {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            double ww;
            double wb;
            double lw;
            double lb;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                ww = in.readDouble();
                wb = in.readDouble();
                lw = in.readDouble();
                lb = in.readDouble();
            }

            double answer;

            @Override
            public void solve() {
                if (n == 1) {
                    answer = ww;
                    return;
                }
                double[][] mat = new double[2][2];
                mat[0][0] = Math.max(lw, lb);
                mat[0][1] = 1 - mat[0][0];
                mat[1][0] = Math.min(lw, lb);
                mat[1][1] = 1 - mat[1][0];
                double[][] temp = new double[2][2];
                double[][] ans = new double[2][2];
                calculate(mat, n - 2, ans, temp);
                double win = lw * ans[0][0] + (1 - lw) * ans[1][0];
                double lose = 1 - win;
                answer = Math.max(ww, wb) * win + Math.min(ww, wb) * lose;
            }

            private void calculate(double[][] mat, int power, double[][] answer, double[][] temp) {
                if (power == 0) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            answer[i][j] = i == j ? 1 : 0;
                        }
                    }
                    return;
                }
                if ((power & 1) == 1) {
                    calculate(mat, power - 1, temp, answer);
                    multiply(answer, temp, mat);
                } else {
                    calculate(mat, power >> 1, temp, answer);
                    multiply(answer, temp, temp);
                }
            }

            private void multiply(double[][] a, double[][] b, double[][] c) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        a[i][j] = 0;
                        for (int k = 0; k < 2; k++) {
                            a[i][j] += b[i][k] * c[k][j];
                        }
                    }
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", String.format("%.10f", answer));
            }
        }, 4);
    }
}
