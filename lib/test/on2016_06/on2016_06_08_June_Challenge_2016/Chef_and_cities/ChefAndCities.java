package on2016_06.on2016_06_08_June_Challenge_2016.Chef_and_cities;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.reverse;

public class ChefAndCities {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] f = readIntArray(in, n);
        int base = (int) round(sqrt(n));
        long[] result = new long[base];
        double[] dResult = new double[base];
        for (int i = 1; i < base; i++) {
            result[i] = 1;
            dResult[i] = 1;
            for (int j = 0; j < n; j += i) {
                result[i] *= f[j];
                result[i] %= MOD7;
                dResult[i] *= f[j];
                if (dResult[i] >= 1000000000) {
                    dResult[i] /= 1000000000;
                }
            }
        }
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            if (type == 1) {
                int p = in.readInt() - 1;
                int nf = in.readInt();
                long multiply = nf * reverse(f[p], MOD7) % MOD7;
                double dMultiply = (double)nf / f[p];
                for (int j = 1; j < base; j++) {
                    if (p % j == 0) {
                        result[j] *= multiply;
                        result[j] %= MOD7;
                        dResult[j] *= dMultiply;
                        if (dResult[j] >= 1000000000) {
                            dResult[j] /= 1000000000;
                        } else if (dResult[j] < 1) {
                            dResult[j] *= 1000000000;
                        }
                    }
                }
                f[p] = nf;
            } else {
                int r = in.readInt();
                if (r < base) {
                    out.printLine(firstDigit(dResult[r]), result[r]);
                } else {
                    long answer = 1;
                    double dAnswer = 1;
                    for (int j = 0; j < n; j += r) {
                        answer *= f[j];
                        answer %= MOD7;
                        dAnswer *= f[j];
                        if (dAnswer >= 1000000000) {
                            dAnswer /= 1000000000;
                        }
                    }
                    out.printLine(firstDigit(dAnswer), answer);
                }
            }
        }
    }

    private int firstDigit(double answer) {
        while (answer >= 10 - 1e-8) {
            answer /= 10;
        }
        while (answer < 1 - 1e-9) {
            answer *= 10;
        }
        return (int)(answer + 1e-9);
    }
}
