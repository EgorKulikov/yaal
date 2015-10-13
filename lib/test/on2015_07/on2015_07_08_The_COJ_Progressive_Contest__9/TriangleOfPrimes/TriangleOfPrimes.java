package on2015_07.on2015_07_08_The_COJ_Progressive_Contest__9.TriangleOfPrimes;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TriangleOfPrimes {
    long[] sums = new long[1000 * 1001 / 2 + 1];

    public TriangleOfPrimes() {
        boolean[] isPrime = IntegerUtils.generatePrimalityTable(sums.length);
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + (isPrime[i] ? i : 0);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        out.printLine(sums[count * (count + 1) / 2] - sums[count * (count - 1) / 2]);
    }
}
