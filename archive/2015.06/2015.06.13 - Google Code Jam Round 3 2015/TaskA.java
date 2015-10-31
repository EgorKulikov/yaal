package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            int maxDelta;
            int s0, as, cs, rs;
            int m0, am, cm, rm;
            int answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                maxDelta = in.readInt();
                s0 = in.readInt();
                as = in.readInt();
                cs = in.readInt();
                rs = in.readInt();
                m0 = in.readInt();
                am = in.readInt();
                cm = in.readInt();
                rm = in.readInt();
            }

            @Override
            public void solve() {
                int[] salary = generate(count, s0, as, cs, rs);
                int[] parent = generate(count, m0, am, cm, rm);
                for (int i = 1; i < count; i++) {
                    parent[i] %= i;
                }
                for (int i = count - 1; i >= 0; i--) {
                    salary[i] -= salary[0];
                }
                IntervalTree tree = new SumIntervalTree(maxDelta + 1);
                int[] from = new int[count];
                int[] to = new int[count];
                from[0] = 0;
                to[0] = maxDelta;
                tree.update(0, maxDelta, 1);
                for (int i = 1; i < count; i++) {
                    from[i] = Math.max(from[parent[i]], salary[i]);
                    to[i] = Math.min(to[parent[i]], salary[i] + maxDelta);
                    tree.update(from[i], to[i], 1);
                }
                answer = 0;
                for (int i = 0; i <= maxDelta; i++) {
                    answer = (int) Math.max(answer, tree.query(i, i));
                }
            }


            private int[] generate(int count, int s0, int as, int cs, int rs) {
                int[] result = new int[count];
                for (int i = 0; i < count; i++) {
                    result[i] = s0;
                    s0 = (s0 * as + cs) % rs;
                }
                return result;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
