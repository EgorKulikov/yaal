package on2015_10.on2015_10_31_Yekaterinbirg_GP.TaskI;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		long c = in.readLong();
		long[][] a = new long[n][k];
		ArrayUtils.fill(a, Long.MAX_VALUE);
		int m = in.readInt();
		for (int i = 0; i < m; i++) {
			int s = in.readInt() - 1;
			int t = in.readInt() - 1;
			long w = in.readLong();
//			if (w < 0) {
//				while (true);
//			}
//			if (a[s][t] != Long.MAX_VALUE) {
//				while (true);
//			}
			a[s][t] = Math.min(a[s][t], w);
		}
		int q = in.readInt();
		long[][] r = new long[q][k];
		long sum = 0;
		for (int i = 0; i < q; i++) {
			for (int j = 0; j < k; j++) {
				r[i][j] = in.readInt();
				sum += r[i][j];
			}
		}
		if (sum == 0) {
			out.printLine(0);
			return;
		}

		long[][] d = new long[q][q];
		int[][] cst = new int[k][n];
		for (int i = 0; i < q; i++) {
			long[] w = new long[k];
			for (int j = i; j < q; j++) {
				for (int x = 0; x < k; x++) {
					w[x] += r[j][x];
				}
//				Graph graph = new Graph(n + k + 2);
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < k; y++) {
						if (a[x][y] < Long.MAX_VALUE) {
							cst[y][x] = (int) (a[x][y] * w[y]);
//							graph.addFlowWeightedEdge(x, n + y, a[x][y] * w[y], 1);
						} else {
							if (w[y] == 0) {
								cst[y][x] = 0;
							} else {
								cst[y][x] = Integer.MAX_VALUE / 1000;
							}
						}
					}
				}
//				for (int x = 0; x < n; x++) {
//					graph.addFlowWeightedEdge(n + k, x, 0, 1);
//				}
//				for (int x = 0; x < k; x++) {
//					if (w[x] != 0) {
//						graph.addFlowWeightedEdge(n + x, n + k + 1, 0, 1);
//					}
//				}
//				Pair<Long, Long> flow = MinCostFlow.minCostMaxFlow(graph, n + k, n + k + 1, true);
				d[i][j] = hung(cst);
//				System.out.println(i + " " + j + " " + Arrays.toString(w) + " " + flow + " " + d[i][j]);

			}
		}

		long[] dd = new long[q + 1];
		Arrays.fill(dd, Long.MAX_VALUE);
		dd[0] = 0;
		for (int i = 0; i < q; i++) {
			for (int j = i + 1; j <= q; j++) {
				dd[j] = Math.min(dd[j], dd[i] + c + d[i][j - 1]);
			}
		}
		out.printLine(dd[q]);
	}

	long hung(int[][] a) {
			int n = a.length;
			int m = a[0].length;
			int[] u = new int[n + 1];
			int[] v = new int[m + 1];
			int[] p = new int[m + 1];
			int[] way = new int[m + 1];
			int[] minv = new int[m + 1];
			int INF = Integer.MAX_VALUE / 2;
			boolean[] used = new boolean[m + 1];
			for (int i = 1; i <= n; ++i) {
				p[0] = i;
				int j0 = 0;
				Arrays.fill(minv, INF);
				Arrays.fill(used, false);
				do {
					used[j0] = true;
					int i0 = p[j0], delta = INF, j1 = -1;
					for (int j = 1; j <= m; ++j)
						if (!used[j]) {
							int cur = a[i0 - 1][j - 1] - u[i0] - v[j];
							if (cur < minv[j]) {
								minv[j] = cur;
								way[j] = j0;
							}
							if (minv[j] < delta) {
								delta = minv[j];
								j1 = j;
							}
						}
					for (int j = 0; j <= m; ++j)
						if (used[j]) {
							u[p[j]] += delta;
							v[j] -= delta;
						} else
							minv[j] -= delta;
//					if (j1 == -1) {
//						return 0;
//					}
					j0 = j1;
				} while (p[j0] != 0);
				do {
					int j1 = way[j0];
					p[j0] = p[j1];
					j0 = j1;
				} while (j0 != 0);
			}
			return -v[0];
	}
}
