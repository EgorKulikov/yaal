package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.numbers.IntegerUtils.gcd;

public class ChefAndPeriodicSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int answer = 0;
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] != -1) {
                if (last != -1 && a[i] - a[last] != i - last) {
                    if (a[i] - a[last] > i - last) {
                        out.printLine("impossible");
                        return;
                    }
                    answer = gcd(answer, i - last - a[i] + a[last]);
                }
                last = i;
            }
        }
        if (answer == 0) {
            out.printLine("inf");
        } else if (answer < maxElement(a)) {
            out.printLine("impossible");
        } else {
            out.printLine(answer);
        }
    }
}
