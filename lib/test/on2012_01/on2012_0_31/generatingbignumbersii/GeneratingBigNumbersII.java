package on2012_01.on2012_0_31.generatingbignumbersii;



import net.egork.collections.ArrayUtils;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GeneratingBigNumbersII {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] count = IOUtils.readIntArray(in, 8);
		Graph<Integer> graph = new Graph<Integer>();
		int source = 168;
		int sink = source + 1;
		for (int i = 0; i < 8; i++)
			graph.add(new FlowEdge<Integer>(source, i, count[i]));
		long required = ArrayUtils.sumArray(count);
		for (int i = 0; ; i++) {
			graph.add(new FlowEdge<Integer>(i + 8, sink, 1));
			for (int j = 0; j < 8; j++) {
				if ((i + 1) % (j + 2) != 0)
					graph.add(new FlowEdge<Integer>(j, i + 8, 1));
			}
			required -= GraphAlgorithms.dinic(graph, source, sink);
			if (required == 0) {
				out.printLine(i + 1);
				return;
			}
		}
	}
}
