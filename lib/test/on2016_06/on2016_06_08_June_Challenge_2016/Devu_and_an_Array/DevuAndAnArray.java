package on2016_06.on2016_06_08_June_Challenge_2016.Devu_and_an_Array;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.maxElement;
import static net.egork.misc.ArrayUtils.minElement;

public class DevuAndAnArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = readIntArray(in, n);
        int min = minElement(a);
        int max = maxElement(a);
        for (int i = 0; i < q; i++) {
            int t = in.readInt();
            if (t >= min && t <= max) {
                out.printLine("Yes");
            } else {
                out.printLine("No");
            }
        }
    }
}
