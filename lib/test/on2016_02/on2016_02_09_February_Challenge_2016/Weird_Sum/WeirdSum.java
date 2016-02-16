package on2016_02.on2016_02_09_February_Challenge_2016.Weird_Sum;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Interpolation;
import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class WeirdSum {
    static final long MOD = 998244353;
    static final int BUBEN = 900;

//    static long[][] values = new long[BUBEN + 2][BUBEN + 2];
//    static long[][] coef = new long[BUBEN + 2][BUBEN + 2];
    static Interpolation[] interpolations = new Interpolation[BUBEN + 1];

    static {
        long[] f = IntegerUtils.generateReverseFactorials(BUBEN + 2, MOD);
        for (int i = 1; i <= BUBEN; i++) {
            long[] values = new long[i + 2];
            for (int j = 1; j <= i + 1; j++) {
                values[j] = (values[j - 1] + IntegerUtils.power(j, i, MOD)) % MOD;
            }
            interpolations[i] = new Interpolation(values, MOD, f);
        }
    }

    static long result(int k, long x) {
        if (k > BUBEN) {
            long answer = 0;
            for (int i = 0; i <= x; i++) {
                answer += IntegerUtils.power(i, k, MOD);
            }
            return answer % MOD;
        }
        return interpolations[k].calculate(x);
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        BigInteger n = in.readBigInteger();
        long answer = result(1, n.mod(BigInteger.valueOf(MOD)).longValue());
        BigInteger current = BigInteger.valueOf(4);
        BigInteger limit = current;
        while (limit.pow(2).compareTo(n) <= 0) {
            limit = limit.multiply(BigInteger.valueOf(2));
        }
        long[] mu = MultiplicativeFunction.MOBIUS.calculateUpTo(10000);
        for (int i = 2; current.compareTo(n) <= 0; i++) {
            BigInteger left = BigInteger.ONE;
            BigInteger right = limit;
            while (left.compareTo(right) < 0) {
                BigInteger middle = left.add(right).add(BigInteger.ONE).divide(BigInteger.valueOf(2));
                if (middle.pow(i).compareTo(n) <= 0) {
                    left = middle;
                } else {
                    right = middle.subtract(BigInteger.ONE);
                }
            }
            limit = left;
            long x = limit.mod(BigInteger.valueOf(MOD)).longValue();
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    answer += result(j, x) * mu[j];
                }
            }
            current = current.multiply(BigInteger.valueOf(2));
        }
        answer--;
        answer %= MOD;
        if (answer < 0) {
            answer += MOD;
        }
        out.printLine(answer);
    }
}
