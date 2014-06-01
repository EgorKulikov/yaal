package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] cost = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		boolean[] removed = new boolean[count];
		int[] order = ArrayUtils.order(cost);
		ArrayUtils.reverse(order);
		int answer = 0;
		for (int i : order) {
			removed[i] = true;
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				int destination = graph.destination(j);
				if (!removed[destination])
					answer += cost[destination];
			}
		}
		out.printLine(answer);
    }
}
