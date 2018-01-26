package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String a = in.readString();
        String b = in.readString();
        int[] qty = new int[26];
        for (char c : a.toCharArray()) {
            qty[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                if (--qty[c - 'a'] < 0) {
                    out.printLine("NO");
                    return;
                }
            }
        }
        out.printLine("YES");
    }
}
