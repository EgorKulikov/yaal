package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.minElement;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        if (minElement(a) == 1 || minElement(b) == 1) {
            out.printLine(-1);
            return;
        }
        double answer = m;
        for (int i : a) {
            answer /= i - 1;
            answer *= i;
        }
        for (int i : b) {
            answer /= i - 1;
            answer *= i;
        }
        out.printLine(answer - m);
    }
}
