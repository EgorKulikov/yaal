package net.egork;

import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.beans.Beans;
import java.util.Arrays;

public class TaskA {
	private static final long MOD = (long) (1e9 + 7);
	long[][][] result;
	private boolean[] processed;
	private Graph graph;
	private int count;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		int scumCount = in.readInt();
		int edgeCount = in.readInt();
		graph = new Graph(count);
		for (int i = 0; i < edgeCount; i++) {
			char type = in.readCharacter();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			graph.addWeightedEdge(from, to, type == 'A' ? 0 : 1);
		}
		long[] answer = new long[count + 1];
		answer[0] = 1;
		processed = new boolean[count];
		result = new long[2][count][];
		for (int i = 0; i < count; i++) {
			if (!processed[i]) {
				dfs(0, i);
				dfs(1, i);
				for (int j = count; j >= 0; j--) {
					for (int k = count; k > j; k--) {
						answer[k] += (result[0][i][k - j] + result[1][i][k - j]) * answer[j];
						answer[k] %= MOD;
					}
				}
			}
		}
		out.printLine(answer[scumCount]);
	}

	private void dfs(int isScum, int id) {
		if (result[isScum][id] != null) {
			return;
		}
		processed[id] = true;
		result[isScum][id] = new long[count + 1];
		result[isScum][id][isScum] = 1;
		for (int i = graph.firstOutbound(id); i != -1; i = graph.nextOutbound(i)) {
			int to = graph.destination(i);
			if (isScum == 1 && graph.weight(i) == 0) {
				dfs(0, to);
				for (int j = count; j >= 0; j--) {
					for (int k = count; k > j; k--) {
						result[isScum][id][k] += result[0][to][k - j] * result[isScum][id][j];
						result[isScum][id][k] %= MOD;
					}
				}
			}
			if (isScum == 1 && graph.weight(i) == 1) {
				dfs(1, to);
				for (int j = count; j >= 0; j--) {
					for (int k = count; k > j; k--) {
						result[isScum][id][k] += result[1][to][k - j] * result[isScum][id][j];
						result[isScum][id][k] %= MOD;
					}
					result[isScum][id][j] = 0;
				}
			}
			if (isScum == 0) {
				dfs(0, to);
				dfs(1, to);
				for (int j = count; j >= 0; j--) {
					for (int k = count; k > j; k--) {
						result[isScum][id][k] += (result[1][to][k - j] + result[0][to][k - j]) * result[isScum][id][j];
						result[isScum][id][k] %= MOD;
					}
				}
			}
		}
	}
}
