package on2015_01.on2015_01_10_SnarkNews_Winter_Series_2015__Round_1.C___International_Race;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	long answer = 0;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		int[] profit = new int[count - 1];
		IOUtils.readIntArrays(in, from, to, profit);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createWeightedGraph(count, from, to, ArrayUtils.asLong(profit));
		go(0, -1, graph);
		out.printLine(answer);
    }

	private long go(int vertex, int last, Graph graph) {
		long max = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (graph.destination(i) == last) continue;
			long current = go(graph.destination(i), vertex, graph) + graph.weight(i);
			answer = Math.max(answer, current + max);
			max = Math.max(max, current);
		}
		return max;
	}
}
