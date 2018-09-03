package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.abs;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long m = in.readLong();
        String w = in.readString();
        int answer = 0;
        int[] delta = new int[w.length() + 1];
        for (int i = 0; i < w.length(); i++) {
            for (int j = i + 1; j <= i + 2; j++) {
                int a = i;
                int b = j;
                int start = 0;
                int end = 0;
                int diff = 0;
                while (a >= 0 && b < w.length()) {
                    int current = abs(w.charAt(a) - w.charAt(b));
                    diff += current;
                    delta[end++] = current;
                    while (diff > m) {
                        diff -= delta[start++];
                    }
                    answer = Math.max(answer, end - start);
                    a--;
                    b++;
                }
            }
        }
        out.printLine(answer);
    }
}
