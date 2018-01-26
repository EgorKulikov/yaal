package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class FoxHoles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int m;
            int[] v;
            int[] a;
            int[] b;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                m = in.readInt();
                v = readIntArray(in, 2 * n);
                a = new int[m];
                b = new int[m];
                readIntArrays(in, a, b);
            }

            @Override
            public void solve() {
                decreaseByOne(v, a, b);
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
            }
        }, 4);
    }
}
