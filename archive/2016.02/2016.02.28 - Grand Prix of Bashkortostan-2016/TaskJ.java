package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskJ {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int p = in.readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }
        int q = in.readInt();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.readInt();
        }
        if (bad(p, q, a, b) || bad(q, p, b, a)) {
            out.printLine("NO");
            out.printLine(res);
        } else {
            out.printLine("YES");
        }
    }

    String res;

    private boolean bad(int p, int q, int[] a, int[] b) {
        int[] z = new int[p];
        long[] r1 = new long[p];
        long[] r2 = new long[p];
        Arrays.fill(z, -1);
        z[0] = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = p - 1; j >= 0; j--) {
                if (z[j] >= 0 && j + a[i] < p) {
                    if (z[j] + b[i] > z[j + a[i]]) {
                        z[j + a[i]] = z[j] + b[i];
                        r1[j + a[i]] = r1[j];
                        r2[j + a[i]] = r2[j];
                        if (i < 50) {
                            r1[j + a[i]] |= (1L << i);
                        } else {
                            r2[j + a[i]] |= (1L << (i - 50));
                        }
                        if (z[j + a[i]] >= q) {
                            res = "";
                            for (int k = 0; k < a.length; k++) {
                                if (k < 50) {
                                    res += (r1[j + a[i]] >> k) & 1;
                                } else {
                                    res += (r2[j + a[i]] >> (k - 50)) & 1;
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
