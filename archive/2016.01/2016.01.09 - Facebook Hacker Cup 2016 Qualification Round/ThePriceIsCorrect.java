package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class ThePriceIsCorrect {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int p;
            int[] b;
            long answer;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                p = in.readInt();
                b = IOUtils.readIntArray(in, n);
            }

            @Override
            public void solve() {
                int j = 0;
                long sum = 0;
                for (int i = 0; i < n; i++) {
                    while (j < n && sum <= p) {
                        sum += b[j++];
                    }
                    answer += j - i - 1;
                    if (sum <= p) {
                        answer++;
                    }
                    sum -= b[i];
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
