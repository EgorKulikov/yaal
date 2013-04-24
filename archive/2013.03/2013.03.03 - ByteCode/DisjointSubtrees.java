package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class DisjointSubtrees {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int firstSize = in.readInt();
		int secondSize = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		if (firstSize + secondSize > count) {
			out.printLine(-1);
			return;
		}
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
//		int[][][] direct = new int[count][count][];
//		int[][][] reverse = new int[count][count][];
		int[][] directTotal = calculate(graph, values, firstSize);
		for (int i = 0; i < count; i++)
			values[i] = -values[i];
		int[][] reverseTotal = calculate(graph, values, secondSize);
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < count - 1; i++) {
			if (directTotal[from[i]][to[i]] != Integer.MIN_VALUE && reverseTotal[to[i]][from[i]] != Integer.MIN_VALUE)
				answer = Math.max(answer, directTotal[from[i]][to[i]] + reverseTotal[to[i]][from[i]]);
			if (reverseTotal[from[i]][to[i]] != Integer.MIN_VALUE && directTotal[to[i]][from[i]] != Integer.MIN_VALUE)
				answer = Math.max(answer, reverseTotal[from[i]][to[i]] + directTotal[to[i]][from[i]]);
		}
		if (answer == Integer.MIN_VALUE) {
			out.printLine(-1);
			return;
		}
		out.printLine(answer);
    }

	private int[][] calculate(int[][] graph, int[] values, int size) {
		int count = graph.length;
		int[][][] result = new int[count][count][];
		int[][][] leftToRight = new int[count][][];
		int[][][] rightToLeft = new int[count][][];
		int[] skipped = new int[count];
		Arrays.fill(skipped, -1);
		int[][] total = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j : graph[i])
				calculate(i, j, graph, values, result, leftToRight, rightToLeft, skipped, total, size);
		}
		return total;
	}

	private void calculate(int vertex, int last, int[][] graph, int[] values, int[][][] result, int[][][] leftToRight, int[][][] rightToLeft, int[] skipped, int[][] total, int size) {
		if (result[vertex][last] != null)
			return;
		if (skipped[vertex] == -1) {
			leftToRight[vertex] = new int[graph[vertex].length][];
			rightToLeft[vertex] = new int[graph[vertex].length][];
			int j = 0;
			leftToRight[vertex][0] = new int[size];
			Arrays.fill(leftToRight[vertex][0], Integer.MIN_VALUE / 3);
			for (int i : graph[vertex]) {
				if (i != last) {
					calculate(i, vertex, graph, values, result, leftToRight, rightToLeft, skipped, total, size);
					leftToRight[vertex][j + 1] = unite(leftToRight[vertex][j], result[i][vertex]);
					j++;
				}
			}
			j = rightToLeft[vertex].length - 1;
			rightToLeft[vertex][j] = new int[size];
			Arrays.fill(rightToLeft[vertex][j], Integer.MIN_VALUE / 3);
			for (int ii = graph[vertex].length - 1; ii >= 0; ii--) {
				int i = graph[vertex][ii];
				if (i != last) {
					rightToLeft[vertex][j - 1] = unite(rightToLeft[vertex][j], result[i][vertex]);
					j--;
				}
			}
			result[vertex][last] = imbue(rightToLeft[vertex][0], values[vertex]);
			skipped[vertex] = last;
		} else {
			int j = 0;
			for (int i = 0; i < graph[vertex].length; i++) {
				if (graph[vertex][i] == last) {
					calculate(skipped[vertex], vertex, graph, values, result, leftToRight, rightToLeft, skipped, total, size);
					result[vertex][last] = imbue(unite(unite(leftToRight[vertex][j], rightToLeft[vertex][j + 1]), result[skipped[vertex]][vertex]), values[vertex]);
					break;
				}
				if (graph[vertex][i] != skipped[vertex])
					j++;
			}
		}
		total[vertex][last] = result[vertex][last][size - 1];
		for (int i : graph[vertex]) {
			if (i != last)
				total[vertex][last] = Math.max(total[vertex][last], total[i][vertex]);
		}
		if (total[vertex][last] < Integer.MIN_VALUE / 4)
			total[vertex][last] = Integer.MIN_VALUE;
	}

	private int[] imbue(int[] without, int value) {
		int[] result = new int[without.length];
		result[0] = value;
		for (int i = 1; i < without.length; i++)
			result[i] = without[i - 1] + value;
		return result;
	}

	private int[] unite(int[] left, int[] right) {
		int[] result = left.clone();
		for (int i = left.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++)
				result[i] = Math.max(result[i], left[j] + right[i - j - 1]);
			result[i] = Math.max(result[i], right[i]);
		}
		return result;
	}

}
