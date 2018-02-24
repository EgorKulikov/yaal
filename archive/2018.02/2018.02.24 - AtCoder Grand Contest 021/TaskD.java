package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.fill;

public class TaskD {
    int[][][] answer;
    String s;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        s = in.readString();
        int n = s.length();
        int k = in.readInt();
        answer = new int[n][n][k + 1];
        fill(answer, -1);
        out.printLine(go(0, n - 1, k));
    }

    private int go(int from, int to, int k) {
        if (from > to) {
            return 0;
        }
        if (from == to) {
            return 1;
        }
        if (answer[from][to][k] != -1) {
            return answer[from][to][k];
        }
        if (s.charAt(from) == s.charAt(to)) {
            answer[from][to][k] = go(from + 1, to - 1, k) + 2;
        } else {
            answer[from][to][k] = max(go(from, to - 1, k), go(from + 1, to, k));
            if (k != 0) {
                answer[from][to][k] = Math.max(answer[from][to][k], go(from + 1, to - 1, k - 1) + 2);
            }
        }
        return answer[from][to][k];
    }
}
