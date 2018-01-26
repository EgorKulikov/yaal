package on2017_12.on2017_12_14_World_CodeSprint_12.The_Salesman;





import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.misc.ArrayUtils.minElement;

public class TheSalesman {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = in.readIntArray(n);
        out.printLine(maxElement(x) - minElement(x));
    }
}
