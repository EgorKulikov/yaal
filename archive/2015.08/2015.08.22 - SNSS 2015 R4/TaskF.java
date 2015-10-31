package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
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
        List<Pair<Long, Integer>> xFact = IntegerUtils.factorize(val);
        IntList simpleX = new IntArrayList();
        for (Pair<Long, Integer> pair : xFact) {
            for (int i = 0; i < pair.second; i++) {
                simpleX.add((int)(long)pair.first);
            }
        }
        return simpleX.inPlaceSort(IntComparator.REVERSE).toArray();
    }
}
