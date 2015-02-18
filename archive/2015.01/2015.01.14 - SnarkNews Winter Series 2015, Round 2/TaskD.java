package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int start = in.readInt() - 1;
		int end = in.readInt() - 1;
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] length = new int[edgeCount];
		int[] cost = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, length, cost);
		MiscUtils.decreaseByOne(from, to);
		Graph direct = Graph.createWeightedGraph(count, from, to, ArrayUtils.asLong(length));
		long[] directDistances = ShortestDistance.dijkstraAlgorithm(direct, start).first;
		Graph reverse = Graph.createWeightedGraph(count, to, from, ArrayUtils.asLong(length));
		long[] reverseDistances = ShortestDistance.dijkstraAlgorithm(reverse, end).first;
		int[] minLength = new int[edgeCount];
		for (int i = 0; i < edgeCount; i++) {
			if (directDistances[from[i]] == Long.MAX_VALUE || reverseDistances[to[i]] == Long.MAX_VALUE) minLength[i] = Integer.MAX_VALUE;
			else minLength[i] = (int) (directDistances[from[i]] + reverseDistances[to[i]] + length[i]);
		}
		int[] uniqueLengths = minLength.clone();
		ArrayUtils.sort(uniqueLengths, IntComparator.DEFAULT);
		ArrayUtils.unique(uniqueLengths);
		int[] totalCost = new int[uniqueLengths.length];
		for (int i = 0; i < edgeCount; i++) totalCost[Arrays.binarySearch(uniqueLengths, minLength[i])] += cost[i];
		for (int i = 1; i < uniqueLengths.length; i++) totalCost[i] += totalCost[i - 1];
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int maxLength = in.readInt();
			int at = Arrays.binarySearch(uniqueLengths, maxLength);
			if (at < 0) at = -at - 2;
			if (at == -1) out.printLine(0);
			else out.printLine(totalCost[at]);
		}
	}
}
