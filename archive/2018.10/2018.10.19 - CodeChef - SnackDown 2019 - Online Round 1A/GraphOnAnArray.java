package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.count;
import static net.egork.numbers.IntegerUtils.gcd;

public class GraphOnAnArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (gcd(a[i], a[j]) == 1) {
                    setSystem.join(i, j);
                }
            }
        }
        if (setSystem.getSetCount() == 1) {
            out.printLine(0);
            out.printLine(a);
        } else {
            if (count(a, 43) == n) {
                a[0] = 47;
            } else {
                a[0] = 43;
            }
            out.printLine(1);
            out.printLine(a);
        }
    }
}
