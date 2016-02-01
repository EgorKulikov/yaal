package on2016_01.on2016_01_31_Grand_Prix_of_Asia_2016.B___Airports;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] x = new int[n];
		int[] y = new int[n];
		readIntArrays(in, x, y);
		int[] base = new int[4];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int min = Integer.MAX_VALUE;
				int at = -1;
				for (int k = 0; k < n; k++) {
					int candidate = (2 * i - 1) * x[k] + (2 * j - 1) * y[k];
					if (candidate < min) {
						at = k;
						min = candidate;
					}
				}
				base[2 * i + j] = at;
			}
		}
		int[] component = new int[n];
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int maxDist = 0;
			for (int j = 0; j < 4; j++) {
				if (base[j] == i) {
					component[i] = j;
					maxDist = Integer.MAX_VALUE;
					break;
				}
				int candidate = Math.abs(x[i] - x[base[j]]) + Math.abs(y[i] - y[base[j]]);
				if (candidate > maxDist) {
					maxDist = candidate;
					component[i] = j;
				}
			}
			answer = Math.min(answer, maxDist);
		}
		int[][] distances = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < n; j++) {
				if (i == component[j]) {
					continue;
				}
				int current = Math.abs(x[j] - x[base[i]]) + Math.abs(y[j] - y[base[i]]);
				distances[i][component[j]] = Math.max(distances[i][component[j]], current);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i; j++) {
				distances[i][j] = distances[j][i] = Math.max(distances[i][j], distances[j][i]);
			}
		}
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i; j++) {
				edges.add(new Edge(i, j, distances[i][j]));
			}
		}
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(4);
		for (int i = 0; i < n; i++) {
			int current = 0;
			for (int j = 0; j < 4; j++) {
				int cur = Math.abs(x[i] - x[base[j]]) + Math.abs(y[i] - y[base[j]]);
				if (cur >= answer) {
					current += 1 << j;
				}
			}
			for (int j = 0; j < 4; j++) {
				if ((current >> j & 1) == 0) {
					continue;
				}
				for (int k = 0; k < j; k++) {
					if ((current >> k & 1) == 1) {
						setSystem.join(j, k);
					}
				}
			}
		}
		Collections.sort(edges, (a, b) -> Integer.compare(b.dist, a.dist));
		for (Edge edge : edges) {
			if (setSystem.join(edge.from, edge.to)) {
				answer = Math.min(answer, edge.dist);
			}
		}
		out.printLine(answer);
	}

	static class Edge {
		int from;
		int to;
		int dist;

		public Edge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}
}
