package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[][] table = IOUtils.readIntTable(in, count, count);
        int[][] answer = new int[count][count];
        int[][] next = new int[count][count];
        answer[0][0] = table[0][0];
        for (int i = 1; i <= 2 * count - 2; i++) {
            for (int j = Math.max(0, i - count + 1); j < count && j <= i; j++) {
                for (int k = j; k < count && k <= i; k++) {
                    next[j][k] = Integer.MIN_VALUE;
                    if (i != j) {
                        if (k != i)
                            next[j][k] = Math.max(next[j][k], answer[j][k]);
                        if (k != j)
                            next[j][k] = Math.max(next[j][k], answer[j][k - 1]);
                    }
                    if (j != 0) {
                        next[j][k] = Math.max(next[j][k], answer[j - 1][k - 1]);
                        if (k != i)
                            next[j][k] = Math.max(next[j][k], answer[j - 1][k]);
                    }
                    next[j][k] += table[j][i - j];
                    if (j != k)
                        next[j][k] += table[k][i - k];
                }
            }
            int[][] temp = next;
            next = answer;
            answer = temp;
        }
        out.printLine(answer[count - 1][count - 1]);
	}
}
