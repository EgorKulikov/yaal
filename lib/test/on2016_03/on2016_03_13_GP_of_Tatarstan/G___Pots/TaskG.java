package on2016_03.on2016_03_13_GP_of_Tatarstan.G___Pots;



import net.egork.generated.collections.list.IntArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.maxElement;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] d = readIntArray(in, n);
        out.printLine(maxElement(new IntArray(d).qty()));
    }
}
