package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import java.util.Arrays;

public class TextEditor {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int k;
            String[] words;
            int answer;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                k = in.readInt();
                words = IOUtils.readStringArray(in, n);
            }

            @Override
            public void solve() {
                Arrays.sort(words);
                int[] result = solve(0, n - 1, 0);
                answer = result[k];
            }

            private int[] solve(int left, int right, int prefix) {
                if (left > right) {
                    return new int[1];
                }
                int[] result = ArrayUtils.createArray(right - left + 2, Integer.MAX_VALUE / 2);
                result[0] = 0;
                if (words[left].length() == prefix) {
                    int[] next = solve(left + 1, right, prefix);
                    for (int i = 1; i < result.length; i++) {
                        result[i] = next[i - 1] + 1;
                    }
                    return result;
                }
                char c = words[left].charAt(prefix);
                int start = left;
                for (int i = left + 1; i <= right; i++) {
                    if (words[i].charAt(prefix) != c) {
                        join(result, start, i - 1, prefix);
                        start = i;
                        c = words[i].charAt(prefix);
                    }
                }
                join(result, start, right, prefix);
                return result;
            }

            private void join(int[] result, int left, int right, int prefix) {
                int delta = 0;
                while (prefix < words[left].length() && words[left].charAt(prefix) == words[right].charAt(prefix)) {
                    delta += 2;
                    prefix++;
                }
                int[] next = solve(left, right, prefix);
                for (int i = 1; i < next.length; i++) {
                    next[i] += delta;
                }
                for (int i = result.length - 1; i >= 0; i--) {
                    for (int j = 1; j < next.length && j <= i; j++) {
                        result[i] = Math.min(result[i], next[j] + result[i - j]);
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
