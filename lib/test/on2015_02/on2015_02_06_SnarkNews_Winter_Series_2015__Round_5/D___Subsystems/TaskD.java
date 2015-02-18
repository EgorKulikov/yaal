package on2015_02.on2015_02_06_SnarkNews_Winter_Series_2015__Round_5.D___Subsystems;



import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int source = in.readInt() - 1;
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] time = new int[edgeCount];
		IOUtils.readIntArrays(in, to, from, time);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = Graph.createWeightedGraph(count, from, to, ArrayUtils.asLong(time));
		long[] distance = ShortestDistance.dijkstraAlgorithm(graph, source).first;
		int fails = 0;
		long maxTime = 0;
		for (int i = 0; i < count; i++) {
			if (distance[i] != Long.MAX_VALUE) {
				fails++;
				maxTime = Math.max(maxTime, distance[i]);
			}
		}
		out.printLine(fails, maxTime);
	}
}
