package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.find;

public class ArrangingTheInventory {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] c = in.readCharArray(n);
        int start = find(c, '.');
        if (start == -1) {
            out.printLine(0);
            return;
        }
        long answer = 0;
        for (int i = start; i < n; i++) {
            if (c[i] == '#') {
                int end = i + 1;
                for (int j = i + 1; j < n; j++) {
                    if (j > end) {
                        break;
                    }
                    if (c[j] == '#') {
                        answer += end + 1 - j;
                        end += 2;
                    }
                }
                if (end >= n) {
                    out.printLine(-1);
                    return;
                }
                for (int k = i; k < end; k += 2) {
                    answer += k - start;
                    start++;
                }
                i = end;
            }
        }
        out.printLine(answer);
    }
}
