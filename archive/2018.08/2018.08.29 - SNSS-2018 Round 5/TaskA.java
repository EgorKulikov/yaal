package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.minElement;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String first = in.readString();
        String second = in.readString();
        int n = in.readInt();
        int[] h = new int[n + 2];
        int[] m = new int[n + 2];
        parse(h, m, first, 0);
        parse(h, m, second, 1);
        for (int i = 0; i < n; i++) {
            parse(h, m, in.readString(), i + 2);
        }
        int[] answer = new int[n + 1];
        int[] next = new int[n + 1];
        for (int i = 2; i < n + 2; i++) {
            next[i - 1] = MAX_VALUE;
            int delta = getDelta(h[i], m[i], h[i - 1], m[i - 1]);
            for (int j = 0; j < i - 1; j++) {
                next[j] = answer[j] + delta;
                next[i - 1] = Math.min(next[i - 1], answer[j] + getDelta(h[i], m[i], h[j], m[j]));
            }
            int[] temp = answer;
            answer = next;
            next = temp;
        }
        out.printLine(minElement(answer));
    }

    private int getDelta(int h, int m, int h1, int m1) {
        int hh = abs(h - h1);
        hh = Math.min(hh, 24 - hh);
        int mm = abs(m - m1);
        mm = Math.min(mm, 60 - mm);
        return hh + mm;
    }

    private void parse(int[] h, int[] m, String time, int i) {
        h[i] = parseInt(time.substring(0, 2));
        m[i] = parseInt(time.substring(3));
    }
}
