package on2012_12.on2012_12_19_Volume_2._1109___Conference;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1109 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int leftCount = in.readInt();
		int rightCount = in.readInt();
		int pairCount = in.readInt();
		Graph<String> graph = new Graph<String>();
		String[] left = new String[leftCount];
		for (int i = 0; i < leftCount; i++)
			graph.addFlowEdge("source", left[i] = "left" + i, 1);
		String[] right = new String[rightCount];
		for (int i = 0; i < rightCount; i++)
			graph.addFlowEdge(right[i] = "right" + i, "sink", 1);
		for (int i = 0; i < pairCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			graph.addFlowEdge(left[from], right[to], 1);
		}
		out.printLine(leftCount + rightCount - MaxFlow.dinic(graph, "source", "sink"));
	}
}
