package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.InputMismatchException;

public class BikeRacers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int leftCount = in.readInt();
		int rightCount = in.readInt();
		int required = in.readInt();
		int[] xl = new int[leftCount];
		int[] yl = new int[leftCount];
		int[] xr = new int[leftCount];
		int[] yr = new int[leftCount];
		try {
		IOUtils.readIntArrays(in, xl, yl);
		IOUtils.readIntArrays(in, xr, yr);
		} catch (InputMismatchException e) {}
		long[][] distance = new long[leftCount][rightCount];
		for (int i = 0; i < leftCount; i++) {
			for (int j = 0; j < rightCount; j++) {
				long dx = xl[i] - xr[j];
				long dy = yl[i] - yr[j];
				distance[i][j] = dx * dx + dy * dy;
			}
		}
		long left = 0;
		long right = 200000000000000L;
		while (left < right) {
			long middle = (left + right) >> 1;
			Graph graph = new Graph(leftCount + rightCount + 2);
			for (int i = 0; i < leftCount; i++) {
				graph.addFlowEdge(leftCount + rightCount, i, 1);
				for (int j = 0; j < rightCount; j++) {
					if (distance[i][j] <= middle)
						graph.addFlowEdge(i, leftCount + j, 1);
				}
			}
			for (int i = 0; i < rightCount; i++)
				graph.addFlowEdge(leftCount + i, leftCount + rightCount + 1, 1);
			if (MaxFlow.dinic(graph, leftCount + rightCount, leftCount + rightCount + 1) >= required)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
    }
}
