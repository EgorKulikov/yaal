package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.gcd;
import static net.egork.numbers.IntegerUtils.lcm;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] a = new long[n];
        long[] b = new long[n];
        in.readLongArrays(a, b);
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer = gcd(answer, lcm(a[i], b[i]));
        }
        for (int i = 0; i < n; i++) {
            if (gcd(answer, a[i]) != 1) {
                answer = gcd(answer, a[i]);
            } else {
                answer = gcd(answer, b[i]);
            }
        }
        out.printLine(answer == 1 ? -1 : answer);
    }
}
