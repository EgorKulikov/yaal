package on2015_06.on2015_06_06_Yandex_Algorithm_2015_Round_3.F______________________;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int start = in.readInt() - 1;
        int end = in.readInt() - 1;
        int initial = in.readInt();
        int target = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        int[] weight = new int[edgeCount];
        int[] cost = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to, weight, cost);
        MiscUtils.decreaseByOne(from, to);
        if (start == end) {
            out.printLine(0);
            return;
        }
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < edgeCount; i++) {
            if (weight[i] < 0 || cost[i] == 0) {
                out.printLine(0);
                return;
            }
            answer = Math.min(answer, (weight[i] + 1L) * cost[i]);
        }
        int delta = target - initial;
        Graph graph = BidirectionalGraph.createWeightedGraph(count, from, to, ArrayUtils.asLong(weight));
        long[] fromStart = ShortestDistance.dijkstraAlgorithm(graph, start).first;
        long[] fromEnd = ShortestDistance.dijkstraAlgorithm(graph, end).first;
        for (int i = 0; i < edgeCount; i++) {
            long path = Math.min(fromStart[from[i]] + fromEnd[to[i]] + weight[i], fromStart[to[i]] + fromEnd[from[i]] + weight[i]);
            if (path <= delta) {
                out.printLine(0);
                return;
            }
            if (answer / cost[i] >= path - delta) {
                answer = (path - delta) * cost[i];
            }
        }
        out.printLine(answer);
    }
}
