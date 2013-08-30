package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int entranceCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] entrances = IOUtils.readIntArray(in, entranceCount);
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to, entrances);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		for (int i = 0; i < count; i++) {
			int closedCount = in.readInt();
			int[] closed = IOUtils.readIntArray(in, closedCount);
			MiscUtils.decreaseByOne(closed);
			for (int j : closed) {
				if (i == from[j])
					graph.removeEdge(2 * j + 1);
				else
					graph.removeEdge(2 * j);
			}
		}
		int[] queue = Arrays.copyOf(entrances, count);
		int size = entranceCount;
		boolean[] occupied = new boolean[count];
		for (int i : entrances)
			occupied[i] = true;
		for (int i = 0; i < size; i++) {
			for (int j = graph.firstOutbound(queue[i]); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				if (!occupied[next]) {
					occupied[next] = true;
					queue[size++] = next;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			if (!occupied[i]) {
				out.printLine(i + 1);
				return;
			}
		}
		out.printLine("Impossible");
    }
}
