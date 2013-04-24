package net.egork;

import net.egork.graph.FixedSizeGraph;
import net.egork.graph.MaxFlowOnFixedSizeGraph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		int hours = in.readInt();
		if (size == 0 && count == 0 && hours == 0)
			throw new UnknownError();
		int[][] heights = IOUtils.readIntTable(in, size, size);
		FixedSizeGraph graph = new FixedSizeGraph(2 * (hours + 1) * size * size + 2, 2 * ((hours + 1) * size * size * 6 + 2 * size * size + 100));
		int source = 2 * (hours + 1) * size * size;
		int sink = source + 1;
		for (int i = 0; i < hours; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					graph.addFlowEdge(((i * size + j) * size + k) * 2 + 1, (((i + 1) * size + j) * size + k) * 2, 1);
					for (int l = 0; l < 4; l++) {
						int nj = j + MiscUtils.DX4[l];
						int nk = k + MiscUtils.DY4[l];
						if (nj >= 0 && nj < size && nk >= 0 && nk < size)
							graph.addFlowEdge(((i * size + j) * size + k) * 2 + 1, (((i + 1) * size + nj) * size + nk) * 2, 1);
					}
				}
			}
		}
		int[] startRow = new int[count];
		int[] startColumn = new int[count];
		IOUtils.readIntArrays(in, startRow, startColumn);
		int[] level = IOUtils.readIntArray(in, hours);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				graph.addFlowEdge(((hours * size + i) * size + j) * 2 + 1, sink, 1);
		}
		for (int i = 0; i < count; i++)
			graph.addFlowEdge(source, (startRow[i] * size + startColumn[i]) * 2 + 1, 1);
		for (int i = 0; i < hours; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if (heights[j][k] > level[i]/* && (i == 0 || heights[j][k] > level[i - 1])*/)
						graph.addFlowEdge((((i + 1) * size + j) * size + k) * 2, (((i + 1) * size + j) * size + k) * 2 + 1, 1);
				}
			}
		}
		out.printLine(MaxFlowOnFixedSizeGraph.dinic(graph, source, sink));
    }
}
