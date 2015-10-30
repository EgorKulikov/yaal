package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BalancedContestOrNot {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] beauty = new int[5];
        int[] difficulty = new int[5];
        IOUtils.readIntArrays(in, beauty, difficulty);
        ArrayUtils.orderBy(beauty, difficulty);
        for (int i = 1; i < 5; i++) {
            if (beauty[i] <= beauty[i - 1] || difficulty[i] <= difficulty[i - 1]) {
                out.printLine(0);
                return;
            }
        }
        out.printLine(1);
    }
}
