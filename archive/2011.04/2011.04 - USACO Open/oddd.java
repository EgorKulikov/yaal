import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class oddd implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		int[] degree = new int[vertexCount];
		for (int i = 0; i < edgeCount; i++) {
			degree[--from[i]]++;
			degree[--to[i]]++;
		}
		int[][] graph = new int[vertexCount][];
		for (int i = 0; i < vertexCount; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < edgeCount; i++) {
			graph[from[i]][--degree[from[i]]] = to[i];
			graph[to[i]][--degree[to[i]]] = from[i];
		}
		int[] order = new int[vertexCount];
		int pointer = vertexCount - 1;
		int[] parent = new int[vertexCount];
		Arrays.fill(parent, -2);
		for (int i = 0; i < vertexCount; i++) {
			if (parent[i] == -2) {
				parent[i] = -1;
				order[pointer--] = i;
				for (int j = pointer + 1; j > pointer; j--) {
					for (int child : graph[order[j]]) {
						if (parent[child] == -2) {
							parent[child] = order[j];
							order[pointer--] = child;
						}
					}
				}
			}
		}
		Set<Pair<Integer, Integer>> removed = new HashSet<Pair<Integer, Integer>>();
		boolean[] oddity = new boolean[vertexCount];
		for (int i : order) {
			oddity[i] = graph[i].length % 2 == 0;
			for (int j : graph[i]) {
				if (parent[j] == i)
					oddity[i] ^= oddity[j];
			}
			if (oddity[i]) {
				if (parent[i] == -1) {
					out.println(-1);
					return;
				}
				removed.add(Pair.makePair(i, parent[i]));
			}
		}
		out.println(edgeCount - removed.size());
		for (int i = 0; i < edgeCount; i++) {
			if (!removed.contains(Pair.makePair(from[i], to[i])) && !removed.contains(Pair.makePair(to[i], from[i])))
				out.println(i + 1);
		}
	}
}



