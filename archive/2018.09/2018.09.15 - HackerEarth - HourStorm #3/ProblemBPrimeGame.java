package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

public class ProblemBPrimeGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String x = in.readString();
        int n = x.length();
        boolean[][] isPrime = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                isPrime[j][i] = new BigInteger(x.substring(j, i + 1)).isProbablePrime(100);
            }
        }
        boolean[][] win = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                win[i][j] = isPrime[i][j] || (!win[i + 1][j] || !win[i][j - 1]);
            }
        }
        out.printLine(win[0][n - 1] ? "Alice" : "Bob");
    }
}
