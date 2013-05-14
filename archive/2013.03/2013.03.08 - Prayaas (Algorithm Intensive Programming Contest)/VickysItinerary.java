package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class VickysItinerary {
	int[][] f = new int[3001][3001];

	{
		for (int i = 1; i <= 3000; i++) {
			int x = i + 1;
			for (int j = i; j <= 3000; j++) {
				f[i][j] = f[i - 1][j] + f[i][j - 1] - f[i - 1][j - 1];
				int toCheck = i * i + j * j;
				while (x * x < toCheck)
					x++;
				if (x * x == toCheck)
					f[i][j]++;
				f[j][i] = f[i][j];
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] order = IOUtils.readIntArray(in, count);
		Graph<Integer> graph = new Graph<Integer>();
		int source = Integer.MIN_VALUE;
		int sink = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			graph.addFlowEdge(source, -i - 1, 1);
			graph.addFlowEdge(i + 1, sink, 1);
			for (int j = 0; j < count; j++) {
				if ((f[order[i]][order[j]] & 1) == 1)
					graph.addFlowEdge(-i - 1, j + 1, 1);
			}
		}
		out.printLine(MaxFlow.dinic(graph, source, sink));
    }
}
