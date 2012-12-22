package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Kingdom {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		if (size == 2) {
			out.printLine(1);
			out.printLine(1, 2);
			return;
		}
		int[] from = new int[size - 1];
		int[] to = new int[size - 1];
		for (int i = 0; i < size - 1; i++) {
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
		}
		int[] degree = new int[size];
		for (int i = 0; i < size - 1; i++) {
			degree[from[i]]++;
			degree[to[i]]++;
		}
		int[][] graph = new int[size][];
		for (int i = 0; i < size; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < size - 1; i++) {
			graph[from[i]][--degree[from[i]]] = to[i];
			graph[to[i]][--degree[to[i]]] = from[i];
		}
		int start = -1;
		for (int i = 0; i < size; i++) {
			if (graph[i].length != 1) {
				start = i;
				break;
			}
		}
		if (start == -1)
			throw new RuntimeException();
		int[] singeCount = new int[size];
		int totalSingles = go(start, -1, singeCount, graph);
		int maxIndex;
		while (true) {
			int sum = 0;
			int max = 0;
			maxIndex = -1;
			for (int i : graph[start]) {
				sum += singeCount[i];
				if (singeCount[i] > max) {
					max = singeCount[i];
					maxIndex = i;
				}
			}
			if (sum + 1 >= 2 * max)
				break;
			singeCount[start] = sum - max;
			start = maxIndex;
		}
		final int[] color = new int[size];
		for (int i : graph[start])
			color(i, start, i, graph, color);
		final int[] perColor = new int[size];
		for (int i = 0; i < size; i++) {
			if (graph[i].length == 1)
				perColor[color[i]]++;
		}
		final int[][] perColorList = new int[size][];
		for (int i = 0; i < size; i++)
			perColorList[i] = new int[perColor[i]];
		for (int i = 0; i < size; i++) {
			if (graph[i].length == 1) {
				perColorList[color[i]][--perColor[color[i]]] = i;
			}
		}
		Queue<Integer> queue = new PriorityQueue<Integer>(graph[start].length, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return -(perColorList[o1].length - perColor[o1]) + (perColorList[o2].length - perColor[o2]);
			}
		});
		out.printLine((totalSingles + 1) / 2);
		for (int i = 0; i < size; i++) {
			if (perColorList[i].length != 0)
				queue.add(i);
		}
		while (queue.size() > 1) {
			int first = queue.poll();
			int second = queue.poll();
			out.printLine(perColorList[first][perColor[first]++] + 1, perColorList[second][perColor[second]++] + 1);
			if (perColor[first] != perColorList[first].length)
				queue.add(first);
			if (perColor[second] != perColorList[second].length)
				queue.add(second);
		}
		if (queue.size() == 1) {
			int first = queue.poll();
			out.printLine(perColorList[first][perColor[first]++] + 1, start + 1);
		}
	}

	private void color(int vertex, int last, int currentColor, int[][] graph, int[] color) {
		color[vertex] = currentColor;
		for (int i : graph[vertex]) {
			if (i != last)
				color(i, vertex, currentColor, graph, color);
		}
	}

	private int go(int vertex, int last, int[] singeCount, int[][] graph) {
		if (graph[vertex].length == 1) {
			singeCount[vertex] = 1;
			return 1;
		}
		for (int i : graph[vertex]) {
			if (i != last)
				singeCount[vertex] += go(i, vertex, singeCount, graph);
		}
		return singeCount[vertex];
	}
}
