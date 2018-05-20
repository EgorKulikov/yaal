package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;

public class BXORpiramida {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        int[][] value = new int[n][];
        value[0] = a;
        for (int i = 1; i < n; i++) {
            value[i] = new int[value[i - 1].length - 1];
            for (int j = 0; j < value[i].length; j++) {
                value[i][j] = value[i - 1][j] ^ value[i - 1][j + 1];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < value[i].length; j++) {
                value[i][j] = Math.max(value[i][j], max(value[i - 1][j], value[i - 1][j + 1]));
            }
        }
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int l = in.readInt() - 1;
            int r = in.readInt() - 1;
            out.printLine(value[r - l][l]);
        }
    }
}
