package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.orderBy;

public class STMincut {
    long answer;
    int currentCost;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] a = in.readIntTable(n, n);
        answer = 0;
        int[] from = new int[n * (n - 1) / 2];
        int[] to = new int[n * (n - 1) / 2];
        int[] cost = new int[n * (n - 1) / 2];
        int at = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                answer -= a[i][j] + a[j][i];
                from[at] = i;
                to[at] = j;
                cost[at++] = -max(a[i][j], a[j][i]);
            }
        }
        orderBy(cost, from, to);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        int[] size = createArray(n, 1);
        setSystem.setListener(new IndependentSetSystem.Listener() {
            @Override
            public void joined(int joinedRoot, int root) {
                answer += 2L * size[joinedRoot] * size[root] * currentCost;
                size[root] += size[joinedRoot];
            }
        });
        for (int i = 0; i < cost.length; i++) {
            currentCost = -cost[i];
            setSystem.join(from[i], to[i]);
        }
        out.printLine(answer);
    }
}
