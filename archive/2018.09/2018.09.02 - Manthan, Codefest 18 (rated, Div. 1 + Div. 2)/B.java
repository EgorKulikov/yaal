package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.sort;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int s = in.readInt();
        int[] a = in.readIntArray(n);
        sort(a);
        int middle = n >> 1;
        long answer = 0;
        for (int i = 0; i < middle; i++) {
            if (a[i] > s) {
                answer += a[i] - s;
            }
        }
        answer += abs(a[middle] - s);
        for (int i = middle + 1; i < n; i++) {
            if (a[i] < s) {
                answer += s - a[i];
            }
        }
        out.printLine(answer);
    }
}
