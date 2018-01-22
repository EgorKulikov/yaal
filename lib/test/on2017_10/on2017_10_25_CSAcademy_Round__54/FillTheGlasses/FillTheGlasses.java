package on2017_10.on2017_10_25_CSAcademy_Round__54.FillTheGlasses;


import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.copyOf;
import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.ArrayUtils.sumArray;

public class FillTheGlasses {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] capacity = in.readIntArray(n);
        sort(capacity);
        out.printLine((sumArray(copyOf(capacity, k)) + 99) / 100);
    }
}
