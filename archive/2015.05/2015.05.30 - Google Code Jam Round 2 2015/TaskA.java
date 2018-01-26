package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int rowCount;
            int columnCount;
            char[][] grid;
            int answer;

            @Override
            public void read(InputReader in) {
                rowCount = in.readInt();
                columnCount = in.readInt();
                grid = IOUtils.readTable(in, rowCount, columnCount);
            }

            @Override
            public void solve() {
                answer = 0;
                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < columnCount; j++) {
                        if (grid[i][j] == '.') {
                            continue;
                        }
                        int mask = 15;
                        for (int k = 0; k < i; k++) {
                            if (grid[k][j] != '.') {
                                mask &= 14;
                            }
                        }
                        for (int k = i + 1; k < rowCount; k++) {
                            if (grid[k][j] != '.') {
                                mask &= 13;
                            }
                        }
                        for (int k = 0; k < j; k++) {
                            if (grid[i][k] != '.') {
                                mask &= 11;
                            }
                        }
                        for (int k = j + 1; k < columnCount; k++) {
                            if (grid[i][k] != '.') {
                                mask &= 7;
                            }
                        }
                        if (mask == 15) {
                            answer = -1;
                            return;
                        }
                        if (grid[i][j] == '^') {
                            answer += mask & 1;
                        } else if (grid[i][j] == 'v') {
                            answer += (mask >> 1) & 1;
                        } else if (grid[i][j] == '<') {
                            answer += (mask >> 2) & 1;
                        } else {
                            answer += (mask >> 3) & 1;
                        }
                    }
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                if (answer >= 0) {
                    out.printLine("Case #" + testNumber + ":", answer);
                } else {
                    out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
                }
            }
        }, 4);
    }
}
