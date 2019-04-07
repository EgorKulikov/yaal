package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

public class ForegoneSolution {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String n = in.readString();
        char[] a = new char[n.length()];
        char[] b = new char[n.length()];
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '4') {
                a[i] = '2';
                b[i] = '2';
            } else {
                a[i] = n.charAt(i);
                b[i] = '0';
            }
        }
        out.printLine("Case #" + testNumber + ":", new BigInteger(new String(a)), new BigInteger(new String(b)));
    }
}
