package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            long[] values;
            long[] frequencies;
            long[] answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                values = IOUtils.readLongArray(in, count);
                frequencies = IOUtils.readLongArray(in, count);
            }

            @Override
            public void solve() {
                long sum = 0;
                for (long i : frequencies) {
                    sum += i;
                }
                int size = Long.bitCount(sum - 1);
                answer = new long[size];
                long[] curFrequencies = new long[count];
                curFrequencies[0] = 1;
                int at = 0;
                for (int i = 0; i < count; i++) {
                    while (curFrequencies[i] != frequencies[i]) {
                        answer[at++] = values[i] - values[0];
                        long delta = values[i] - values[0];
                        int k = count - 1;
                        for (int j = count - 1; j >= 0; j--) {
                            while (k > 0 && values[j] - values[k] < delta) {
                                k--;
                            }
                            if (values[j] - values[k] == delta) {
                                curFrequencies[j] += curFrequencies[k];
                            }
                        }
                    }
                }
                int start = ArrayUtils.find(values, 0);
                int[] minReach = new int[count];
                Arrays.fill(minReach, size);
                minReach[start] = -1;
                for (int k = 0; k < size; k++) {
                    long i = answer[k];
                    for (int j = 0; j < count; j++) {
                        if (minReach[j] < k) {
                            int pos = Arrays.binarySearch(values, values[j] - i);
                            if (pos >= 0 && minReach[pos] > k) {
                                minReach[pos] = k;
                            }
                        }
                    }
                }
                long current = values[0];
                for (int i = size - 1; i >= 0; i--) {
                    int pos = Arrays.binarySearch(values, current + answer[i]);
                    if (pos >= 0 && minReach[pos] < i) {
                        current += answer[i];
                        answer[i] *= -1;
                    }
                }
                Arrays.sort(answer);
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.print("Case #" + testNumber + ": ");
                out.printLine(answer);
            }
        }, 4);
    }
}
