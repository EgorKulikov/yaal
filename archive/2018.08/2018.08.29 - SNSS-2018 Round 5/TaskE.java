package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.orderBy;
import static net.egork.misc.ArrayUtils.sumArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int p = in.readInt();
        int q = in.readInt();
        int[] a = new int[p];
        int[] b = new int[p];
        int[] c = new int[p];
        in.readIntArrays(a, b, c);
        int[] x = new int[q];
        int[] y = new int[q];
        int[] z = new int[q];
        in.readIntArrays(x, y, z);
        decreaseByOne(a, b, x, y);
        long answer = sumArray(c) * n + sumArray(z) * m;
        IndependentSetSystem f = new RecursiveIndependentSetSystem(m);
        IndependentSetSystem s = new RecursiveIndependentSetSystem(n);
        orderBy(c, a, b);
        orderBy(z, x, y);
        int i = 0;
        int j = 0;
        while (i < p && j < q) {
            if (c[i] < z[j]) {
                if (f.join(a[i], b[i])) {
                    answer -= (long)c[i] * s.getSetCount();
                }
                i++;
            } else {
                if (s.join(x[j], y[j])) {
                    answer -= (long)z[j] * f.getSetCount();
                }
                j++;
            }
        }
        for (; i < p; i++) {
            if (f.join(a[i], b[i])) {
                answer -= c[i];
            }
        }
        for (; j < q; j++) {
            if (s.join(x[j], y[j])) {
                answer -= z[j];
            }
        }
        out.printLine(answer);
    }
}
