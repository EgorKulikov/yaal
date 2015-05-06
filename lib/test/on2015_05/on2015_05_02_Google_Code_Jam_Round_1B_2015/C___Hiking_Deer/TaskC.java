package on2015_05.on2015_05_02_Google_Code_Jam_Round_1B_2015.C___Hiking_Deer;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import java.util.Arrays;
import java.util.Set;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            int[] at;
            int[] size;
            int[] start;

            long answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                at = new int[count];
                size = new int[count];
                start = new int[count];
                IOUtils.readIntArrays(in, at, size, start);
            }

            @Override
            public void solve() {
                answer = ArrayUtils.sumArray(size);
                Set<Long> finishTimes = new EHashSet<>();
                for (int i = 0; i < count; i++) {
                    for (int j = start[i]; j < start[i] + size[i]; j++) {
                        long weFinish = (360L - at[i]) * j;
                        finishTimes.add(weFinish);
                    }
                }
                long[] times = new long[finishTimes.size()];
                int ind = 0;
                for (long i : finishTimes) {
                    times[ind++] = i;
                }
                Arrays.sort(times);
                SumIntervalTree tree = new SumIntervalTree(times.length);
                for (int i = 0; i < count; i++) {
                    for (int j = start[i]; j < start[i] + size[i]; j++) {
                        long weFinish = (360L - at[i]) * j;
                        for (long current = weFinish + 360L * j; ; ) {
                            int at = Arrays.binarySearch(times, current);
                            if (at < 0) {
                                at = -at - 1;
                            }
                            if (at >= times.length) {
                                break;
                            }
                            if (tree.query(at, at) >= answer) {
                                break;
                            }
                            long here = (times[at] - current) / (360L * j) + 1;
                            tree.update(at, times.length - 1, here);
                            current += 360L * j * here;
                        }
                    }
                }
                for (int i = 0; i < count; i++) {
                    for (int j = start[i]; j < start[i] + size[i]; j++) {
                        long weFinish = (360L - at[i]) * j;
                        int at = Arrays.binarySearch(times, weFinish);
                        tree.update(0, at - 1, 1);
                    }
                }
                for (int i = 0; i < times.length; i++) {
                    answer = Math.min(answer, tree.query(i, i));
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
