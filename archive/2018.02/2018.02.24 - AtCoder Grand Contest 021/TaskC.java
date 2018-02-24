package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.fill;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int a = in.readInt();
        int b = in.readInt();
        if (2 * (a + b) > n * m) {
            out.printLine("NO");
            return;
        }
        char[][] answer = new char[n][m];
        fill(answer, '.');
        if (n % 2 != 0) {
            for (int i = 0; i + 1 < m; i += 2) {
                if (a != 0) {
                    answer[n - 1][i] = '<';
                    answer[n - 1][i + 1] = '>';
                    a--;
                }
            }
        }
        if (m % 2 != 0) {
            for (int i = n % 2; i < n; i += 2) {
                if (b != 0) {
                    answer[i][m - 1] = '^';
                    answer[i + 1][m - 1] = 'v';
                    b--;
                }
            }
        }
        for (int i = n - n % 2 - 2; i >= 0; i -= 2) {
            for (int j = 0; j + 1 < m; j += 2) {
                if (a >= 2) {
                    answer[i][j] = '<';
                    answer[i][j + 1] = '>';
                    answer[i + 1][j] = '<';
                    answer[i + 1][j + 1] = '>';
                    a -= 2;
                } else if (b >= 2) {
                    answer[i][j] = '^';
                    answer[i][j + 1] = '^';
                    answer[i + 1][j] = 'v';
                    answer[i + 1][j + 1] = 'v';
                    b -= 2;
                } else if (a == 1 && b == 1 && i == 0 && j == m - 3 && n % 2 == 1) {
                    answer[i][j] = '^';
                    answer[i + 1][j] = 'v';
                    answer[i][j + 1] = '<';
                    answer[i][j + 2] = '>';
                    a--;
                    b--;
                } else if (a == 1) {
                    answer[i][j] = '<';
                    answer[i][j + 1] = '>';
                    a--;
                } else if (b == 1) {
                    answer[i][j] = '^';
                    answer[i + 1][j] = 'v';
                    b--;
                }
            }
        }
        if (a != 0 || b != 0) {
            out.printLine("NO");
            return;
        }
        out.printLine("YES");
        for (char[] row : answer) {
            out.printLine(row);
        }
    }
}
