package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.gcd;
import static net.egork.numbers.IntegerUtils.lcm;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[] s = in.readCharArray(n);
        char[] t = in.readCharArray(m);
        int g = gcd(n, m);
        int addS = n / g;
        int addT = m / g;
        for (int i = 0, j = 0; i < n; i += addS, j += addT) {
            if (s[i] != t[j]) {
                out.printLine(-1);
                return;
            }
        }
        out.printLine(lcm(n, m));
    }
}
