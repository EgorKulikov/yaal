package on2014_01.on2014_01_09_SnarkNews_Winter_Series_Round__1.TaskC;



import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int securityCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] length = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, length);
		int[] starts = IOUtils.readIntArray(in, securityCount + 1);
		MiscUtils.decreaseByOne(from, to, starts);
		Graph graph = BidirectionalGraph.createWeightedGraph(count, from, to, ArrayUtils.asLong(length));
		Pair<long[],int[]> result = ShortestDistance.dijkstraAlgorithm(graph, 0);
		for (int i = 0; i < securityCount; i++) {
			if (result.first[starts[i]] <= result.first[starts[securityCount]]) {
				out.printLine(-1);
				return;
			}
		}
		out.printLine(result.first[starts[securityCount]]);
	}
}
