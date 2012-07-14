package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int first = in.readInt() - 1;
		int second = in.readInt() - 1;
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		int[] capacity = new int[count - 1];
		IOUtils.readIntArrays(in, from, to, capacity);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildGraph(count, from, to);
		int[] last = new int[count];
		int[] queue = new int[count];
		queue[0] = first;
		int size = 1;
		boolean[] processed = new boolean[count];
		processed[first] = true;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int j : graph[current]) {
				int other = GraphUtils.otherVertex(current, from[j], to[j]);
				if (!processed[other]) {
					processed[other] = true;
					last[other] = j;
					queue[size++] = other;
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		int current = second;
		while (current != first) {
			answer = Math.min(answer, capacity[last[current]]);
			current = GraphUtils.otherVertex(current, from[last[current]], to[last[current]]);
		}
		current = second;
		while (current != first) {
			capacity[last[current]] -= answer;
			current = GraphUtils.otherVertex(current, from[last[current]], to[last[current]]);
		}
		int firstMax = 0;
		for (int i : graph[first])
			firstMax = Math.max(firstMax, capacity[i]);
		int secondMax = 0;
		for (int i : graph[second])
			secondMax = Math.max(secondMax, capacity[i]);
		answer += Math.min(firstMax, secondMax);
		out.printLine(answer);
	}
}
