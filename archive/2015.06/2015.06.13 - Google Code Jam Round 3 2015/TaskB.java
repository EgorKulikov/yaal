package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            int step;
            int[] sums;
            long answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                step = in.readInt();
                sums = IOUtils.readIntArray(in, count - step + 1);
            }

            @Override
            public void solve() {
                answer = 0;
                long[] start = new long[step];
                long[] delta = new long[step];
                for (int i = 0; i < step; i++) {
                    long current = 0;
                    long max = 0;
                    long min = 0;
                    for (int j = i; j + 1 < sums.length; j += step) {
                        current += sums[j + 1] - sums[j];
                        max = Math.max(max, current);
                        min = Math.min(min, current);
                    }
                    start[i] = -min;
                    delta[i] = max - min;
                }
                answer = ArrayUtils.maxElement(delta);
                long sum = -(sums[0] % step) + step;
                long freedom = 0;
                for (long i : start) {
                    sum += i;
                }
                for (long i : delta) {
                    freedom += answer - i;
                }
                if (sum % step != 0 && freedom + sum % step < step) {
                    answer++;
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
