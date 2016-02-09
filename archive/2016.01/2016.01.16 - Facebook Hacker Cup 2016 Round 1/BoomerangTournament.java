package net.egork;

import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BoomerangTournament {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int[][] w;
            int[] min;
            int[] max;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                w = IOUtils.readIntTable(in, n, n);
            }

            @Override
            public void solve() {
                boolean[][] can = new boolean[n][1 << n];
                for (int i = 1; i < (1 << n); i++) {
                    int size = Integer.bitCount(i);
                    if (size == 1) {
                        can[Integer.numberOfTrailingZeros(i)][i] = true;
                    } else if (Integer.bitCount(size) != 1) {
                        continue;
                    } else {
                        for (int j = 0; j < n; j++) {
                            for (int k = 0; k < n; k++) {
                                if (w[j][k] == 0) {
                                    continue;
                                }
                                int z = i;
                                do {
                                    if (Integer.bitCount(z) == (size >> 1) && can[j][z] && can[k][i - z]) {
                                        can[j][i] = true;
                                        break;
                                    }
                                    z = (z - 1) & i;
                                } while (i != z);
                                if (can[j][i]) {
                                    break;
                                }
                            }
                        }
                    }
                }
                min = new int[n];
                max = new int[n];
                int[] place = new int[n + 1];
                place[n] = 1;
                place[n / 2] = 2;
                for (int i = n / 4; i != 0; i /= 2) {
                    place[i] = place[i * 2] * 2 - 1;
                }
                for (int i = 0; i < n; i++) {
                    if (ArrayUtils.count(w[i], 0) > 1) {
                        max[i] = n / 2 + 1;
                    } else {
                        max[i] = 1;
                    }
                    min[i] = n + 1;
                    for (int j = 0; j < (1 << n); j++) {
                        if (can[i][j]) {
                            min[i] = Math.min(min[i], place[Integer.bitCount(j)]);
                        }
                    }
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                for (int i = 0; i < n; i++) {
                    out.printLine(min[i], max[i]);
                }
            }
        }, 4);
    }
}
