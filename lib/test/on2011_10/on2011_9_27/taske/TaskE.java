package on2011_10.on2011_9_27.taske;


import net.egork.misc.ArrayUtils;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int teaCount = in.readInt();
		int[] quantity = IOUtils.readIntArray(in, teaCount);
		int[][] favorite = new int[count][];
		for (int i = 0; i < count; i++)
			favorite[i] = IOUtils.readIntArray(in, in.readInt());
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < favorite[i].length; j++)
				favorite[i][j]--;
		}
		int right = (int) (ArrayUtils.sumArray(quantity) / count);
		int left = 0;
		while (left < right) {
			int middle = (left + right + 1) / 2;
			if (canDo(favorite, quantity, middle))
				left = middle;
			else
				right = middle - 1;
		}
		out.println(left);
	}

	private boolean canDo(int[][] favorite, int[] quantity, int middle) {
		Graph graph = new Graph(favorite.length + quantity.length + 2);
		int source = favorite.length + quantity.length;
		int sink = source + 1;
		for (int i = 0; i < favorite.length; i++) {
			graph.addFlowEdge(source, i, middle);
			for (int j : favorite[i])
				graph.addFlowEdge(i, favorite.length + j, quantity[j]);
		}
		for (int j = 0; j < quantity.length; j++)
			graph.addFlowEdge(favorite.length + j, sink, quantity[j]);
		return MaxFlow.dinic(graph, source, sink) == middle * favorite.length;
	}
}
