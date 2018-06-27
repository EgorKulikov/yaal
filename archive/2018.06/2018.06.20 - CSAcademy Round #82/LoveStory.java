package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class LoveStory {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] lengths = in.readIntArray(m);
        int has = n;
        for (int i = 0; i < m; i++) {
            if (has < 2 * lengths[i]) {
                out.printLine(i + 1);
                return;
            }
            has -= 2 * lengths[i];
            if (has == 0) {
                out.printLine(-1);
                return;
            }
        }
    }
}
