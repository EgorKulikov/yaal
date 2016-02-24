package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class BankRobbery {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] amount = readIntTable(in, n, 4);
        int[] answer = new int[4];
        int[] next = new int[4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                next[j] = Integer.MAX_VALUE;
                for (int k = 0; k < 4; k++) {
                    if (j != k) {
                        next[j] = Math.min(next[j], answer[k] + amount[i][j]);
                    }
                }
            }
            int[] temp = answer;
            answer = next;
            next = temp;
        }
        out.printLine(minElement(answer));
    }
}
