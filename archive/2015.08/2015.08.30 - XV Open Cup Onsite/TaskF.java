package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	int[] diameter;
	int[] fromRoot;

	int n;
	Graph graph;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		int[] a = new int[n - 1];
		int[] b = new int[n - 1];
		IOUtils.readIntArrays(in, a, b);
		MiscUtils.decreaseByOne(a, b);
		graph = BidirectionalGraph.createGraph(n, a, b);
		diameter = new int[2 * n - 2];
		fromRoot = new int[2 * n - 2];
		calculateDirect(0, -1);
		calculateOther(0, -1, 0, 0);

		int bestShortest = Integer.MAX_VALUE;
		int edgeShortest = -1;
		int bestLongest = -1;
		int edgeLongest = -1;
		for (int i = 0; i < 2 * n - 2; i += 2) {
			int curLongest = diameter[i] + diameter[i + 1] + 1;
			if (curLongest > bestLongest) {
				bestLongest = curLongest;
				edgeLongest = i;
			}
			int curShortest = ((diameter[i] + 1) >> 1) + ((diameter[i + 1] + 1) >> 1) + 1;
			curShortest = Math.max(curShortest, diameter[i]);
			curShortest = Math.max(curShortest, diameter[i + 1]);
			if (curShortest < bestShortest) {
				bestShortest = curShortest;
				edgeShortest = i;
			}
		}
		edgeShortest /= 2;
		edgeLongest /= 2;
		Graph shGraph = new BidirectionalGraph(n);
		for (int i = 0; i < n - 1; i++) {
			if (i != edgeShortest) {
				shGraph.addSimpleEdge(a[i], b[i]);
			}
		}
		int left = findCenter(shGraph, a[edgeShortest]);
		int right = findCenter(shGraph, b[edgeShortest]);
		shGraph.addSimpleEdge(left, right);
		out.printLine(/*ArrayUtils.maxElement(calcDistance(shGraph, findEnd(shGraph, 0)))*/ bestShortest, a[edgeShortest] + 1, b[edgeShortest] + 1, left + 1, right + 1);
		Graph longGraph = new BidirectionalGraph(n);
		for (int i = 0; i < n - 1; i++) {
			if (i != edgeLongest) {
				longGraph.addSimpleEdge(a[i], b[i]);
			}
		}
		left = findEnd(longGraph, a[edgeLongest]);
		right = findEnd(longGraph, b[edgeLongest]);
		out.printLine(bestLongest, a[edgeLongest] + 1, b[edgeLongest] + 1, left + 1, right + 1);
//		out.printLine();
	}

	private int findCenter(Graph graph, int vertex) {
		vertex = findEnd(graph, vertex);
		int other = findEnd(graph, vertex);
		int[] d1 = calcDistance(graph, vertex);
		int[] d2 = calcDistance(graph, other);
		int best = Integer.MAX_VALUE;
		int at = -1;
		for (int i = 0; i < n; i++) {
			if (d1[i] == Integer.MAX_VALUE) {
				continue;
			}
			if (Math.abs(d1[i] - d2[i]) > 1) {
				continue;
			}
			if (best > d1[i] + d2[i]) {
				best = d1[i] + d2[i];
				at = i;
			}
		}
		return at;
	}

	private int[] calcDistance(Graph graph, int vertex) {
		int[] distance = ArrayUtils.createArray(n, Integer.MAX_VALUE);
		int[] queue = new int[n];
		queue[0] = vertex;
		distance[vertex] = 0;
		int size = 1;
		for (int i = 0; i < size; i++) {
			for (int j = graph.firstOutbound(queue[i]); j != -1; j = graph.nextOutbound(j)) {
				int vert = graph.destination(j);
				if (distance[vert] == Integer.MAX_VALUE) {
					distance[vert] = distance[queue[i]] + 1;
					queue[size++] = vert;
				}
			}
		}
		return distance;
	}

	private int findEnd(Graph graph, int vertex) {
		int[] distance = calcDistance(graph, vertex);
		int at = -1;
		int best = -1;
		for (int i = 0; i < n; i++) {
			if (distance[i] > best && distance[i] != Integer.MAX_VALUE) {
				best = distance[i];
				at = i;
			}
		}
		return at;
	}

	private void calculateOther(int vertex, int last, int topDiameter, int topFromRoot) {
		int bestDiameter = topDiameter;
		int secondDiameter = 0;
		int bestFromRoot = topFromRoot;
		int secondFromRoot = 0;
		int thirdFromRoot = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (graph.destination(i) == last) {
				continue;
			}
			if (diameter[i] > bestDiameter) {
				secondDiameter = bestDiameter;
				bestDiameter = diameter[i];
			} else {
				secondDiameter = Math.max(secondDiameter, diameter[i]);
			}
			if (fromRoot[i] >= bestFromRoot) {
				thirdFromRoot = secondFromRoot;
				secondFromRoot = bestFromRoot;
				bestFromRoot = fromRoot[i] + 1;
			} else if (fromRoot[i] >= secondFromRoot) {
				thirdFromRoot = secondFromRoot;
				secondFromRoot = fromRoot[i] + 1;
			} else {
				thirdFromRoot = Math.max(thirdFromRoot, fromRoot[i] + 1);
			}
		}
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (graph.destination(i) == last) {
				continue;
			}
			int curDiameter = 0;
			if (bestDiameter == diameter[i]) {
				curDiameter = Math.max(curDiameter, secondDiameter);
			} else {
				curDiameter = Math.max(curDiameter, bestDiameter);
			}
			if (bestFromRoot == fromRoot[i] + 1) {
				curDiameter = Math.max(curDiameter, secondFromRoot + thirdFromRoot);
			} else if (secondFromRoot == fromRoot[i] + 1) {
				curDiameter = Math.max(curDiameter, bestFromRoot + thirdFromRoot);
			} else {
				curDiameter = Math.max(curDiameter, bestFromRoot + secondFromRoot);
			}
			int curFromRoot;
			if (bestFromRoot == fromRoot[i] + 1) {
				curFromRoot = secondFromRoot;
			} else {
				curFromRoot = bestFromRoot;
			}
			diameter[i ^ 1] = curDiameter;
//			fromRoot[i ^ 1] = curFromRoot;
			calculateOther(graph.destination(i), vertex, curDiameter, curFromRoot + 1);
		}
	}

	private IntPair calculateDirect(int vertex, int last) {
		int bestDiameter = 0;
		int bestDown = 0;
		int secondDown = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (graph.destination(i) == last) {
				continue;
			}
			IntPair result = calculateDirect(graph.destination(i), vertex);
			diameter[i] = result.first;
			fromRoot[i] = result.second;
			bestDiameter = Math.max(bestDiameter, result.first);
			if (result.second >= bestDown) {
				secondDown = bestDown;
				bestDown = result.second + 1;
			} else {
				secondDown = Math.max(secondDown, result.second + 1);
			}
		}
		bestDiameter = Math.max(bestDiameter, bestDown + secondDown);
		return new IntPair(bestDiameter, bestDown);
	}
}
