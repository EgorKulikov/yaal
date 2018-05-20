package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.fill;

public class FallingBalls {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int c = in.readInt();
        int[] b = in.readIntArray(c);
        if (b[0] == 0 || b[c - 1] == 0) {
            out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
            return;
        }
        int[] to = new int[c];
        int at = 0;
        for (int i = 0; i < c; i++) {
            while (b[at] == 0) {
                at++;
            }
            to[i] = at;
            b[at]--;
        }
        int height = 1;
        for (int i = 0; i < c; i++) {
            height = Math.max(height, abs(i - to[i]) + 1);
        }
        char[][] answer = new char[height][c];
        fill(answer, '.');
        for (int i = 0; i < c; i++) {
            int row = 0;
            int col = i;
            while (col != to[i]) {
                if (col > to[i]) {
                    answer[row][col] = '/';
                    col--;
                } else {
                    answer[row][col] = '\\';
                    col++;
                }
                row++;
            }
        }
        out.printLine("Case #" + testNumber + ":", answer.length);
        for (char[] row : answer) {
            out.printLine(row);
        }
    }
}
