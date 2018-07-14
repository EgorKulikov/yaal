package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class Tourist {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int k;
            long v;
            String[] a;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                k = in.readInt();
                v = in.readLong();
                a = in.readStringArray(n);
            }

            String[] answer;

            @Override
            public void solve() {
                int start = (int) ((v - 1) * k % n);
                int end = start + k;
                answer = new String[k];
                int at = 0;
                for (int i = 0; i < end - n; i++) {
                    answer[at++] = a[i];
                }
                for (int i = start; i < end && i < n; i++) {
                    answer[at++] = a[i];
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.print("Case #" + testNumber + ": ");
                out.printLine((Object[]) answer);
            }
        }, 4);
    }
}
