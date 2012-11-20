package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int propertyCount = in.readInt();
		boolean[][] canBe = new boolean[count][count];
		ArrayUtils.fill(canBe, true);
		int[] max = new int[count];
		int[] min = new int[count];
		Arrays.fill(max, count - 1);
		Arrays.fill(min, 0);
		for (int i = 0; i < propertyCount; i++) {
			int type = in.readInt();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int number = in.readInt() - 1;
			for (int j = 0; j < from; j++)
				canBe[j][number] = false;
			for (int j = to + 1; j < count; j++)
				canBe[j][number] = false;
			for (int j = from; j <= to; j++) {
				if (type == 2)
					min[j] = Math.max(min[j], number);
				else
					max[j] = Math.min(max[j], number);
			}
		}
		Graph<String> graph = new Graph<String>(2 * count + 2);
		for (int i = 0; i < count; i++) {
			graph.addFlowEdge("source", "position" + i, 1);
			graph.addFlowEdge("number" + i, "sink", 1);
			for (int j = min[i]; j <= max[i]; j++) {
				if (canBe[i][j])
					graph.addFlowEdge("position" + i, "number" + j, 1);
			}
		}
		if (MaxFlow.dinic(graph, "source", "sink") != count) {
			out.printLine(-1);
			return;
		}
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			for (Edge<String> edge : graph.getOutbound("position" + i)) {
				if (edge.getFlow() != 0) {
					answer[i] = Integer.parseInt(edge.getDestination().substring(6)) + 1;
					break;
				}
			}
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
