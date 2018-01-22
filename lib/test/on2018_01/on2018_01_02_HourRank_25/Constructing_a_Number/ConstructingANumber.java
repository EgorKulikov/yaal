package on2018_01.on2018_01_02_HourRank_25.Constructing_a_Number;


import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sumArray;

public class ConstructingANumber {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        out.printLine(sumArray(a) % 3 == 0 ? "Yes" : "No");
    }
}
