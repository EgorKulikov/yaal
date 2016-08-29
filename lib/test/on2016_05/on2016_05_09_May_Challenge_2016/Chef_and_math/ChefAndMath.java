package on2016_05.on2016_05_09_May_Challenge_2016.Chef_and_math;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.copyOfRange;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateFibonacci;

public class ChefAndMath {
    long[] f = copyOfRange(generateFibonacci(1000000000), 1, 44);
    IntHashMap[][] result;

    {
        result = new IntHashMap[11][43];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 43; j++) {
                result[i][j] = new IntHashMap();
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.readInt();
        int k = in.readInt();
        out.printLine(go(k, 42, x));
    }

    private int go(int remaining, int index, int x) {
        if (index == -1) {
            return 0;
        }
        if (x > (long)remaining * f[index]) {
            return 0;
        }
        if (remaining == 0) {
            return 1;
        }
        if (result[remaining][index].contains(x)) {
            return result[remaining][index].get(x);
        }
        if (x < f[index]) {
            int call = go(remaining, index - 1, x);
            result[remaining][index].put(x, call);
            return call;
        }
        int call = go(remaining - 1, index, (int) (x - f[index])) + go(remaining, index - 1, x);
        if (call >= MOD7) {
            call -= MOD7;
        }
        result[remaining][index].put(x, call);
        return call;
    }
}
