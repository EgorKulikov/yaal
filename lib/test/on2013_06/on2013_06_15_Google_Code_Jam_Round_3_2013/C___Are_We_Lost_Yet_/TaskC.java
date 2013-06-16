package on2013_06.on2013_06_15_Google_Code_Jam_Round_3_2013.C___Are_We_Lost_Yet_;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int length = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] min = new int[edgeCount];
		int[] max = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, min, max);
		int[] path = IOUtils.readIntArray(in, length);
		MiscUtils.decreaseByOne(from, to, path);
		Graph graph = Graph.createGraph(count, from, to);
		int distance = 0;
		final int[] longest = new int[count];
		final int[] shortest = new int[count];
		Heap longHeap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(longest[first], longest[second]);
			}
		}, count);
		Heap shortHeap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(shortest[first], shortest[second]);
			}
		}, count);
		for (int i : path) {
			graph.removeEdge(i);
			distance += min[i];
			Arrays.fill(longest, Integer.MAX_VALUE);
			longest[0] = 0;
			longHeap.add(0);
			while (!longHeap.isEmpty()) {
				int vertex = longHeap.poll();
				int id = graph.firstOutbound(vertex);
				while (id != -1) {
					int next = graph.destination(id);
					int dist = longest[vertex] + max[id];
					if (longest[next] > dist) {
						longest[next] = dist;
						int index = longHeap.getIndex(next);
						if (index == -1)
							longHeap.add(next);
						else
							longHeap.shiftUp(index);
					}
					id = graph.nextOutbound(id);
				}
			}
			if (distance > longest[to[i]]) {
				out.printLine("Case #" + testNumber + ":", i + 1);
				return;
			}
			Arrays.fill(shortest, Integer.MAX_VALUE);
			shortest[to[i]] = distance;
			shortHeap.add(to[i]);
			while (!shortHeap.isEmpty()) {
				int vertex = shortHeap.poll();
				int id = graph.firstOutbound(vertex);
				while (id != -1) {
					int next = graph.destination(id);
					int dist = shortest[vertex] + min[id];
					if (shortest[next] > dist && longest[next] >= dist) {
						shortest[next] = dist;
						int index = shortHeap.getIndex(next);
						if (index == -1)
							shortHeap.add(next);
						else
							shortHeap.shiftUp(index);
					}
					id = graph.nextOutbound(id);
				}
			}
			if (longest[1] < shortest[1]) {
				out.printLine("Case #" + testNumber + ":", i + 1);
				return;
			}
			max[i] = min[i];
			graph.restoreEdge(i);
		}
		out.printLine("Case #" + testNumber + ": Looks Good To Me");
    }
}
