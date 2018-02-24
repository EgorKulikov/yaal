package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] a = in.readIntArray(10);
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            int[] b = in.readIntArray(6);
            int qty = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 6; k++) {
                    if (a[j] == b[k]) {
                        qty++;
                    }
                }
            }
            out.printLine(qty >= 3 ? "Lucky" : "Unlucky");
        }
    }
}
