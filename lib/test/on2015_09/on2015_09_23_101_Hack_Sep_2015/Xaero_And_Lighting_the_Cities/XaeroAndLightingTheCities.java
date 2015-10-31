package on2015_09.on2015_09_23_101_Hack_Sep_2015.Xaero_And_Lighting_the_Cities;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class XaeroAndLightingTheCities {
	int[] light;
	Graph graph;
	int[] with;
	int[] without;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		light = IOUtils.readIntArray(in, n);
		int[] u = new int[n - 1];
		int[] v = new int[n - 1];
		IOUtils.readIntArrays(in, u, v);
		MiscUtils.decreaseByOne(u, v);
		with = new int[n];
		without = new int[n];
		graph = BidirectionalGraph.createGraph(n, u, v);
		calculate(0, -1);
		out.printLine(Math.min(with[0], without[0]));
	}

	private void calculate(int vertex, int last) {
		with[vertex] = 1 - light[vertex];
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last) {
				continue;
			}
			calculate(next, vertex);
			with[vertex] += Math.min(without[next], with[next]);
			without[vertex] += with[next];
		}
	}
}
