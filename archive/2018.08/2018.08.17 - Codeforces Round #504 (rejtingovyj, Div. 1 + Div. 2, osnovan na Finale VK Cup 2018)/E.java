package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.string.StringUtils.reverse;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        StringBuilder start = new StringBuilder();
        int row = 1;
        int col = 1;
        while (row + col < n + 1) {
            out.printLine('?', row + 1, col, n, n);
            out.flush();
            if ("YES".equals(in.readString())) {
                row++;
                start.append('D');
            } else {
                col++;
                start.append('R');
            }
        }
        StringBuilder end = new StringBuilder();
        row = n;
        col = n;
        while (row + col > n + 1) {
            out.printLine('?', 1, 1, row, col - 1);
            out.flush();
            if ("YES".equals(in.readString())) {
                col--;
                end.append('R');
            } else {
                row--;
                end.append('D');
            }
        }
        String answer = start.toString() + reverse(end.toString());
        out.printLine('!', answer);
        out.flush();
    }
}
