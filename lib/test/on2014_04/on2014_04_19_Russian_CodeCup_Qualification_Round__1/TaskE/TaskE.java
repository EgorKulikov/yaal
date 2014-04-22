package on2014_04.on2014_04_19_Russian_CodeCup_Qualification_Round__1.TaskE;



import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() + in.readInt();
		int[] description = IOUtils.readIntArray(in, count);
		Graph graph = new Graph(count + 2);
		for (int i = 0; i < count; i++) {
			if (description[i] == 0)
				graph.addFlowEdge(i, count + 1, 1);
			else {
				graph.addFlowEdge(count, i, description[i]);
				for (int j = 0; j < count; j++) {
					if (description[j] == 0)
						graph.addFlowWeightedEdge(i, j, Math.min(Math.abs(i - j), count - Math.abs(i - j)), 1);
				}
			}
		}
		out.printLine(new MinCostFlow(graph, count, count + 1, false).minCostMaxFlow().first);
    }
}
