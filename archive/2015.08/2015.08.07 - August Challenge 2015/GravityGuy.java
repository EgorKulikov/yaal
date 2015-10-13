package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GravityGuy {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[][] l = {in.readString().toCharArray(), in.readString().toCharArray()};
        int[] answer = new int[2];
        for (int i = 0; i < 2; i++) {
            answer[i] = l[i][0] == '#' ? Integer.MAX_VALUE / 2 : 0;
        }
        int[] next = new int[2];
        for (int i = 1; i < l[0].length; i++) {
            for (int j = 0; j < 2; j++) {
                next[j] = Math.min(answer[j], answer[1 - j] + 1);
                if (l[j][i] == '#') {
                    next[j] = Integer.MAX_VALUE / 2;
                }
            }
            int[] temp = answer;
            answer = next;
            next = temp;
        }
        int result = Math.min(answer[0], answer[1]);
        if (result >= Integer.MAX_VALUE / 2) {
            out.printLine("No");
        } else {
            out.printLine("Yes");
            out.printLine(result);
        }
    }
}
