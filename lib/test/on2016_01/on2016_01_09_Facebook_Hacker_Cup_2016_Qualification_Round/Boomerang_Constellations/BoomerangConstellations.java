package on2016_01.on2016_01_09_Facebook_Hacker_Cup_2016_Qualification_Round.Boomerang_Constellations;



import net.egork.collections.map.Counter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BoomerangConstellations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int[] x;
            int[] y;
            int answer = 0;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                x = new int[n];
                y = new int[n];
                IOUtils.readIntArrays(in, x, y);
            }

            @Override
            public void solve() {
                for (int i = 0; i < n; i++) {
                    Counter<Integer> counter = new Counter<>();
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            int dx = x[i] - x[j];
                            int dy = y[i] - y[j];
                            counter.add(dx * dx + dy * dy);
                        }
                    }
                    for (long j : counter.values()) {
                        answer += j * (j - 1);
                    }
                }
                answer /= 2;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
