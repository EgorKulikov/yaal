package net.egork;

import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class AllGraphCuts {
    public int[] findGraph(int[] x) {
        int n = (int) Math.round(Math.sqrt(x.length));
        int[][] flow = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flow[i][j] = x[i * n + j];
            }
        }
        boolean bad = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (flow[i][j] != flow[j][i] || flow[i][i] != 0) {
                    bad = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    int min = Math.min(flow[i][j], Math.min(flow[i][k], flow[j][k]));
                    int qty = 0;
                    if (flow[i][j] == min) {
                        qty++;
                    }
                    if (flow[i][k] == min) {
                        qty++;
                    }
                    if (flow[j][k] == min) {
                        qty++;
                    }
                    if (qty < 2) {
                        bad = true;
                    }
                }
            }
        }
        if (bad) {
            return new int[]{-1};
        }
        IntList answer = solve(flow, new IntHashSet(Range.range(n)));
        return answer.toArray();
    }

    private IntList solve(int[][] flow, IntSet vertices) {
        if (vertices.size() == 1) {
            return new IntArrayList();
        }
        int from = -1;
        int to = -1;
        int weight = Integer.MAX_VALUE;
        for (int i : vertices) {
            for (int j : vertices) {
                if (i == j) {
                    break;
                }
                if (flow[i][j] < weight) {
                    weight = flow[i][j];
                    from = i;
                    to = j;
                }
            }
        }
        IntList answer = new IntArrayList();
        answer.add(weight * flow.length * flow.length + from * flow.length + to);
        IntSet left = new IntHashSet();
        left.add(to);
        IntSet right = new IntHashSet();
        right.add(from);
        for (int i : vertices) {
            if (i == from || i == to) {
                continue;
            }
            if (flow[from][i] == weight) {
                left.add(i);
            } else {
                right.add(i);
            }
        }
        answer.addAll(solve(flow, left));
        answer.addAll(solve(flow, right));
        return answer;
    }
}
