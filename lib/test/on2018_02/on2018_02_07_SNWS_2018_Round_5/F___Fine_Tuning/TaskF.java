package on2018_02.on2018_02_07_SNWS_2018_Round_5.F___Fine_Tuning;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.misc.ArrayUtils.minElement;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n + 1);
        out.printLine(maxElement(a) - minElement(a));
    }
}
