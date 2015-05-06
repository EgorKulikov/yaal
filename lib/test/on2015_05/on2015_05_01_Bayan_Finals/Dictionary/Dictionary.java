package on2015_05.on2015_05_01_Bayan_Finals.Dictionary;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class Dictionary {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            int size;
            int[] words;

            int min;
            int max;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                size = in.readInt();
                words = IOUtils.readIntArray(in, count);
            }

            @Override
            public void solve() {
                int[] qty = new int[ArrayUtils.maxElement(words) + 1];
                for (int i : words) {
                    qty[i]++;
                }
                min = 0;
                int current = 0;
                for (int i = qty.length - 1; i > 0; i--) {
                    current = Math.max(current, qty[i]);
                    min += current;
                    current = (current + size - 1) / size;
                }
                max = 0;
                int total = 1;
                int remaining = count;
                for (int i = 1; i < qty.length; i++) {
                    total *= size;
                    total = Math.min(total, remaining);
                    max += total;
                    remaining -= qty[i];
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                out.printLine(min, max);
            }
        }, 4);
    }
}
