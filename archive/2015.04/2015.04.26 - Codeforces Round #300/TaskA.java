package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    static final String TARGET = "CODEFORCES";

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        if (s.length() >= TARGET.length()) {
            for (int i = 0; i <= TARGET.length(); i++) {
                if (s.substring(0, i).equals(TARGET.substring(0, i)) && s.substring(s.length() - TARGET.length() + i).equals(TARGET.substring(i))) {
                    out.printLine("YES");
                    return;
                }
            }
        }
        out.printLine("NO");
    }
}
