package on2016_03.on2016_03_18_CROC_2016___Elimination_Round.F___Cowslip_Collections;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.copyOf;
import static net.egork.io.IOUtils.readIntArray;

public class TaskF {
    private static final long MOD = (long) (1e9 + 7);
    int[] divisor = IntegerUtils.generateDivisorTable(1000001);
    int[][] allDivisors = new int[divisor.length][];
    int[] ratio = new int[divisor.length];
    long[] fact = IntegerUtils.generateFactorial(200001, MOD);
    long[] revFact = IntegerUtils.generateReverseFactorials(200001, MOD);

    {
        allDivisors[1] = new int[]{1};
        ratio[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            int p = divisor[i];
            int c = i;
            int pow = 0;
            int mult = 1;
            do {
                pow++;
                c /= p;
                mult *= p;
            } while (c % p == 0);
            mult /= p;
            mult *= p - 1;
            allDivisors[i] = copyOf(allDivisors[c], allDivisors[c].length * (pow + 1));
            for (int j = allDivisors[c].length; j < allDivisors[i].length; j++) {
                allDivisors[i][j] = allDivisors[i][j - allDivisors[c].length] * p;
            }
            ratio[i] = ratio[c] * mult;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int q = in.readInt();
        int[] a = readIntArray(in, n);
        int[] qty = new int[divisor.length];
        for (int i : a) {
            for (int j : allDivisors[i]) {
                qty[j]++;
            }
        }
        long answer = 0;
        for (int i = 1; i < qty.length; i++) {
            if (qty[i] >= k) {
                answer += c(qty[i], k) * ratio[i] % MOD;
            }
        }
        answer %= MOD;
        int[] c = readIntArray(in, q);
        for (int i : c) {
            for (int j : allDivisors[i]) {
                if (qty[j] >= k - 1) {
                    answer += c(qty[j], k - 1) * ratio[j] % MOD;
                }
                qty[j]++;
            }
            answer %= MOD;
            out.printLine(answer);
        }
    }

    private long c(int n, int m) {
        return fact[n] * revFact[m] % MOD * revFact[n - m] % MOD;
    }
}
