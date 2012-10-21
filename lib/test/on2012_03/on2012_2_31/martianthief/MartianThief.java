package on2012_03.on2012_2_31.martianthief;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class MartianThief {
	private int[] weights;
	private int[] value;
	private int[][] graph;
	long[][] answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int capacity = in.readInt();
		weights = IOUtils.readIntArray(in, count);
		value = IOUtils.readIntArray(in, count);
		int[] parent = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(parent);
//		for (int i = 0; i < count; i++) {
//			if (value[i] == 0)
//				parent[i] = -1;
//		}
		int[] degree = new int[count + 1];
		for (int i : parent) {
			if (i != -1)
				degree[i]++;
			else
				degree[count]++;
		}
		graph = new int[count + 1][];
		for (int i = 0; i <= count; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < count; i++) {
			if (parent[i] != -1)
				graph[parent[i]][--degree[parent[i]]] = i;
			else
				graph[count][--degree[count]] = i;
		}
		weights = Arrays.copyOf(weights, count + 1);
		value = Arrays.copyOf(value, count + 1);
		answer = new long[count + 1][];
		go(count, capacity);
		out.printLine(CollectionUtils.maxElement(Array.wrap(answer[count])));
	}

	private void go(int vertex, int capacity) {
		answer[vertex] = new long[capacity + 1];
		if (weights[vertex] > capacity)
			return;
		answer[vertex][weights[vertex]] = value[vertex];
		boolean first = true;
		for (int i : graph[vertex]) {
			go(i, capacity - weights[vertex]);
			if (first) {
				for (int j = 0; j < answer[i].length; j++)
					answer[vertex][j + weights[vertex]] = answer[i][j] + value[vertex];
				first = false;
			} else {
				for (int j = capacity; j >= 0; j--) {
					for (int k = 0; weights[vertex] <= j - k && k < answer[i].length; k++)
						answer[vertex][j] = Math.max(answer[vertex][j], answer[vertex][j - k] + answer[i][k]);
				}
			}
		}
	}
}
