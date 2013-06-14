package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] capacity = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, capacity);
		MiscUtils.decreaseByOne(from, to);
		int[] outgoing = new int[count];
		int[] incoming = new int[count];
		int[] answer = new int[edgeCount];
		Arrays.fill(answer, -1);
		for (int i = 0; i < edgeCount; i++) {
			outgoing[from[i]] += capacity[i];
			outgoing[to[i]] += capacity[i];
		}
		int[][] graph = GraphUtils.buildGraph(count, from, to);
		int[] queue = new int[count];
		int size = 1;
		for (int i = 0; i < size; i++) {
			int vertex = queue[i];
			for (int j : graph[vertex]) {
				if (answer[j] != -1)
					continue;
				if (from[j] == vertex)
					answer[j] = 0;
				else
					answer[j] = 1;
				int other = GraphUtils.otherVertex(vertex, from[j], to[j]);
				outgoing[other] -= capacity[j];
				incoming[other] += capacity[j];
				if (other != count - 1 && incoming[other] == outgoing[other])
					queue[size++] = other;
			}
		}
		for (int i : answer)
			out.printLine(i);
	}
}
