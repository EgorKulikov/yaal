package on2015_06.on2015_06_12_CodeChef_Snackdown_2015___Online_Elimination_Round.ChefAndTrees;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndTrees {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] from = new int[count - 1];
        int[] to = new int[count - 1];
        IOUtils.readIntArrays(in, from, to);
        if (count == 2) {
            out.printLine("NO");
            return;
        }
        MiscUtils.decreaseByOne(from, to);
        Graph graph = BidirectionalGraph.createWeightedGraph(count, from, to, ArrayUtils.createArray(count - 1, 1L));
        int current = 0;
        long diameter = 0;
        for (int i = 0; i < 3; i++) {
            long[] distance = ShortestDistance.dijkstraAlgorithm(graph, current).first;
            current = ArrayUtils.maxPosition(distance);
            diameter = distance[current];
        }
        long[] distance = ShortestDistance.dijkstraAlgorithm(graph, current).first;
        int position = ArrayUtils.maxPosition(distance);
        if (ArrayUtils.count(distance, distance[position]) == 1) {
            out.printLine("YES");
            return;
        }
        distance = ShortestDistance.dijkstraAlgorithm(graph, position).first;
        position = ArrayUtils.maxPosition(distance);
        if (ArrayUtils.count(distance, distance[position]) == 1) {
            out.printLine("YES");
            return;
        }
        out.printLine("NO");
    }
}
