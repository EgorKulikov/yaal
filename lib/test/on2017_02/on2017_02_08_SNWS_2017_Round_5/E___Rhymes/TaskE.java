package on2017_02.on2017_02_08_SNWS_2017_Round_5.E___Rhymes;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.misc.ArrayUtils.partialSums;
import static net.egork.numbers.IntegerUtils.generatePrimes;

public class TaskE {
    int m;
    long[][] sums;

    {
        int[] qty = new int[1000001];
        int[] prime = generatePrimes(1000000);
        for (int i : prime) {
            for (int j = i; j <= 1000000; j += i) {
                qty[j]++;
            }
        }
        m = maxElement(qty);
        int[] count = new int[1000001];
        sums = new long[m + 1][];
        for (int i = 1; i <= m; i++) {
            fill(count, 0);
            for (int j = 2; j <= 1000000; j++) {
                if (qty[j] % i == 0) {
                    count[j] = 1;
                }
            }
            sums[i] = partialSums(count);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int l = in.readInt();
        int r = in.readInt();
        for (int i = m; i >= 1; i--) {
            if (sums[i][r + 1] - sums[i][l] >= 2) {
                out.printLine(i);
                return;
            }
        }
        throw new RuntimeException();
    }
}
