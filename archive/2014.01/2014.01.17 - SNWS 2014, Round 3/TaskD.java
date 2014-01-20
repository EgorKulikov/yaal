package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int start = in.readInt() - 1;
		int destination = in.readInt() - 1;
		int[] cheese = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] weight = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, weight);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createWeightedGraph(count, from, to, ArrayUtils.asLong(weight));
		final long[] distance = new long[count];
		Arrays.fill(distance, -1);
		distance[destination] = Integer.MAX_VALUE;
		Heap heap = new Heap(count, new IntComparator() {
			public int compare(int first, int second) {
				return -IntegerUtils.longCompare(distance[first], distance[second]);
			}
		}, count);
		heap.add(destination);
		while (!heap.isEmpty()) {
			int current = heap.poll();
			int id = graph.firstOutbound(current);
			while (id != -1) {
				int next = graph.destination(id);
				long total = Math.min(graph.weight(id), distance[current]) - cheese[next];
				if (distance[next] < total) {
					distance[next] = total;
					if (heap.getIndex(next) == -1)
						heap.add(next);
					else
						heap.shiftUp(heap.getIndex(next));
				}
				id = graph.nextOutbound(id);
			}
		}
		out.printLine(distance[start]);
	}
}
