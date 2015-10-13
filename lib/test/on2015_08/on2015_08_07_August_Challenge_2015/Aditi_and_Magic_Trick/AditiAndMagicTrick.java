package on2015_08.on2015_08_07_August_Challenge_2015.Aditi_and_Magic_Trick;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AditiAndMagicTrick {
    long[] fib = IntegerUtils.generateFibonacci(1000000000000000000L);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        int pos = Arrays.binarySearch(fib, n);
        if (pos < 0) {
            pos = -pos - 2;
        }
        out.printLine(pos);
    }
}
