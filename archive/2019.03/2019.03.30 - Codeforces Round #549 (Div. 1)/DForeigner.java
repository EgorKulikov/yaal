package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class DForeigner {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[][] shift = new int[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = i; k != j; k = (k + 1) % 11) {
                    shift[i][j] += k;
                }
                shift[i][j] %= 11;
            }
        }
        String s = in.readString();
        int[][] length = new int[2][11];
        int[][] next = new int[2][11];
        long answer = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                answer += length[1][digit] + 1;
            }
            for (int j = 0; j < 2; j++) {
                for (int current = 0; current < 11; current++) {
                    if (digit >= current) {
                        next[j][current] = 0;
                        continue;
                    }
                    int first = j * 10;
                    int nFirst = (1 - j) * 10;
                    int nCurrent = (nFirst + shift[first][current] + digit) % 11;
                    next[j][current] = 1 + length[1 - j][nCurrent];
                }
            }
            int[][] temp = length;
            length = next;
            next = temp;
        }
        out.printLine(answer);
    }
}
