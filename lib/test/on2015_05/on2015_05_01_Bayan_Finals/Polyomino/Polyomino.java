package on2015_05.on2015_05_01_Bayan_Finals.Polyomino;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class Polyomino {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int length;
            char[] sequence;
            int answer;

            @Override
            public void read(InputReader in) {
                length = in.readInt();
                sequence = IOUtils.readCharArray(in, length);
            }

            @Override
            public void solve() {
                if (length <= 1) {
                    answer = 1;
                    return;
                }
                int as = ArrayUtils.count(sequence, 'A');
                if (as == 0 || as == length) {
                    answer = 2;
                    return;
                }
                boolean alternate = true;
                for (int i = 1; i < length; i++) {
                    if (sequence[i] == sequence[i - 1]) {
                        alternate = false;
                    }
                }
                if (alternate) {
                    answer = 2;
                    return;
                }
                for (int i = 0; i < 2; i++) {
                    for (char c = 'A'; c <= 'B'; c++) {
                        boolean good = true;
                        for (int j = i; j < length; j += 2) {
                            if (sequence[j] != c) {
                                good = false;
                                break;
                            }
                        }
                        if (good) {
                            answer = 3;
                            return;
                        }
                    }
                }
                answer = 4;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                out.printLine(answer);
            }
        }, 4);
    }
}
