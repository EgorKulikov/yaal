package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class SpecialLCMOfSubarray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = IOUtils.readIntArray(in, n);
        int[] product = new int[n + 1];
        int[] three = new int[n + 1];
        product[0] = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] == 3) {
                product[i + 1] = product[i];
                three[i + 1] = three[i] + 1;
            } else {
                product[i + 1] = product[i] * a[i] % 3;
                three[i + 1] = three[i];
            }
        }
        for (int i = 0; i < q; i++) {
            int l = in.readInt() - 1;
            int r = in.readInt();
            if (three[l] % 2 != three[r] % 2) {
                out.printLine(0);
            } else {
                out.printLine(product[l] * product[r] % 3);
            }
        }
    }
}
