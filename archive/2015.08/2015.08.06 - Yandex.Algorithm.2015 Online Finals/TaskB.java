package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] qty = new int[7];
        for (int i = 0; i < n; i++) {
            qty[in.readInt()]++;
        }
        int answer = 0;
        answer += qty[6];
        qty[6] = 0;
        answer += qty[5];
        qty[1] = Math.max(0, qty[1] - qty[5]);
        qty[5] = 0;
        answer += qty[4];
        int take = Math.min(qty[4], qty[2]);
        qty[2] -= take;
        qty[4] -= take;
        qty[1] = Math.max(0, qty[1] - 2 * qty[4]);
        qty[4] = 0;
        answer += qty[3] / 2;
        qty[3] %= 2;
        if (qty[3] > 0) {
            answer++;
            if (qty[2] > 0) {
                qty[2]--;
                qty[1] = Math.max(0, qty[1] - 1);
            } else {
                qty[1] = Math.max(0, qty[1] - 3);
            }
            qty[3] = 0;
        }
        answer += (2 * qty[2] + qty[1] + 5) / 6;
        out.printLine(answer);
    }
}
