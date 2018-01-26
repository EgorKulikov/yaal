package on2017_04.on2017_04_29_Qualification.E______________________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateBinomialCoefficients;
import static net.egork.numbers.IntegerUtils.generateFactorial;
import static net.egork.numbers.IntegerUtils.reverse;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[][] c = generateBinomialCoefficients(n + 1, MOD7);
        long[] factorial = generateFactorial(n + 1, MOD7);
        long rev2 = reverse(2, MOD7);
        long rev6 = reverse(6, MOD7);
        long answer = 0;
        long base = n * (n - 1) % MOD7 * rev6 % MOD7 * rev2 % MOD7;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; i + j <= n - 2; j++) {
                int k = n - 2 - i - j;
                if (j == n - 2 || k == n - 2) {
                    continue;
                }
                answer += base * c[n - 2][i] % MOD7 * c[j + k][j] % MOD7 * factorial[i] % MOD7 * factorial[j] % MOD7 *
                        factorial[k] % MOD7;
            }
        }
        base = n * rev2 % MOD7 * rev2 % MOD7 * rev2 % MOD7;
        for (int i = 2; i < n - 2; i++) {
            answer += base * c[n - 1][i] % MOD7 * factorial[i] % MOD7 * factorial[n - 1 - i] % MOD7;
        }
        out.printLine(answer % MOD7);
    }
}
