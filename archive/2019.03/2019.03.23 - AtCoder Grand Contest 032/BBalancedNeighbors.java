package net.egork;

import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class BBalancedNeighbors {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        List<IntIntPair> answer = new ArrayList<>();
        int sum = n + (1 - (n & 1));
        for (int i = 1; i <= n; i++) {
            int other = sum - i;
            for (int j = i + 1; j <= n; j++) {
                if (j != other) {
                    answer.add(new IntIntPair(i, j));
                }
            }
        }
        out.printLine(answer.size());
        for (IntIntPair pair : answer) {
            out.printLine(pair.first, pair.second);
        }
    }
}
