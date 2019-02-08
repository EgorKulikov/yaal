package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.fill;

public class DMOPC18Contest1P6Sorting {
    int[][][] answer;
    int[][] sum;
    char[][] a;
    int divider;
    int start;
    int end;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int b = in.readInt();
        a = in.readTable(n, b);
        sum = new int[b][n + 1];
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j + 1] = sum[i][j] + a[j][i] - '0';
            }
        }
        answer = new int[b][n][n];
        fill(answer, -1);
        for (int i = b - 1; i > 0; i--) {
            for (int j = 0; j < n; j++) {
                batch(i, j, j, n - 1, j - 1, n - 1);
            }
        }
        start = -1;
        end = n - 1;
        out.printLine(go(0, 0, n - 1));
    }

    private void batch(int column, int lf, int from, int to, int st, int en) {
        if (from > to) {
            return;
        }
        int middle = (from + to) >> 1;
        st = Math.max(st, lf - 1);
        en = min(en, to);
        start = st;
        end = min(en, middle);
        go(column, lf, middle);
        int div = divider;
        batch(column, lf, from, middle - 1, st, div);
        batch(column, lf, middle + 1, to, div, en);
    }

    private int go(int column, int r1, int r2) {
        if (column == answer.length) {
            return 0;
        }
        if (r1 > r2) {
            return 0;
        }
        if (answer[column][r1][r2] != -1) {
            return answer[column][r1][r2];
        }
/*        int left = r1 - 1;
        int right = r2;
        while (left < right) {
            int middle = (left + right) >> 1;
            int v1 = calculate(column, r1, middle, 0) + calculate(column, middle + 1, r2, 1);
            int v2 = calculate(column, r1, middle + 1, 0) + calculate(column, middle + 2, r2, 1);
            if (v1 <= v2) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return answer[column][r1][r2] = calculate(column, r1, left, 0) + calculate(column, left + 1, r2, 1);*/
        int result = MAX_VALUE;
        int delta = r2 - start - sum[column][r2 + 1] + sum[column][start + 1];
        delta += sum[column][start + 1] - sum[column][r1];
        for (int i = start; i <= end; i++) {
            int candidate = delta + go(column + 1, r1, i) + go(column + 1, i + 1, r2);
            if (result > candidate) {
                result = candidate;
                divider = i;
            }
            if (i != r2) {
                if (a[i + 1][column] == '0') {
                    delta--;
                } else {
                    delta++;
                }
            }
        }
        return answer[column][r1][r2] = result;
    }

    private int calculate(int column, int r1, int r2, int value) {
        int ones = sum[column][r2 + 1] - sum[column][r1];
        if (value == 1) {
            ones = r2 - r1 + 1 - ones;
        }
        return ones + go(column + 1, r1, r2);
    }
}
