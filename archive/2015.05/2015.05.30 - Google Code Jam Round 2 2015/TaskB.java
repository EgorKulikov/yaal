package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskB {

    public static final double EPS = 1e-12;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            double volume;
            double target;
            double[] rate;
            double[] temperature;
            double answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                volume = in.readDouble();
                target = in.readDouble();
                rate = new double[count];
                temperature = new double[count];
                IOUtils.readDoubleArrays(in, rate, temperature);
            }

            @Override
            public void solve() {
                double minTemp = Double.POSITIVE_INFINITY;
                double maxTemp = Double.NEGATIVE_INFINITY;
                for (int i = 0; i < count; i++) {
                    minTemp = Math.min(minTemp, temperature[i]);
                    maxTemp = Math.max(maxTemp, temperature[i]);
                }
                if (maxTemp < target - 1e-5 || minTemp > target + 1e-5) {
                    answer = -1;
                    return;
                }
                ArrayUtils.orderBy(temperature, rate);
                double sumMult = 0;
                double sumRate = 0;
                for (int i = 0; i < count; i++) {
                    sumMult += temperature[i] * rate[i];
                    sumRate += rate[i];
                }
                int start = 0;
                int end = count - 1;
                while (sumMult / sumRate < target - EPS) {
                    double nextMult = 0;
                    double nextRate = 0;
                    for (int i = start + 1; i <= end; i++) {
                        nextMult += temperature[i] * rate[i];
                        nextRate += rate[i];
                    }
                    if (nextMult / nextRate < target - EPS) {
                        start++;
                        sumMult = nextMult;
                        sumRate = nextRate;
                        continue;
                    }
                    double left = 0;
                    double right = rate[start];
                    for (int i = 0; i < 100; i++) {
                        double current = (left + right) / 2;
                        double curMult = nextMult + current * temperature[start];
                        double curRate = nextRate + current;
                        if (curMult / curRate < target) {
                            right = current;
                        } else {
                            left = current;
                        }
                    }
                    answer = volume / (nextRate + left);
                    return;
                }
                while (sumMult / sumRate > target + EPS) {
                    double nextMult = 0;
                    double nextRate = 0;
                    for (int i = start; i < end; i++) {
                        nextMult += temperature[i] * rate[i];
                        nextRate += rate[i];
                    }
                    if (nextMult / nextRate > target + EPS) {
                        end--;
                        sumMult = nextMult;
                        sumRate = nextRate;
                        continue;
                    }
                    double left = 0;
                    double right = rate[end];
                    for (int i = 0; i < 100; i++) {
                        double current = (left + right) / 2;
                        double curMult = nextMult + current * temperature[end];
                        double curRate = nextRate + current;
                        if (curMult / curRate > target) {
                            right = current;
                        } else {
                            left = current;
                        }
                    }
                    answer = volume / (nextRate + left);
                    return;
                }
                answer = volume / sumRate;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                if (answer > -.5) {
                    out.printLine("Case #" + testNumber + ":", answer);
                } else {
                    out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
                }
            }
        }, 4);
    }
}
