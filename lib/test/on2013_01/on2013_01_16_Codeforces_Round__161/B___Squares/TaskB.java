package on2013_01.on2013_01_16_Codeforces_Round__161.B___Squares;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int covered = in.readInt();
        if (covered > count) {
            out.printLine(-1);
            return;
        }
        int[] sizes = IOUtils.readIntArray(in, count);
        ArrayUtils.sort(sizes, IntComparator.REVERSE);
        if (covered == count || sizes[covered] != sizes[covered - 1]) {
            out.printLine(sizes[covered - 1], sizes[covered - 1]);
            return;
        }
        out.printLine(-1);
    }
}
