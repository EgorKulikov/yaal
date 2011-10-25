package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		in.readIntArrays(from, to);
		int[] degree = new int[count];
		for (int i : from)
			degree[i - 1]++;
		int[][] tree = new int[count][];
		for (int i = 0; i < count; i++)
			tree[i] = new int[degree[i]];
		int[] index = new int[count];
		for (int i = 0; i < count - 1; i++)
			tree[from[i] - 1][index[from[i] - 1]++] = to[i] - 1;
		int[] size = new int[count];
		long[] result = new long[count];
		int[] order = new int[count];
		int orderSize = 1;
		for (int i = 0; i < count; i++) {
			for (int j : tree[order[i]])
				order[orderSize++] = j;
		}
		for (int ii = count - 1; ii >= 0; ii--) {
			int i = order[ii];
			size[i] = 1;
			for (int j : tree[i])
				size[i] += size[j];
			int remainingSize = size[i] - 1;
			for (int j : tree[i]) {
				remainingSize -= size[j];
				result[i] += result[j] + remainingSize * size[j];
			}
		}
		out.println("Case " + testNumber + ": " + (count - 1) + " " + result[0]);
	}

	private long go(int vertex, int[][] tree, int[] size) {
		long remainingSize = size[vertex] - 1;
		long result = 0;
		for (int i : tree[vertex]) {
			remainingSize -= size[i];
			result += size[i] * remainingSize + go(i, tree, size);
		}
		return result;
	}

	private int initSizes(int vertex, int[][] tree, int[] size) {
		size[vertex] = 1;
		for (int next : tree[vertex])
			size[vertex] += initSizes(next, tree, size);
		return size[vertex];
	}
}

