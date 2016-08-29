package on2016_06.on2016_06_02_HourRank_9.Longest_Mod_Path;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.abs;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;
import static net.egork.numbers.IntegerUtils.gcd;

public class LongestModPath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] x = new int[n];
        readIntArrays(in, a, b, x);
        decreaseByOne(a, b);
        Graph graph = BidirectionalGraph.createGraph(n, a, b);
        IntList cycle = dfs(graph, 0, -1, new boolean[n]);
        long cycleCost = 0;
        for (int i : cycle) {
            if ((i & 1) == 0) {
                cycleCost += x[i >> 1];
            } else {
                cycleCost -= x[i >> 1];
            }
        }
        cycleCost = abs(cycleCost);
        long[] cost = new long[n];
        dfs(graph, graph.source(cycle.get(0)), new boolean[n], cost, x, 0);
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int s = in.readInt() - 1;
            int e = in.readInt() - 1;
            long m = in.readInt();
            long mm = m;
            m = gcd(m, cycleCost);
            long answer = (cost[e] - cost[s]) % m;
            if (answer < 0) {
                answer += m;
            }
            answer += mm - m;
            out.printLine(answer);
        }
    }

    private void dfs(Graph graph, int vertex, boolean[] visited, long[] cost, int[] x, long current) {
        if (visited[vertex]) {
            return;
        }
        visited[vertex] = true;
        cost[vertex] = current;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            dfs(graph, next, visited, cost, x, current + ((i & 1) == 0 ? x[i >> 1] : -x[i >> 1]));
        }
    }

    private IntList dfs(Graph graph, int vertex, int edge, boolean[] visited) {
        visited[vertex] = true;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            if ((i ^ 1) == edge) {
                continue;
            }
            int next = graph.destination(i);
            if (visited[next]) {
                IntList result = new IntArrayList();
                result.add(i);
                return result;
            }
            IntList result = dfs(graph, next, i, visited);
            if (result != null) {
                if (graph.destination(result.first()) != graph.source(result.last())) {
                    result.add(i);
                }
                return result;
            }
        }
        return null;
    }
}
