package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class Interception {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int[] p;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                p = in.readIntArray(n + 1);
            }

            boolean isZero;

            @Override
            public void solve() {
                isZero = n % 2 == 1;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", isZero ? 1 : 0);
                if (isZero) {
                    out.printLine("0.0");
                }
            }
        }, 4);
    }
}
