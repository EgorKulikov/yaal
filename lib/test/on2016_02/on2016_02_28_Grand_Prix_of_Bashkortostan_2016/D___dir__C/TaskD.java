package on2016_02.on2016_02_28_Grand_Prix_of_Bashkortostan_2016.D___dir__C;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int w = in.readInt();
        int[] f = readIntArray(in, n);
        IntervalTree tree = new ReadOnlyIntervalTree(asLong(f)) {
            @Override
            protected long neutralValue() {
                return 0;
            }

            @Override
            protected long joinValue(long left, long right) {
                return max(left, right);
            }
        };
        for (int l = 1; ; l++) {
            int remaining = w + 1;
            for (int j = 0; j < n && remaining >= 0; j += l) {
                remaining -= tree.query(j, j + l - 1) + 1;
            }
            if (remaining >= 0) {
                out.printLine(l);
                return;
            }
        }
    }
}
