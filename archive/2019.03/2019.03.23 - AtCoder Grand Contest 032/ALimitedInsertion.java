package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;

public class ALimitedInsertion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] b = in.readIntArray(n);
        for (int i = 0; i < n; i++) {
            if (b[i] > i + 1) {
                out.printLine(-1);
                return;
            }
        }
        int[] answer = createArray(n, -1);
        for (int i = n - 1; i >= 0; i--) {
            int at = -1;
            for (int j = i; j >= 0; j--) {
                if (b[j] == j + 1) {
                    at = j;
                    break;
                }
            }
            answer[i] = at + 1;
            for (int j = at + 1; j <= i; j++) {
                b[j - 1] = b[j];
            }
        }
        for (int i : answer) {
            out.printLine(i);
        }
    }
}
