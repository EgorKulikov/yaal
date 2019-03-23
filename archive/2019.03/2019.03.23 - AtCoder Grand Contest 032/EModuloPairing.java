package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sort;

public class EModuloPairing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = in.readIntArray(2 * n);
        sort(a);
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            int x = 2 * mid;
            int y = 2 * n - 1;
            boolean ok = true;
            while (x < y) {
                if (a[x] + a[y] < m) {
                    ok = false;
                    break;
                }
                x++;
                y--;
            }
            if (ok) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int x = 0;
        int y = 2 * left - 1;
        int answer = 0;
        while (x < y) {
            answer = Math.max(answer, a[x] + a[y]);
            x++;
            y--;
        }
        x = 2 * left;
        y = 2 * n - 1;
        while (x < y) {
            answer = Math.max(answer, a[x] + a[y] - m);
            x++;
            y--;
        }
        out.printLine(answer);
    }
}
