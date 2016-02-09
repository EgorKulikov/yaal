package net.egork;

import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HighSecurity {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            char[][] map;
            int answer;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                map = IOUtils.readTable(in, 2, n);
            }

            @Override
            public void solve() {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (map[j][i] != '.') {
                            continue;
                        }
                        if ((i == 0 || map[j][i - 1] == 'X') && (i == n - 1 || map[j][i + 1] == 'X')) {
                            answer++;
                            if (map[1 - j][i] == '.') {
                                map[1 - j][i] = 'G';
                            } else {
                                map[j][i] = 'G';
                            }
                        }
                    }
                }
                for (int i = 0; i < 2; i++) {
                    int length = 0;
                    boolean guard = false;
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] == 'X') {
                            if (length > 1 && !guard) {
                                answer++;
                            }
                            length = 0;
                            guard = false;
                        } else {
                            length++;
                            if (map[i][j] == 'G') {
                                guard = true;
                            }
                        }
                    }
                    if (length > 1 && !guard) {
                        answer++;
                    }
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
