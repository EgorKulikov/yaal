package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntTable;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[][] orders = readIntTable(in, n, k);
        decreaseByOne(orders);
        int answer = 0;
        IntSet taken = new IntHashSet();
        do {
            int was = answer;
            answer = max(taken.size(), 1);
            for (int i = 0; i < n; i++) {
                for (int j = was; j < answer; j++) {
                    taken.add(orders[i][j] + 1);
                }
            }
        } while (answer != taken.size());
        IntList result = new IntArrayList(taken);
        result.sort();
        out.printLine(result.size());
        out.printLine(result);
    }
}
