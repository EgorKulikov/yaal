package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[][] s = in.readTable(n, m);
        long tRow = 0;
        long tCol = 0;
        int qty = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s[i][j] == 'B') {
                    tRow += i;
                    tCol += j;
                    qty++;
                }
            }
        }
        out.printLine(tRow / qty + 1, tCol / qty + 1);
    }
}
