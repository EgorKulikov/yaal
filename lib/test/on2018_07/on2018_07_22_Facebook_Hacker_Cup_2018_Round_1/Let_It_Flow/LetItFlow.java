package on2018_07.on2018_07_22_Facebook_Hacker_Cup_2018_Round_1.Let_It_Flow;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.power;

public class LetItFlow {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            char[][] map;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                map = in.readTable(3, n);
            }

            long answer;

            @Override
            public void solve() {
                answer = 0;
                if (n % 2 != 0) {
                    return;
                }
                if (map[0][0] == '#' || map[2][n - 1] == '#') {
                    return;
                }
                for (int i = 0; i < n; i++) {
                    if (map[1][i] == '#') {
                        return;
                    }
                }
                int variants = 0;
                for (int i = 1; i < n - 1; i += 2) {
                    int ways = 0;
                    for (int j = 0; j < 3; j += 2) {
                        if (map[j][i] == '.' && map[j][i + 1] == '.') {
                            ways++;
                        }
                    }
                    if (ways == 0) {
                        return;
                    }
                    if (ways == 2) {
                        variants++;
                    }
                }
                answer = power(2, variants, MOD7);
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
