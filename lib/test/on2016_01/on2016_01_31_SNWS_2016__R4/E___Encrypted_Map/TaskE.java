package on2016_01.on2016_01_31_SNWS_2016__R4.E___Encrypted_Map;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.InputMismatchException;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int k = in.readInt();
//		int[] sumRow = IOUtils.readIntArray(in, n);
//		int[] sumColumn = IOUtils.readIntArray(in, m);
			int[] sumRow = new int[n];
		try {
			for (int i = 0; i < n; i++) {
				sumRow[i] = in.readInt();
			}
		} catch (InputMismatchException e) {}
		int[] sumColumn = new int[m];
		try {
			for (int i = 0; i < m; i++) {
				sumColumn[i] = in.readInt();
			}
		} catch (InputMismatchException e) {}
		long sum = ArrayUtils.sumArray(sumRow);
		if (sum != ArrayUtils.sumArray(sumColumn)) {
			out.printLine("Good Morning");
			return;
		}
		Graph graph = new Graph(n + m + 2);
		int[][] id = new int[n][m];
		int source = graph.vertexCount() - 2;
		int sink = source + 1;
		for (int i = 0; i < n; i++) {
			if (sumRow[i] < 0 || sumRow[i] > k * m) {
				out.printLine("Good Morning");
				return;
			}
			if (sumRow[i] != 0) {
				graph.addFlowEdge(source, i, sumRow[i]);
			}
		}
		for (int i = 0; i < m; i++) {
			if (sumColumn[i] < 0 || sumColumn[i] > k * n) {
				out.printLine("Good Morning");
				return;
			}
			if (sumColumn[i] != 0) {
				graph.addFlowEdge(n + i, sink, sumColumn[i]);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				id[i][j] = graph.edgeCount();
				graph.addFlowEdge(i, n + j, k);
			}
		}
		if (MaxFlow.dinic(graph, source, sink) != sum) {
			out.printLine("Good Morning");
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				id[i][j] = (int) graph.capacity(id[i][j]);
			}
		}
			boolean[][] has = new boolean[m][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (id[i][j] != 0) {
						for (int l = 0; l < m; l++) {
							if (l != j && id[i][l] != k) {
								if (has[l][j]) {
									out.printLine("Multiple Maps");
									return;
								}
							}
						}
					}
				}
				for (int j = 0; j < m; j++) {
					if (id[i][j] != 0) {
						for (int l = 0; l < m; l++) {
							if (l != j && id[i][l] != k) {
								has[j][l] = true;
							}
						}
					}
				}
			}
			for (int i = 0; i < n; i++) {
				out.printLine(id[i]);
			}
	}
}
