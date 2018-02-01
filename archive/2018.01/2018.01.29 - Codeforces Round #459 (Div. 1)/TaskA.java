package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            int min = 0;
            int max = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == ')') {
                    min--;
                    max--;
                } else if (s.charAt(j) == '(') {
                    min++;
                    max++;
                } else {
                    min--;
                    max++;
                }
                if (max < 0) {
                    break;
                }
                min = Math.max(min, 0);
                if (min == 0 && ((j - i) & 1) == 1) {
                    answer++;
                }
            }
        }
        out.printLine(answer);
    }
}
