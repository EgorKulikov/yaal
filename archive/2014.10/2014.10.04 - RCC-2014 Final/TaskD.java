package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int[] degree = new int[count];
		for (int i : from) {
			degree[i]++;
		}
		for (int i : to) {
			degree[i]++;
		}
		int remaining = count;
		int present = -1;
		int[] vertices = new int[2];
		for (int i = 0; i < count; i++) {
			if (degree[i] == 2) {
				int id = 0;
				for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
					graph.removeEdge(j);
					graph.removeEdge(j ^ 1);
					vertices[id++] = graph.destination(j);
				}
				remaining--;
				graph.addSimpleEdge(vertices[0], vertices[1]);
			} else {
				present = i;
			}
		}
		if (remaining == 2) {
			out.printLine(1);
			out.printLine(present + 1);
			return;
		}
		IntList answer = new IntArrayList();
		for (int i = 0; i < count; i++) {
			boolean skipped = false;
			if (degree[i] <= 2) {
				continue;
			}
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				if (degree[graph.destination(j)] == 1) {
					if (skipped) {
						answer.add(graph.destination(j) + 1);
					} else {
						skipped = true;
					}
				}
			}
		}
		out.printLine(answer.size());
		out.printLine(answer);
    }
}
