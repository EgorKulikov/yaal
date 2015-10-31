package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] type = in.readString().toCharArray();
        int[] qty = new int[type.length + (type.length & 1)];
        int[] at = new int[256];
        Arrays.fill(at, -1);
        for (int i = 0; i < type.length; i++) {
            at[type[i]] = i;
        }
        for (char c : in.readString().toCharArray()) {
            if (at[c] != -1) {
                qty[at[c]]++;
            }
        }
        StringBuilder answer = new StringBuilder();
        int up = 0;
        for (int i = qty.length - 2; i >= 0; i -= 2) {
            int q5 = Math.min(qty[i + 1], 1);
            int q1 = Math.min(qty[i], 3);
            if (q5 + q1 <= 2 && up == 1 && q1 >= 1) {
                answer.append(type[i]);
                answer.append(type[i + 2]);
                up = q1 - 1;
            } else {
                for (int j = 0; j < q5; j++) {
                    answer.append(type[i + 1]);
                }
                for (int j = 0; j < q1; j++) {
                    answer.append(type[i]);
                }
                up = Math.min(1, qty[i] - q1);
            }
        }
        out.printLine(answer);
    }
}
