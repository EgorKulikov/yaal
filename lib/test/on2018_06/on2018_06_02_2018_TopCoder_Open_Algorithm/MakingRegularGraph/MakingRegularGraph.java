package on2018_06.on2018_06_02_2018_TopCoder_Open_Algorithm.MakingRegularGraph;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class MakingRegularGraph {
    Set<IntIntPair> edges = new HashSet<>();
    int[] degree;
    NavigableSet<Integer> all = new TreeSet<>();

    public int[] addEdges(int n, int[] x, int[] y) {
        degree = new int[n];
        for (int i : x) {
            degree[i]++;
        }
        for (int j : y) {
            degree[j]++;
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] <= 1) {
                all.add(i);
            }
        }
        for (int i = 0; i < x.length; i++) {
            edges.add(new IntIntPair(x[i], y[i]));
        }
        IntList answer = new IntArrayList();
        go(answer);
        return answer.toArray();
    }

    private void go(IntList answer) {
        if (all.size() == 0) {
            return;
        }
        if (all.size() == 1) {
            answer.add(-1);
            return;
        }
        int first = all.first();
        for (int i : new ArrayList<>(all)) {
            if (i != first && !edges.contains(new IntIntPair(first, i))) {
                edges.add(new IntIntPair(first, i));
                answer.add(first);
                answer.add(i);
                if (++degree[first] == 2) {
                    all.remove(first);
                }
                if (++degree[i] == 2) {
                    all.remove(i);
                }
                go(answer);
                if (answer.last() != -1) {
                    return;
                }
                answer.popLast();
                answer.popLast();
                answer.popLast();
                degree[first]--;
                degree[i]--;
                edges.remove(new IntIntPair(first, i));
                all.add(first);
                all.add(i);
            }
        }
        answer.add(-1);
    }
}
