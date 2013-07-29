package on2013_07.on2013_07_21_Yandex_Algorithm_Online_Round__3.TaskE;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] cost = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = Graph.createGraph(count, from, to);
		Pair<int[],Graph> pair = StronglyConnectedComponents.kosaraju(graph);
		int colorCount = pair.second.vertexCount();
		int[] color = pair.first;
		color[0] = colorCount++;
		Graph special = new Graph(colorCount, edgeCount);
		for (int i = 0; i < edgeCount; i++)
			special.addWeightedEdge(color[from[i]], color[to[i]], cost[to[i]]);
		out.printLine(ShortestDistance.dijkstraAlgorithm(special, color[0], color[count - 1]).first);
	}
}
