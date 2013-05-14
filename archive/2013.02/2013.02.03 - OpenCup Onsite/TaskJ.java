package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	int size;
	int[][] graph;
	int[] result;
	int[] topSize;
	int[] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		size = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		for (int i = 1; i < count; i++) {
			from[i - 1] = in.readInt() - 1;
			to[i - 1] = i;
		}
		graph = GraphUtils.buildSimpleOrientedGraph(count, from, to);
		result = new int[count];
		topSize = new int[count];
		go(0);
		answer = new int[count];
		go2(0, -1, -1);
		for (int i = 0; i < count; i++)
			out.printLine(answer[i]);
    }

	private void go2(int vertex, int lastResult, int lastTopSize) {
		if (vertex == 0)
			answer[vertex] = result[vertex];
		else {
			answer[vertex] = Math.max(result[vertex], lastResult);
			if (lastResult == result[vertex] && topSize[vertex] + lastTopSize > size)
				answer[vertex]++;
			else if (lastResult > result[vertex] && lastTopSize == size)
				answer[vertex]++;
		}
		int firstMax = 1;
		int firstMaxAt = -1;
		int secondMax = 1;
		if (vertex != 0)
			firstMax = lastResult;
		for (int i : graph[vertex]) {
			if (result[i] > firstMax) {
				secondMax = firstMax;
				firstMax = result[i];
				firstMaxAt = i;
			} else if (result[i] > secondMax)
				secondMax = result[i];
		}
		int topSizeFirst = 1;
		int topSizeSecond = 1;
		if (firstMax == lastResult)
			topSizeFirst += lastTopSize;
		if (secondMax == lastResult)
			topSizeSecond += lastTopSize;
		for (int i : graph[vertex]) {
			if (result[i] == firstMax)
				topSizeFirst += topSize[i];
			if (result[i] == secondMax)
				topSizeSecond += topSize[i];
		}
		for (int i : graph[vertex]) {
			int curTopSize;
			int curResult;
			if (i == firstMaxAt) {
				curTopSize = topSizeSecond;
				curResult = secondMax;
			} else {
				curTopSize = topSizeFirst;
				curResult = firstMax;
			}
			if (result[i] == curResult)
				curTopSize -= topSize[i];
			if (curTopSize > size) {
				curTopSize = 1;
				curResult++;
			}
			go2(i, curResult, curTopSize);
		}
	}

	private void go(int vertex) {
		int max = 1;
		for (int i : graph[vertex]) {
			go(i);
			max = Math.max(max, result[i]);
		}
		topSize[vertex] = 1;
		for (int i : graph[vertex]) {
			if (result[i] == max)
				topSize[vertex] += topSize[i];
		}
		if (topSize[vertex] > size) {
			topSize[vertex] = 1;
			result[vertex] = max + 1;
		} else
			result[vertex] = max;
	}
}
