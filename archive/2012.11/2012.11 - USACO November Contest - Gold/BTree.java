package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BTree {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] parent = IOUtils.readIntArray(in, count - 1);
		int[] vertex = new int[count - 1];
		for (int i = 0; i < vertex.length; i++)
			vertex[i] = i + 1;
		MiscUtils.decreaseByOne(parent);
		char[] type = new char[count];
		for (int i = 0; i < count; i++)
			type[i] = in.readCharacter();
		int[][] graph = GraphUtils.buildSimpleGraph(count, parent, vertex);
		int[][] maxOpen = new int[count][];
		int[][] maxClose = new int[count][];
		for (int i = 0; i < count; i++) {
			maxOpen[i] = new int[graph[i].length];
			maxClose[i] = new int[graph[i].length];
		}
		int[] totalOpen = new int[count];
		int[] totalClose = new int[count];
		goFirst(0, -1, maxOpen, maxClose, graph, type, totalOpen, totalClose);
		out.printLine(goSecond(0, -1, maxOpen, maxClose, graph, type, 0, 0));
	}

	private int goSecond(int vertex, int last, int[][] maxOpen, int[][] maxClose, int[][] graph, char[] type, int curMaxOpen, int curMaxClose) {
		curMaxOpen = Math.max(0, curMaxOpen + (type[vertex] == '(' ? 1 : -1));
		for (int i = 0; i < graph[vertex].length; i++) {
			int next = graph[vertex][i];
			if (next == last) {
				maxOpen[vertex][i] = curMaxOpen;
				maxClose[vertex][i] = curMaxClose;
			}
		}
		int openMaxPosition = -1;
		int openSecond = 0;
		int closeMaxPosition = -1;
		int closeSecond = 0;
		for (int i = 0; i < graph[vertex].length; i++) {
			int next = graph[vertex][i];
			if (next != last) {
				if (maxOpen[vertex][i] > curMaxOpen) {
					openSecond = curMaxOpen;
					curMaxOpen = maxOpen[vertex][i];
					openMaxPosition = i;
				} else
					openSecond = Math.max(openSecond, maxOpen[vertex][i]);
				if (maxClose[vertex][i] > curMaxClose) {
					closeSecond = curMaxClose;
					curMaxClose = maxClose[vertex][i];
					closeMaxPosition = i;
				} else
					closeSecond = Math.max(closeSecond, maxClose[vertex][i]);
			}
		}
		int result;
		if (openMaxPosition == closeMaxPosition)
			result = Math.max(Math.min(curMaxOpen, closeSecond), Math.min(curMaxClose, openSecond));
		else
			result = Math.min(curMaxOpen, curMaxClose);
		curMaxClose = Math.max(0, curMaxClose + (type[vertex] == ')' ? 1 : -1));
		closeSecond = Math.max(0, closeSecond + (type[vertex] == ')' ? 1 : -1));
		for (int i = 0; i < graph[vertex].length; i++) {
			int next = graph[vertex][i];
			if (next != last)
				result = Math.max(result, goSecond(next, vertex, maxOpen, maxClose, graph, type, i == openMaxPosition ? openSecond : curMaxOpen, i == closeMaxPosition ? closeSecond : curMaxClose));
		}
		return result;
	}

	private void goFirst(int vertex, int last, int[][] maxOpen, int[][] maxClose, int[][] graph, char[] type, int[] totalOpen, int[] totalClose) {
		for (int i = 0; i < graph[vertex].length; i++) {
			int next = graph[vertex][i];
			if (next != last) {
				goFirst(next, vertex, maxOpen, maxClose, graph, type, totalOpen, totalClose);
				maxOpen[vertex][i] = Math.max(0, totalOpen[next] + (type[vertex] == '(' ? 1 : -1));
				maxClose[vertex][i] = totalClose[next];
				totalOpen[vertex] = Math.max(totalOpen[vertex], maxOpen[vertex][i]);
				totalClose[vertex] = Math.max(totalClose[vertex], maxClose[vertex][i]);
			}
		}
		totalClose[vertex] = Math.max(0, totalClose[vertex] + (type[vertex] == ')' ? 1 : -1));
	}
}
