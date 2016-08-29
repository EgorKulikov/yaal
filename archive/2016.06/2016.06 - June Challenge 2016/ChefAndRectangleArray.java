package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntTable;

public class ChefAndRectangleArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[][] mat = readIntTable(in, n, m);
        int[] queue = new int[max(n, m)];
        int[][] temp = new int[n][m];
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] = mat[i][j] + sum[i][j + 1] + sum[i + 1][j] - sum[i][j];
            }
        }
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int a = in.readInt();
            int b = in.readInt();
            for (int j = 0; j < n; j++) {
                int size = 0;
                int head = 0;
                for (int k = 0; k < b - 1; k++) {
                    while (size > 0 && mat[j][queue[size - 1]] <= mat[j][k]) {
                        size--;
                    }
                    queue[size++] = k;
                }
                for (int k = b - 1; k < m; k++) {
                    while (size > head && mat[j][queue[size - 1]] <= mat[j][k]) {
                        size--;
                    }
                    queue[size++] = k;
                    temp[j][k] = mat[j][queue[head]];
                    if (queue[head] == k - b + 1) {
                        head++;
                    }
                }
            }
            int answer = MAX_VALUE;
            for (int j = b - 1; j < m; j++) {
                int size = 0;
                int head = 0;
                for (int k = 0; k < a - 1; k++) {
                    while (size > 0 && temp[queue[size - 1]][j] <= temp[k][j]) {
                        size--;
                    }
                    queue[size++] = k;
                }
                for (int k = a - 1; k < n; k++) {
                    while (size > head && temp[queue[size - 1]][j] <= temp[k][j]) {
                        size--;
                    }
                    queue[size++] = k;
                    answer = Math.min(answer, a * b * temp[queue[head]][j] -
                            (sum[k + 1][j + 1] - sum[k + 1 - a][j + 1] - sum[k + 1][j + 1 - b] + sum[k + 1 - a][j +
                                    1 - b]));
                    if (queue[head] == k - a + 1) {
                        head++;
                    }
                }
            }
            out.printLine(answer);
        }
    }
}
