package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        boolean[] occupied = new boolean[n];
        for (int i = 0; i < n; i++) {
            occupied[i] = in.readCharacter() == '1';
        }
        int[] next = new int[n];
        int current = n;
        for (int i = n - 1; i >= 0; i--) {
            next[i] = current;
            if (!occupied[i]) {
                current = i;
            }
        }
        int first = current;
        int last = current;
        int farmer = current;
        for (int i = 0; i < k; i++) {
            last = next[last];
        }
        int answer = Integer.MAX_VALUE;
        while (last != n) {
            if (farmer < first) {
                farmer = first;
            }
            int cValue = max(last - farmer, farmer - first);
            while (farmer < last) {
                int nValue = max(last - next[farmer], next[farmer] - first);
                if (nValue > cValue) {
                    break;
                }
                cValue = nValue;
                farmer = next[farmer];
            }
            answer = min(answer, cValue);
            first = next[first];
            last = next[last];
        }
        out.printLine(answer);
    }
}
