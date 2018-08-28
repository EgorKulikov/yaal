package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int n = s.length();
        s += s;
        int start = 0;
        int answer = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                answer = Math.max(answer, i - start);
                start = i;
            }
        }
        answer = Math.max(answer, s.length() - start);
        answer = Math.min(answer, n);
        out.printLine(answer);
    }
}
