package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import java.util.Stack;

public class SnakesAndLadders {
    public static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int[] x;
            int[] h;
            long answer;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                x = new int[n];
                h = new int[n];
                IOUtils.readIntArrays(in, x, h);
            }

            @Override
            public void solve() {
                ArrayUtils.orderBy(x, h);
                Stack<Height> stack = new Stack<>();
                for (int i = 0; i < n; i++) {
                    while (!stack.isEmpty() && stack.peek().height < h[i]) {
                        answer += stack.pop().value();
                    }
                    if (stack.isEmpty() || stack.peek().height != h[i]) {
                        stack.add(new Height(h[i]));
                    }
                    stack.peek().add(x[i]);
                }
                for (Height h : stack) {
                    answer += h.value();
                }
                answer %= MOD;
            }

            class Height {
                long sumX;
                long sumX2;
                int qty;
                int height;

                public Height(int height) {
                    this.height = height;
                }

                void add(long x) {
                    sumX = (sumX + x) % MOD;
                    sumX2 = (sumX2 + x * x) % MOD;
                    qty++;
                }

                long value() {
                    long result = qty * sumX2 - sumX * sumX;
                    result %= MOD;
                    if (result < 0) {
                        result += MOD;
                    }
                    return result;
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
