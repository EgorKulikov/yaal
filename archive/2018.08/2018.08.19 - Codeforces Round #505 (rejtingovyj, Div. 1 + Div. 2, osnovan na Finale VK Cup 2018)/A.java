package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] s = in.readCharArray(n);
        int[] qty = new int[26];
        for (char c : s) {
            qty[c - 'a']++;
        }
        out.printLine((n == 1 || maxElement(qty) > 1) ? "Yes" : "No");
    }
}
