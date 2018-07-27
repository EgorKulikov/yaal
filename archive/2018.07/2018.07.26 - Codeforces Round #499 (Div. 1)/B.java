package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.readInt();
        int n = in.readInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            out.printLine(1);
            out.flush();
            int result = in.readInt();
            if (result == 0) {
                return;
            }
            p[i] = (result + 1) / 2;
        }
        int left = 2;
        int right = m;
        int at = 0;
        while (true) {
            int middle = (left + right) / 2;
            out.printLine(middle);
            out.flush();
            int result = in.readInt();
            if (result == 0) {
                return;
            }
            if (p[at % n] == 0) {
                result = -result;
            }
            at++;
            if (result == -1) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
    }
}
