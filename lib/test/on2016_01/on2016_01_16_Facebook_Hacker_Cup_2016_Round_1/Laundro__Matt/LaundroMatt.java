package on2016_01.on2016_01_16_Facebook_Hacker_Cup_2016_Round_1.Laundro__Matt;



import net.egork.collections.intcollection.Heap;
import net.egork.collections.intcollection.Range;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LaundroMatt {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int l;
            int n;
            int m;
            int d;
            int[] w;
            long answer;

            @Override
            public void read(InputReader in) {
                l = in.readInt();
                n = in.readInt();
                m = in.readInt();
                d = in.readInt();
                w = IOUtils.readIntArray(in, n);
            }

            @Override
            public void solve() {
                long[] next = ArrayUtils.asLong(w);
                long[] order = new long[l];
                Heap heap = new Heap((x, y) -> Long.compare(next[x], next[y]), n);
                heap.addAll(Range.range(n));
                for (int i = 0; i < l; i++) {
                    int current = heap.poll();
                    order[i] = next[current];
                    next[current] += w[current];
                    heap.add(current);
                }
                long[] dNext = new long[Math.min(l, m)];
                heap = new Heap((x, y) -> Long.compare(dNext[x], dNext[y]), dNext.length);
                heap.addAll(Range.range(dNext.length));
                for (int i = 0; i < l; i++) {
                    int current = heap.poll();
                    answer = Math.max(dNext[current], order[i]) + d;
                    dNext[current] = answer;
                    heap.add(current);
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
