package on2016_02.on2016_02_26_Manthan__Codefest_16.B___A_Trivial_Problem;



import net.egork.collections.intcollection.Range;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.readInt();
        int right = count(m);
        int left = count(m - 1);
        out.printLine(right - left);
        out.printLine(Range.range(left + 1, right + 1));
    }

    private int count(int m) {
        int left = m;
        int right = 5 * (m + 1);
        while (left < right) {
            int middle = (left + right + 1) >> 1;
            int count = 0;
            int n = middle;
            while (n >= 5) {
                count += n /= 5;
            }
            if (count > m) {
                right = middle - 1;
            } else {
                left = middle;
            }
        }
        return left;
    }
}
