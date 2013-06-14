package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		if (edgeCount > count) {
			out.printLine("unknown topology");
			return;
		}
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		if (count == edgeCount) {
			for (int i = 0; i < count; i++) {
				if (graph[i].length != 2) {
					out.printLine("unknown topology");
					return;
				}
			}
			out.printLine("ring topology");
			return;
		}
		int ones = 0;
		int twos = 0;
		for (int i = 0; i < count; i++) {
			if (graph[i].length == 1)
				ones++;
			else if (graph[i].length == 2)
				twos++;
		}
		if (ones == count - 1) {
			out.printLine("star topology");
			return;
		}
		if (ones == 2 && twos == count - 2) {
			out.printLine("bus topology");
			return;
		}
		out.printLine("unknown topology");
    }
}
