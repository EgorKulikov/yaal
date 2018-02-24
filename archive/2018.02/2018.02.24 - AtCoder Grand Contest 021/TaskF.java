package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;

import static net.egork.misc.MiscUtils.MODF;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        long[][] answer = new long[n + 1][m + 1];
        answer[0][0] = 1;
        long[] temp = new long[n + 1];
        long[][] c = IntegerUtils.generateBinomialCoefficients(n + 1, MODF);
        for (int mm = 1; mm <= m; mm++) {
            answer[0][mm] = 1;
            for (int nn = 1; nn <= n; nn++) {
                for (int j = 1; j < nn; j++) {
                    temp[j] = 0;
                    for (int k = 0; k < j; k++) {
                        temp[j] += c[j - 1][k] * answer[nn - k - 2][mm - 1] % MODF;
                    }
                    temp[j] %= MODF;
                }
                answer[nn][mm] = answer[nn][mm - 1] * ((nn + 1) * nn / 2 + 1) % MODF;
                for (int j = 0; j < nn; j++) {
                    answer[nn][mm] += (j + 1) * (nn - j) * answer[nn - 1][mm - 1] % MODF;
                    for (int k = j + 1; k < nn; k++) {
                        answer[nn][mm] += (j + 1) * (nn - k) * temp[k - j];
                    }
                    answer[nn][mm] %= MODF;
                }
            }
        }
        long result = 0;
        for (int i = 0; i <= n; i++) {
            result += answer[i][m] * c[n][i] % MODF;
        }
        result %= MODF;
        out.printLine(result);
    }
}
