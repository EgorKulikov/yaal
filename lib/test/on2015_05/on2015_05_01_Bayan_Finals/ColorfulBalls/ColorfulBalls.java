package on2015_05.on2015_05_01_Bayan_Finals.ColorfulBalls;


import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ColorfulBalls {
    public static final long MOD = (long) (1e9 + 7);
    public static volatile long[] factorial = IntegerUtils.generateFactorial((int) (1e6 + 1), MOD);
    public static volatile long[] reverse = IntegerUtils.generateReverseFactorials((int) (1e6 + 1), MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int size;
            int colors;
            int[] numbers;

            long answer;
            long ways;

            @Override
            public void read(InputReader in) {
                size = in.readInt();
                colors = in.readInt();
                numbers = IOUtils.readIntArray(in, size);
            }

            @Override
            public void solve() {
                answer = 0;
                ways = 1;
                for (int i = 29; i >= 0; i--) {
                    if (colors == 1) {
                        int result = Integer.MAX_VALUE;
                        for (int j : numbers) {
                            result &= j;
                        }
                        answer += result;
                        return;
                    }
                    int good = 0;
                    for (int j : numbers) {
                        if (j >= (1 << i)) {
                            good++;
                        }
                    }
                    IntList next = new IntArrayList();
                    if (good < colors) {
                        for (int j = 0; j < good; j++) {
                            ways *= colors--;
                            ways %= MOD;
                        }
                        for (int j : numbers) {
                            if (j < (1 << i)) {
                                next.add(j);
                            } else {
                                answer += j;
                            }
                        }
                    } else {
                        answer += (1L << i) * (colors - 1);
                        int special = Integer.MAX_VALUE;
                        for (int j : numbers) {
                            if (j < (1 << i)) {
                                special &= j;
                            } else {
                                next.add(j - (1 << i));
                            }
                        }
                        if (special == Integer.MAX_VALUE) {
                            answer += 1 << i;
                        } else {
                            next.add(special);
                        }
                    }
                    numbers = next.toArray();
                }
                int remaining = numbers.length;
                long multiplier = 0;
                for (int i = colors; i > 0; i--) {
                    long current = IntegerUtils.power(i, remaining, MOD) * c(colors, i) % MOD;
                    if ((colors - i) % 2 == 0) {
                        multiplier += current;
                    } else {
                        multiplier -= current;
                    }
                }
                multiplier %= MOD;
                if (multiplier < 0) {
                    multiplier += MOD;
                }
                ways *= multiplier;
                ways %= MOD;
            }

            private long c(int n, int m) {
                return factorial[n] * reverse[m] % MOD * reverse[n - m] % MOD;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                out.printLine(answer, ways);
            }
        }, 4);
    }
}
