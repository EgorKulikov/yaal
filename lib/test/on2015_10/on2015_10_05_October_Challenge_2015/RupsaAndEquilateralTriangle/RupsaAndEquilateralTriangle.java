package on2015_10.on2015_10_05_October_Challenge_2015.RupsaAndEquilateralTriangle;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RupsaAndEquilateralTriangle {
    boolean[] good = new boolean[5000001];

    {
        int[] p = IntegerUtils.generatePrimes(5000000);
        for (int i : p) {
            if ((i & 3) != 1) {
                continue;
            }
            for (int j = i; j < good.length; j += i) {
                good[j] = true;
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (good[in.readInt()]) {
            out.printLine("YES");
        } else {
            out.printLine("NO");
        }
    }
}
