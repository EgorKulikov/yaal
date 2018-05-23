package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.reversePermutation;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class BBackfront {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = in.readIntArray(n);
        decreaseByOne(p);
        int[] rev = reversePermutation(p);
        int last = 0;
        int answer = n;
        for (int i = 1; i < n; i++) {
            if (rev[i] < rev[i - 1]) {
                answer = Math.min(answer, n - i + last);
                last = i;
            }
        }
        answer = Math.min(answer, last);
        out.printLine(answer);
    }
}
