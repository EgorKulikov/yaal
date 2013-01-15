package on2013_01.on2013_01_14_SnarkNews_Winter_Series_Round__2.DFS;



import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class DFS {
    Set<Integer>[] graph;
    int[] order;
    boolean[] processed;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        graph = new Set[count];
        for (int i = 0; i < count; i++)
            graph[i] = new EHashSet<Integer>();
        for (int i = 0; i < edgeCount; i++) {
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            graph[from].add(to);
            graph[to].add(from);
        }
        order = IOUtils.readIntArray(in, count);
        MiscUtils.decreaseByOne(order);
        processed = new boolean[count];
        int step = 0;
        while (step != count) {
            step = go(order[step], step + 1);
            if (step == -1) {
                out.printLine("No");
                return;
            }
        }
        out.printLine("Yes");
    }

    private int go(int vertex, int step) {
        processed[vertex] = true;
        while (step < graph.length && graph[vertex].contains(order[step])) {
            step = go(order[step], step + 1);
            if (step == -1)
                return -1;
        }
        for (int i : graph[vertex]) {
            if (!processed[i])
                return -1;
        }
        return step;
    }
}
