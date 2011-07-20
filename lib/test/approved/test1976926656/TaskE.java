package approved.test1976926656;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		if (vertexCount == 0) {
			Exit.exit(in, out);
			return;
		}
		int source = in.readInt() - 1;
		int destination = in.readInt() - 1;
		int edgeCount = in.readInt();
		Graph graph = new BidirectionalGraph(vertexCount);
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int capacity = in.readInt();
			graph.add(new FlowEdge(from, to, capacity));
		}
		out.println("Network " + testNumber);
		out.println("The bandwidth is " + GraphAlgorithms.dinic(graph, source, destination) + ".");
		out.println();
	}
}



