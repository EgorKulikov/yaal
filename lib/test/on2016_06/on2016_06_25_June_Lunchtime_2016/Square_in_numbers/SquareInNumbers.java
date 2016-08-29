package on2016_06.on2016_06_25_June_Lunchtime_2016.Square_in_numbers;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static net.egork.io.IOUtils.readLongArray;
import static net.egork.numbers.IntegerUtils.gcd;

public class SquareInNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] a = readLongArray(in, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (gcd(a[i], a[j]) != 1) {
                    out.printLine(gcd(a[i], a[j]));
                    return;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (long j = 2; j * j * j <= a[i]; j++) {
                if (a[i] % (j * j) == 0) {
                    out.printLine(j);
                    return;
                }
                if (a[i] % j == 0) {
                    a[i] /= j;
                }
            }
            if (a[i] != 1) {
                long number = round(sqrt(a[i]));
                if (number * number == a[i]) {
                    out.printLine(number);
                    return;
                }
            }
        }
    }
}
