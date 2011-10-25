import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		Pair<Integer, Integer>[] tree = IOUtils.readIntPairArray(in, vertexCount);
		int[] sorted = new int[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			sorted[i] = tree[i].second;
		Arrays.sort(sorted);
		Map<Integer, Integer> index = new HashMap<Integer, Integer>();
		for (int i = 0; i < vertexCount; i++)
			index.put(sorted[i], i);
		int[] left = new int[vertexCount];
		int[] right = new int[vertexCount];
		int[] parent = new int[vertexCount];
		Arrays.fill(left, -1);
		Arrays.fill(right, -1);
		Arrays.fill(parent, -1);
		int root = -1;
		for (int i = 0; i < vertexCount; i++) {
			if (tree[i].first == -1) {
				root = index.get(tree[i].second);
				continue;
			}
			int parentIndex = index.get(tree[tree[i].first - 1].second);
			int currentIndex = index.get(tree[i].second);
			if (parentIndex < currentIndex)
				right[parentIndex] = currentIndex;
			else
				left[parentIndex] = currentIndex;
			parent[currentIndex] = parentIndex;
		}
		int queryCount = in.readInt();
		int[] queries = IOUtils.readIntArray(in, queryCount);
		double[] answer = new double[queryCount];
		long[] sum = new long[vertexCount];
		int[] count = new int[vertexCount];
		int[] order = new int[vertexCount];
		int size = 1;
		order[0] = root;
		for (int i = 0; i < size; i++) {
			if (left[order[i]] != -1) {
				order[size++] = left[order[i]];
				order[size++] = right[order[i]];
			}
		}
		for (int i = 1; i < size; i++) {
			int vertex = order[i];
			sum[vertex] = sum[parent[vertex]];
			count[vertex] = count[parent[vertex]] + 1;
			if (left[parent[vertex]] == vertex)
				sum[vertex] += sorted[parent[vertex] + 1];
			else
				sum[vertex] += sorted[parent[vertex] - 1];
		}
		for (int i : queries) {
			int vertex = -Arrays.binarySearch(sorted, i) - 2;
			if (vertex == -1 || left[vertex] != -1)
				vertex++;
			out.printf("%.12f\n", (double)sum[vertex] / count[vertex]);
		}
	}
}

