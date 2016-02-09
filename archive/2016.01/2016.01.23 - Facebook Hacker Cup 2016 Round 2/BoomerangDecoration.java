package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class BoomerangDecoration {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            char[] left;
            char[] right;
            int answer;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                left = IOUtils.readCharArray(in, n);
                right = IOUtils.readCharArray(in, n);
            }

            @Override
            public void solve() {
                int[] prefix = new int[n + 1];
                prefix[1] = 1;
                for (int i = 1; i < n; i++) {
                    prefix[i + 1] = prefix[i] + (right[i] != right[i - 1] ? 1 : 0);
                }
                int[] suffix = new int[n + 1];
                suffix[n - 1] = 1;
                for (int i = n - 2; i >= 0; i--) {
                    suffix[i] = suffix[i + 1] + (right[i] != right[i + 1] ? 1 : 0);
                }
                answer = suffix[0];
                int start = 0;
                for (int i = 0; i < n; i++) {
                    if (left[i] != right[i]) {
                        start = i + 1;
                    }
                    answer = Math.min(answer, Math.max(prefix[start], suffix[i + 1]));
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
