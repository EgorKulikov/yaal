package net.egork;

import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CarnivalCoins {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int k;
            double p;
            double result;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                k = in.readInt();
                p = in.readDouble();
            }

            @Override
            public void solve() {
                double[] answer = new double[n + 1];
                double[] prob = new double[n + 1];
                prob[0] = 1;
                double[] next = new double[n + 1];
                double[] val = new double[n + 1];
                for (int i = 1; i <= n; i++) {
                    Arrays.fill(next, 0);
                    for (int j = 0; j < i; j++) {
                        next[j] += prob[j] * (1 - p);
                        next[j + 1] += prob[j] * p;
                    }
                    double[] temp = prob;
                    prob = next;
                    next = temp;
                    for (int j = k; j <= i; j++) {
                        val[i] += prob[j];
                    }
                }
                for (int i = k; i <= n; i++) {
                    for (int j = k; j <= i; j++) {
                        answer[i] = Math.max(answer[i], answer[i - j] + val[j]);
                    }
                }
                result = answer[n];
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", result);
            }
        }, 4);
    }
}
