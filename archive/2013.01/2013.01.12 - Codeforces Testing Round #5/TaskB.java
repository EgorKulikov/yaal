package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
	int[][] graph;
	int[] first, second;
	int[] degree;
	boolean[] taken;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		first = new int[count];
		second = new int[count];
		taken = new boolean[count];
		IOUtils.readIntArrays(in, first, second);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(7);
		for (int i = 0; i < count; i++)
			setSystem.join(first[i], second[i]);
		int type = setSystem.get(first[0]);
		for (int i = 1; i < count; i++) {
			if (type != setSystem.get(first[i])) {
				out.printLine("No solution");
				return;
			}
		}
		degree = new int[7];
		for (int i : first)
			degree[i]++;
		for (int i : second)
			degree[i]++;
		int total = 0;
		for (int i = 0; i <= 6; i++)
			total += degree[i] % 2;
		if (total > 2) {
			out.printLine("No solution");
			return;
		}
		int start = -1;
		int end = -1;
		for (int i = 0; i <= 6; i++) {
			if (degree[i] % 2 != 0) {
				if (start == -1)
					start = i;
				else
					end = i;
			}
		}
		if (start == -1)
			start = end = first[0];
		graph = GraphUtils.buildGraph(7, first, second);
		List<Edge> answer = findPath(start, end);
		while (answer.size() != count) {
			for (int i = 0; i <= 6; i++) {
				boolean found = i == end;
				for (Edge edge : answer) {
					if (edge.direction && second[edge.index] == i || !edge.direction && first[edge.index] == i) {
						found = true;
						break;
					}
				}
				if (!found)
					continue;
				while (degree[i] != 0) {
					List<Edge> path = findPath(i, i);
					int addAt = -1;
					if (i == end)
						addAt = 0;
					else {
						for (int j = 0; j < answer.size(); j++) {
							Edge edge = answer.get(j);
							if (edge.direction && second[edge.index] == i || !edge.direction && first[edge.index] == i) {
								addAt = j + 1;
								break;
							}
						}
					}
					answer.addAll(addAt, path);
				}
			}
		}
		for (Edge edge : answer)
			out.printLine(edge.index + 1, edge.direction ? '+' : '-');
	}

	private List<Edge> findPath(int start, int end) {
		for (int i : graph[start]) {
			if (taken[i])
				continue;
			taken[i] = true;
			int other = GraphUtils.otherVertex(start, first[i], second[i]);
			List<Edge> result;
			if (other == end)
				result = new ArrayList<Edge>();
			else
				result = findPath(other, end);
			result.add(new Edge(i, second[i] == start));
			degree[first[i]]--;
			degree[second[i]]--;
			return result;
		}
		throw new RuntimeException();
	}

	static class Edge {
		final int index;
		final boolean direction;

		Edge(int index, boolean direction) {
			this.index = index;
			this.direction = direction;
		}
	}
}
