package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int my = in.readInt();
        int[] r = readIntArray(in, n - 1);
        int sum = my + r[n - 2];
        int answer = n / 2;
        int at = n - 3;
        for (int i = 0; i < at; i++) {
            while (at > i && r[i] + r[at] >= sum) {
                at--;
            }
            if (i >= at) {
                break;
            }
            answer--;
            at--;
        }
        out.printLine(answer);
    }
}
