package on2013_08.on2013_08_12_SnarkNews_Summer_Series_Round__2.TaskB;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int left = 0;
		int right = count - 1;
		while (left < right) {
			int middle = (left + right) >> 1;
			if (required(0, -1, graph, middle) == 0)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
    }

	private int required(int vertex, int last, Graph graph, int have) {
		int total = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				total += 1 + required(next, vertex, graph, have);
		}
		return Math.max(0, total - have);
	}
}
