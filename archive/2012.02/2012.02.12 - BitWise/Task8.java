package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task8 {
	private int[][] graph;
	private int[][] reverse;
	private int[][] size;
	private int[] doneSize;
	private int[] sumSize;
	private long[] sumValue;
	private int[] doneValue;
	private long[][] value;
	private long[][] answer;
	private int[] doneAnswer;
	private long[] max;
	private int[] maxIndex;
	private long[] secondMax;
	private int[] secondMaxIndex;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		graph = GraphUtils.buildSimpleGraph(count, from, to);
		for (int[] vertex : graph)
			Arrays.sort(vertex);
		reverse = new int[count][];
		for (int i = 0; i < count; i++) {
			reverse[i] = new int[graph[i].length];
			for (int j = 0; j < graph[i].length; j++)
				reverse[i][j] = Arrays.binarySearch(graph[graph[i][j]], i);
		}
		size = new int[count][];
		doneSize = new int[count];
		Arrays.fill(doneSize, -1);
		sumSize = new int[count];
		for (int i = 0; i < count; i++)
			size[i] = new int[graph[i].length];
		ArrayUtils.fill(size, -1);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (size[i][j] == -1)
					size[i][j] = getSize(graph[i][j], reverse[i][j]);
			}
		}
		value = new long[count][];
		doneValue = new int[count];
		sumValue = new long[count];
		Arrays.fill(doneValue, -1);
		for (int i = 0; i < count; i++)
			value[i] = new long[graph[i].length];
		ArrayUtils.fill(value, -1);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (value[i][j] == -1)
					value[i][j] = getValue(graph[i][j], reverse[i][j]) + size[i][j];
			}
		}
		answer = new long[count][];
		doneAnswer = new int[count];
		max = new long[count];
		maxIndex = new int[count];
		secondMax = new long[count];
		secondMaxIndex = new int[count];
		Arrays.fill(doneAnswer, -1);
		Arrays.fill(maxIndex, -1);
		Arrays.fill(secondMaxIndex, -1);
		long totalAnswer = Long.MAX_VALUE;
		for (int i = 0; i < count; i++)
			answer[i] = new long[graph[i].length];
		ArrayUtils.fill(answer, -1);
		for (int i = 0; i < count; i++) {
			if (graph[i].length == 1) {
				if (answer[i][0] == -1)
					answer[i][0] = getAnswer(graph[i][0], reverse[i][0]);
				totalAnswer = Math.min(totalAnswer, answer[i][0]);
			}
		}
		out.printLine(totalAnswer);
	}

	private long getAnswer(int vertex, int backEdge) {
		if (graph[vertex].length == 1)
			return 0;
		if (doneAnswer[vertex] == -2) {
			if (backEdge != maxIndex[vertex])
				return sumValue[vertex] - value[vertex][backEdge] - max[vertex];
			else
				return sumValue[vertex] - value[vertex][backEdge] - secondMax[vertex];
		} else if (doneAnswer[vertex] == backEdge)
			return sumValue[vertex] - value[vertex][backEdge] - max[vertex];
		else if (doneAnswer[vertex] != -1) {
			answer[vertex][doneAnswer[vertex]] = getAnswer(graph[vertex][doneAnswer[vertex]], reverse[vertex][doneAnswer[vertex]]);
			augment(vertex, doneAnswer[vertex]);
			doneAnswer[vertex] = -2;
			if (backEdge != maxIndex[vertex])
				return sumValue[vertex] - value[vertex][backEdge] - max[vertex];
			else
				return sumValue[vertex] - value[vertex][backEdge] - secondMax[vertex];
		} else {
			for (int i = 0; i < graph[vertex].length; i++) {
				if (i != backEdge) {
					answer[vertex][i] = getAnswer(graph[vertex][i], reverse[vertex][i]);
					augment(vertex, i);
				}
			}
			doneAnswer[vertex] = backEdge;
			return sumValue[vertex] - value[vertex][backEdge] - max[vertex];
		}
	}

	private void augment(int vertex, int edge) {
		long delta = value[vertex][edge] - answer[vertex][edge];
		if (max[vertex] <= delta) {
			secondMax[vertex] = max[vertex];
			secondMaxIndex[vertex] = maxIndex[vertex];
			max[vertex] = delta;
			maxIndex[vertex] = edge;
		} else if (secondMax[vertex] <= delta) {
			secondMax[vertex] = delta;
			secondMaxIndex[vertex] = edge;
		}
	}

	private int getSize(int vertex, int backEdge) {
		if (doneSize[vertex] == -2) {
			return sumSize[vertex] - size[vertex][backEdge] + 1;
		} else if (doneSize[vertex] == backEdge)
			return sumSize[vertex] + 1;
		else if (doneSize[vertex] != -1) {
			sumSize[vertex] += size[vertex][doneSize[vertex]] = getSize(graph[vertex][doneSize[vertex]], reverse[vertex][doneSize[vertex]]);
			doneSize[vertex] = -2;
			return sumSize[vertex] - size[vertex][backEdge] + 1;
		} else {
			for (int i = 0; i < graph[vertex].length; i++) {
				if (i != backEdge)
					sumSize[vertex] += size[vertex][i] = getSize(graph[vertex][i], reverse[vertex][i]);
			}
			doneSize[vertex] = backEdge;
			return sumSize[vertex] + 1;
		}
	}

	private long getValue(int vertex, int backEdge) {
		if (doneValue[vertex] == -2) {
			return sumValue[vertex] - value[vertex][backEdge];
		} else if (doneValue[vertex] == backEdge)
			return sumValue[vertex];
		else if (doneValue[vertex] != -1) {
			sumValue[vertex] += value[vertex][doneValue[vertex]] = getValue(graph[vertex][doneValue[vertex]], reverse[vertex][doneValue[vertex]]) +
				size[vertex][doneValue[vertex]];
			doneValue[vertex] = -2;
			return sumValue[vertex] - value[vertex][backEdge];
		} else {
			for (int i = 0; i < graph[vertex].length; i++) {
				if (i != backEdge)
					sumValue[vertex] += value[vertex][i] = getValue(graph[vertex][i], reverse[vertex][i]) + size[vertex][i];
			}
			doneValue[vertex] = backEdge;
			return sumValue[vertex];
		}
	}
}
