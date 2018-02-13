package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int base = 1;
        while (true) {
            for (int i = 0; i < (n - base) / 2; i++) {
                out.print('.');
            }
            for (int i = 0; i < base; i++) {
                out.print('S');
            }
            out.printLine();
            if (base == n) {
                break;
            }
            base += 2;
        }
    }
}
