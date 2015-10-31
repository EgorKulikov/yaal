package on2015_06.on2015_06_06_Yandex_Algorithm_2015_Round_3.E___________________________;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int dimension = in.readInt();
        if (dimension <= 3) {
            out.printLine(1, 1);
//        } else if (dimension == 4) {
//            out.printLine(1, 6);
        } else {
            int under = dimension + 1;
            if (under % 2 == 0) {
                under /= 2;
            }
            if (IntegerUtils.isSquare(under)) {
                long answer = IntegerUtils.factorial(dimension, MOD) * IntegerUtils.power(2, dimension / 2, MOD) % MOD * IntegerUtils.reverse(Math.round(Math.sqrt(under)), MOD) % MOD;
                out.printLine(1, answer);
            } else if (dimension % 2 == 0) {
                long answer = IntegerUtils.factorial(dimension, MOD) * IntegerUtils.reverse(IntegerUtils.power(2, dimension / 2, MOD), MOD) % MOD;
                out.printLine(1, answer);
            } else {
                out.printLine(1, 1);
            }
        }
    }
}
