package net.egork;



import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.power;

public class FoxPatrols {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int r;
            int c;
            int h;

            @Override
            public void read(InputReader in) {
                r = in.readInt();
                c = in.readInt();
                h = in.readInt();
            }

            long answer;

            @Override
            public void solve() {
                long lastVertical = 0;
                long lastHorizontal = 0;
                for (int i = 1; i <= h; i++) {
                    long vertical = power(i, r, MOD7);
                    long horizontal = power(i, c, MOD7);
                    answer += (vertical - lastVertical) * (horizontal - lastHorizontal) % MOD7;
                    lastVertical = vertical;
                    lastHorizontal = horizontal;
                }
                answer %= MOD7;
                answer += MOD7;
                answer %= MOD7;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
