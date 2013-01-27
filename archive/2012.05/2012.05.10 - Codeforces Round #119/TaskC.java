package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int goodCount = in.readInt();
		int[] good = IOUtils.readIntArray(in, goodCount);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(good, from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		int start = in.readInt() - 1;
		int end = in.readInt() - 1;
		final int[] distance = new int[count];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		boolean[] isGood = new boolean[count];
		for (int i : good)
			isGood[i] = true;
		int[] processed = new int[count];
		Arrays.fill(processed, Integer.MAX_VALUE);
		Queue<Pair<Integer, Integer>> queue = new PriorityQueue<Pair<Integer, Integer>>();
		queue.add(Pair.makePair(0, start));
		int answer = 0;
		while (!queue.isEmpty()) {
			int vertex = queue.poll().second;
			if (processed[vertex] == distance[vertex])
				continue;
			processed[vertex] = distance[vertex];
			if (vertex == end) {
				out.printLine(answer);
				return;
			}
			for (int next : graph[vertex]) {
				if (distance[next] > distance[vertex] + 1) {
					distance[next] = distance[vertex] + 1;
					answer = Math.max(distance[next], answer);
					if (next == end) {
						out.printLine(answer);
						return;
					}
					if (isGood[next])
						distance[next] = 0;
					queue.add(Pair.makePair(distance[next], next));
				}
			}
		}
		out.printLine(-1);
	}
}
