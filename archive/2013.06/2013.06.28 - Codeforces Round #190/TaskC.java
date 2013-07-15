package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		char[] answer = new char[count];
		if (paint(graph, answer, 0, -1)) {
			StringBuilder output = new StringBuilder(2 * count - 1);
			output.append(answer[0]);
			for (int i = 1; i < count; i++)
				output.append(' ').append(answer[i]);
			out.printLine(output);
		} else
			out.printLine("Impossible!");
    }

	private boolean paint(Graph graph, char[] answer, int vertex, int last) {
		if (last == vertex)
			return false;
		int bad = -1;
		for (Edge edge : graph.outbound(vertex)) {
			int next = edge.getDestination();
			if (!go(graph, answer, next, vertex, 'B', 0)) {
				if (bad != -1)
					return false;
				bad = next;
			}
		}
		if (bad == -1) {
			answer[vertex] = 'A';
			return true;
		}
		return paint(graph, answer, bad, vertex);
	}

	private boolean go(Graph graph, char[] answer, int vertex, int last, char mark, int specialDepth) {
		answer[vertex] = mark;
		int degree = 0;
		for (Edge edge : graph.outbound(vertex)) {
			int next = edge.getDestination();
			if (next != last)
				degree++;
		}
		if (degree == 1) {
			specialDepth++;
			answer[vertex] = (char) ('Z' - Integer.bitCount(Integer.lowestOneBit(specialDepth) - 1));
			for (Edge edge : graph.outbound(vertex)) {
				int next = edge.getDestination();
				if (next != last)
					return go(graph, answer, next, vertex, mark, specialDepth);
			}
		} else {
			for (Edge edge : graph.outbound(vertex)) {
				int next = edge.getDestination();
				if (next != last)
					go(graph, answer, next, vertex, (char) (mark + 1), 0);
			}
		}
		return true;
	}
}
