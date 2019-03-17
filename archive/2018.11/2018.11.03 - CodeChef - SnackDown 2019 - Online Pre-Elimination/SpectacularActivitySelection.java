package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.fill;
import static net.egork.numbers.IntegerUtils.generateBinomialCoefficients;

public class SpectacularActivitySelection {
    int mod;
    int n;
    long[][][] answer;
    long[][] c;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        mod = in.readInt();
        answer = new long[n + 1][k + 1][m + 1];
        fill(answer, -1);
        c = generateBinomialCoefficients(n * (n + 1) / 2 + 1, mod);
        out.printLine(go(0, k, m));
    }

    private long go(int pos, int k, int m) {
        if (k > m) {
            return 0;
        }
        if (m == 0) {
            return 1;
        }
        if (k == 0) {
            return 0;
        }
        if (answer[pos][k][m] != -1) {
            return answer[pos][k][m];
        }
        answer[pos][k][m] = 0;
        for (int i = pos + 1; i <= n; i++) {
            int other = (i - pos) * (n - i);
            for (int j = 1; j <= i - pos && j <= m; j++) {
                for (int l = 0; l <= other && j + l <= m; l++) {
                    answer[pos][k][m] += go(i, k - 1, m - j - l) * c[i - pos][j] % mod * c[other][l] % mod;
                }
            }
        }
        return answer[pos][k][m] %= mod;
    }
}
