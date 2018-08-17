package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        String s = in.readString();
        String t = in.readString();
        int asterisk = s.indexOf('*');
        if (asterisk == -1) {
            out.printLine(s.equals(t) ? "YES" : "NO");
            return;
        }
        if (n > m + 1) {
            out.printLine("NO");
            return;
        }
        out.printLine(t.startsWith(s.substring(0, asterisk)) && t.endsWith(s.substring(asterisk + 1)) ? "YES" : "NO");
    }
}
