package old.approved.test626775076;

import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;
import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		Graph graph = new Graph(100);
		while (true) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (from == -1 && to == -1)
				break;
			graph.add(new SimpleEdge(from, to));
		}
		double result = 0;
		int count = 0;
		for (int i = 0; i < 100; i++) {
			if (graph.getIncident(i).isEmpty())
				continue;
			long[] distances = GraphAlgorithms.leviteAlgorithm(graph, i).getDistances();
			for (long value : distances) {
				if (value != 0 && value != Long.MAX_VALUE) {
					result += value;
					count++;
				}
			}
		}
		result /= count;
		if (count == 0) {
			Exit.exit(in, out);
			return;
		}
		out.printf("Case %d: average length between pages = %.3f clicks\n", testNumber, result);
	}
}





