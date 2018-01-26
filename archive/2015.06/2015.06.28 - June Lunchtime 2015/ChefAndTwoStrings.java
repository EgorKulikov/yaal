package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndTwoStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        String t = in.readString();
        int min = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (c1 == '?' || c2 == '?') {
                max++;
            } else if (c1 != c2) {
                min++;
                max++;
            }
        }
        out.printLine(min, max);
    }
}
