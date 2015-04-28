package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import sun.nio.ch.IOUtil;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            int[] qty;
            int answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                qty = IOUtils.readIntArray(in, count);
            }

            @Override
            public void solve() {
                answer = ArrayUtils.maxElement(qty);
                for (int i = answer - 1; i > 0; i--) {
                    int current = i;
                    for (int j : qty) {
                        current += (j - 1) / i;
                    }
                    answer = Math.min(answer, current);
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
