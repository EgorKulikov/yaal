import net.egork.graph.Edge;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class Shooting implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int playerCount = in.readInt();
		int edgeCount = in.readInt();
		Graph graph = new Graph(playerCount * 2 + 2);
		for (int i = 0; i < playerCount; i++) {
			graph.add(new FlowEdge(2 * playerCount, i, 1));
			graph.add(new FlowEdge(i + playerCount, 2 * playerCount + 1, 1));
		}
		for (int i = 0; i < edgeCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			graph.add(new FlowEdge(first, playerCount + second, 1));
			graph.add(new FlowEdge(second, playerCount + first, 1));
		}
		if (GraphAlgorithms.dinic(graph, 2 * playerCount, 2 * playerCount + 1) != playerCount)
			out.println("Impossible");
		else {
			for (int i = 0; i < playerCount; i++) {
				for (Edge edge : graph.getIncident(i)) {
					if (edge.getFlow() == 1)
						out.println(edge.getDestination() - playerCount + 1);
				}
			}
		}
	}
}

