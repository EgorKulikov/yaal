package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.max;

public class GoGopher {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int length = max(3, (a + 2) / 3);
        int at = 2;
        boolean[][] done = new boolean[length][3];
        while (true) {
            out.printLine(at, 2);
            out.flush();
            int row = in.readInt() - 1;
            int column = in.readInt() - 1;
            if (row == -1) {
                return;
            }
            done[row][column] = true;
            if (done[at - 2][0] && done[at - 2][1] && done[at - 2][2] && at < length - 1) {
                at++;
            }
        }
    }
}
