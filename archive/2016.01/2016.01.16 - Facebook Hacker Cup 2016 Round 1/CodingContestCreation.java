package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class CodingContestCreation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int[] d;
            int answer;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                d = IOUtils.readIntArray(in, n);
            }

            @Override
            public void solve() {
                int[] result = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    result[i] = result[i - 1] + 3;
                    int penalty = 0;
                    for (int j = 2; j <= 4 && j <= i; j++) {
                        if (d[i - j] >= d[i - j + 1]) {
                            break;
                        }
                        penalty += (d[i - j + 1] - d[i - j] - 1) / 10;
                        if (penalty > 4 - j) {
                            break;
                        }
                        result[i] = Math.min(result[i], result[i - j] + 4 - j);
                    }
                }
                answer = result[n];
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
