package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sumArray;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int[] qty = in.readIntArray(7);
            boolean good = true;
            for (int j = 0; j < 6; j++) {
                if (qty[j] < qty[6]) {
                    good = false;
                    break;
                }
                qty[j] -= qty[6];
            }
            if (qty[0] < qty[3]) {
                good = false;
            }
            qty[0] -= qty[3];
            if (qty[0] < qty[5]) {
                good = false;
            }
            qty[0] -= qty[5];
            if (qty[1] < qty[3]) {
                good = false;
            }
            qty[1] -= qty[3];
            if (qty[1] < qty[4]) {
                good = false;
            }
            qty[1] -= qty[4];
            if (qty[2] < qty[4]) {
                good = false;
            }
            qty[2] -= qty[4];
            if (qty[2] < qty[5]) {
                good = false;
            }
            qty[2] -= qty[5];
            if (!good) {
                continue;
            }
            answer = (int) Math.max(answer, sumArray(qty));
        }
        out.printLine(answer);
    }
}
