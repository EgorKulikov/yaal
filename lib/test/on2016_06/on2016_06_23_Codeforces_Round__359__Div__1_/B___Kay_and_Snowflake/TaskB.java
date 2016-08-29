package on2016_06.on2016_06_23_Codeforces_Round__359__Div__1_.B___Kay_and_Snowflake;



import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.graph.Graph.createGraph;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskB {
    Graph graph;
    int[] size;
    int[] answer;
    int[] parent;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] p = readIntArray(in, n - 1);
        decreaseByOne(p);
        graph = createGraph(n, p, new IntArrayList(Range.range(n - 1).map((IntToIntFunction)
                x -> x + 1)).toArray());
        size = new int[n];
        DFSOrder order = new DFSOrder(graph);
        for (int i = 0; i < n; i++) {
            size[i] = order.end[i] - order.position[i] + 1;
        }
        answer = new int[n];
        parent = new int[n];
        parent[0] = -1;
        for (int i = 0; i < n - 1; i++) {
            parent[i + 1] = p[i];
        }
        dfs(0, -1);
        for (int i = 0; i < q; i++) {
            out.printLine(answer[in.readInt() - 1] + 1);
        }
    }

    private void dfs(int vertex, int last) {
        if (size[vertex] == 1) {
            answer[vertex] = vertex;
            return;
        }
        int biggestSize = 0;
        int at = -1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int to = graph.destination(i);
            if (to == last) {
                continue;
            }
            dfs(to, vertex);
            if (size[to] > biggestSize) {
                biggestSize = size[to];
                at = to;
            }
        }
        answer[vertex] = answer[at];
        while (size[answer[vertex]] * 2 < size[vertex]) {
            answer[vertex] = parent[answer[vertex]];
        }
    }
}
