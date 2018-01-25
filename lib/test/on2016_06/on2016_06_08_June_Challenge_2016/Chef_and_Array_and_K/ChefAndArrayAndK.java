package on2016_06.on2016_06_08_June_Challenge_2016.Chef_and_Array_and_K;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.Combinations;

import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.MiscUtils.MOD7;

public class ChefAndArrayAndK {
    Combinations c = new Combinations(100001, MOD7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        int zeroes = count(a, 0);
        long answer = 0;
        if (zeroes == 0) {
            for (int i = (k & 1); i <= min(n, k); i += 2) {
                answer += c.c(n, i);
            }
        } else {
            for (int i = 0; i <= min(k, n - zeroes); i++) {
                answer += c.c(n - zeroes, i);
            }
        }
        out.printLine(answer % MOD7);
    }
}
