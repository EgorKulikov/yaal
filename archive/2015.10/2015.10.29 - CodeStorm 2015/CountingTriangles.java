package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountingTriangles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = IOUtils.readIntArray(in, n);
        int[] s = new int[n];
        long acute = 0;
        long right = 0;
        long obtuse = 0;
        for (int i = n - 1; i >= 0; i--) {
            int rPos = n - 1;
            int oPos = n - 1;
            for (int j = i - 1; j >= 0; j--) {
                int sum = a[i] * a[i] + a[j] * a[j];
                int limit = (a[i] + a[j]) * (a[i] + a[j]);
                while (s[oPos] >= limit) {
                    oPos--;
                }
                while (s[rPos] > sum) {
                    rPos--;
                }
                obtuse += oPos - rPos;
                if (s[rPos] == sum) {
                    right++;
                    rPos--;
                }
                acute += rPos - i;
            }
            s[i] = a[i] * a[i];
        }
        out.printLine(acute, right, obtuse);
    }
}
