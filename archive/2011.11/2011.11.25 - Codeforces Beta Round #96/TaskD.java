package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Queue;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count];
		int[] to = new int[count];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		boolean[] cycle = new boolean[count];
		boolean[] visited = new boolean[count];
		if (go(graph, cycle, visited, 0, -1) != -2)
			throw new RuntimeException();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int[] result = new int[count];
		for (int i = 0; i < count; i++) {
			if (cycle[i])
				queue.add(i);
		}
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			for (int i : graph[vertex]) {
				if (!cycle[i]) {
					cycle[i] = true;
					queue.add(i);
					result[i] = result[vertex] + 1;
				}
			}
		}
		out.printLine(Array.wrap(result).toArray());
	}

	private int go(int[][] graph, boolean[] cycle, boolean[] visited, int vertex, int last) {
		if (visited[vertex])
			return vertex;
		visited[vertex] = true;
		for (int i : graph[vertex]) {
			if (i != last) {
				int result = go(graph, cycle, visited, i, vertex);
				if (result >= 0) {
					cycle[vertex] = true;
					if (result == vertex)
						return -2;
					else
						return result;
				} else if (result == -2)
					return -2;
			}
		}
		return -1;
	}
}
