package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class Yachtzee {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int a;
            int b;
            int[] c;
            double answer;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                a = in.readInt();
                b = in.readInt();
                c = IOUtils.readIntArray(in, n);
            }

            @Override
            public void solve() {
                long s = ArrayUtils.sumArray(c);
                long t = a / s;
                a -= t * s;
                b -= t * s;
                if (b <= s) {
                    answer = calculate(a, b);
                } else {
                    answer = calculate(a, s) + calculate(0, b % s) + (b / s - 1) * calculate(0, s);
                }
                answer /= b - a;
            }

            private double calculate(long from, long to) {
                double answer = 0;
                for (int i : c) {
                    if (from >= i) {
                        from -= i;
                        to -= i;
                        continue;
                    }
                    if (to >= i) {
                        answer += (from + i) * (i - from) / 2d;
                        from = 0;
                        to -= i;
                    } else {
                        answer += to * to / 2d - from * from / 2d;
                        break;
                    }
                }
                return answer;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
