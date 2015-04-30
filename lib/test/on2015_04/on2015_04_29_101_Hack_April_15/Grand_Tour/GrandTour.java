package on2015_04.on2015_04_29_101_Hack_April_15.Grand_Tour;



import net.egork.collections.intervaltree.LCA;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GrandTour {
    LCA lca;
    int[] distance;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] sightsee = IOUtils.readIntArray(in, count);
        int[] from = new int[count];
        int[] to = new int[count];
        int[] cost = new int[count];
        IOUtils.readIntArrays(in, from, to, cost);
        int length = in.readInt();
        int[] tour = IOUtils.readIntArray(in, length);
        MiscUtils.decreaseByOne(from, to, tour);
        Graph graph = new BidirectionalGraph(count, count - 1);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
        int special = -1;
        for (int i = 0; i < count; i++) {
            if (setSystem.join(from[i], to[i])) {
                graph.addWeightedEdge(from[i], to[i], cost[i]);
            } else {
                special = i;
            }
        }
        lca = new LCA(graph);
        distance = new int[count];
        calculate(0, -1, 0, graph);
        long answer = 0;
        for (int i : tour) {
            answer += sightsee[i];
        }
        for (int i = 1; i < tour.length; i++) {
            int a = tour[i - 1];
            int b = tour[i];
            int result = distance(a, b);
            result = Math.min(result, distance(a, from[special]) + cost[special] + distance(to[special], b));
            result = Math.min(result, distance(a, to[special]) + cost[special] + distance(from[special], b));
            answer += result;
        }
        out.printLine(answer);
    }

    int distance(int from, int to) {
        return distance[from] + distance[to] - 2 * distance[lca.getLCA(from, to)];
    }

    private void calculate(int vertex, int last, int current, Graph graph) {
        distance[vertex] = current;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next != last) {
                calculate(next, vertex, (int) (current + graph.weight(i)), graph);
            }
        }
    }
}
