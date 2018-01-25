package on2016_03.on2016_03_13_GP_of_Tatarstan.G___Pots;



import net.egork.generated.collections.list.IntArray;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] d = in.readIntArray(n);
        out.printLine(maxElement(new IntArray(d).qty()));
    }
}
