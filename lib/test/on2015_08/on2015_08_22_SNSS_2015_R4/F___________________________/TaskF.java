package on2015_08.on2015_08_22_SNSS_2015_R4.F___________________________;


import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.LongIntPair;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.readInt();
        int y = in.readInt();
        int n = in.readInt();
        int[] m = IOUtils.readIntArray(in, n);
        int g = IntegerUtils.gcd(x, y);
        x /= g;
        y /= g;
        int[] xFact = factorize(x);
        int[] yFact = factorize(y);
        if (xFact.length < yFact.length || xFact.length > n) {
            out.printLine(-1);
            return;
        }
        for (int i = 0; i < xFact.length; i++) {
            int yy = i < yFact.length ? yFact[i] : 1;
            if (xFact[i] < yy) {
                out.printLine(-1);
                return;
            }
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (m[j] == xFact[i]) {
                    m[j] = yy;
                    found = true;
                    break;
                }
            }
            if (!found) {
                out.printLine(-1);
                return;
            }
        }
        out.printLine(m);
    }

    protected int[] factorize(int val) {
        List<LongIntPair> xFact = IntegerUtils.factorize(val);
        IntList simpleX = new IntArrayList();
        for (LongIntPair pair : xFact) {
            for (int i = 0; i < pair.second; i++) {
                simpleX.add((int)(long)pair.first);
            }
        }
        return simpleX.sort(IntComparator.REVERSE).toArray();
    }
}
