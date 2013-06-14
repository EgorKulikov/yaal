package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskJ {
	int[][] graph;
	int[] from;
	int[] to;
	double[] weight;
	double[] distance;
	Heap heap;
	boolean[] processed;
	int[] last;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		from = new int[edgeCount];
		to = new int[edgeCount];
		int[] speed = new int[edgeCount];
		int[] length = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, speed, length);
		MiscUtils.decreaseByOne(from, to);
		weight = new double[edgeCount];
		graph = GraphUtils.buildGraph(count, from, to);
		distance = new double[count];
		last = new int[count];
		heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return Double.compare(distance[first], distance[second]);
			}
		}, count);
		processed = new boolean[count];
		int time = in.readInt();
		double left = 0;
		double right = (long) 1.1e7;
		for (int i = 0; i < 45; i++) {
			double middle = (left + right) / 2;
			for (int j = 0; j < edgeCount; j++)
				weight[j] = length[j] / (speed[j] + middle);
			if (calculate().first <= time)
				right = middle;
			else
				left = middle;
		}
		double answer = right;
		for (int j = 0; j < edgeCount; j++)
			weight[j] = length[j] / (speed[j] + answer);
		int[] path = calculate().second;
		out.printLine(answer, path.length);
		out.printLine(path);
    }

	Pair<Double, int[]> calculate() {
		heap.clear();
		Arrays.fill(distance, Double.POSITIVE_INFINITY);
		distance[0] = 0;
		heap.add(0);
		Arrays.fill(processed, false);
		processed[0] = true;
		while (!heap.isEmpty()) {
			int vertex = heap.poll();
			for (int i : graph[vertex]) {
				int next = GraphUtils.otherVertex(vertex, from[i], to[i]);
				double newDistance = distance[vertex] + weight[i];
				if (!processed[next] && distance[next] > newDistance) {
					distance[next] = newDistance;
					last[next] = i;
					if (heap.getIndex(next) == -1)
						heap.add(next);
					else
						heap.shiftUp(heap.getIndex(next));
				}
			}
		}
		IntList path = new IntArrayList();
		int current = graph.length - 1;
		while (current != 0) {
			path.add(last[current] + 1);
			current = GraphUtils.otherVertex(current, from[last[current]], to[last[current]]);
		}
		path.inPlaceReverse();
		return Pair.makePair(distance[distance.length - 1], path.toArray());
	}
}
