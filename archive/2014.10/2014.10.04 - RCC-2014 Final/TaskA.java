package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		int hints = in.readInt();
		int[][] known = new int[first][(hints + 31) >> 5];
		for (int i = 0; i < first; i++) {
			int count = in.readInt();
			for (int j = 0; j < count; j++) {
				int current = in.readInt() - 1;
				known[i][current >> 5] |= 1 << (current & 31);
			}
		}
		int[][] other = new int[second][(hints + 31) >> 5];
		for (int i = 0; i < second; i++) {
			int count = in.readInt();
			for (int j = 0; j < count; j++) {
				int current = in.readInt() - 1;
				other[i][current >> 5] |= 1 << (current & 31);
			}
		}
		Graph graph = new Graph(first + hints + 2);
		int source = first + hints;
		int sink = source + 1;
		int[] destination = new int[first * second * 2];
		for (int i = 0; i < first; i++) {
			graph.addFlowEdge(source, i, 1);
			for (int j = 0; j < second; j++) {
				int single = -1;
				for (int k = 0; k < known[i].length && single != -2; k++) {
					if ((known[i][k] & other[j][k]) != other[j][k]) {
						int delta = other[j][k] - (known[i][k] & other[j][k]);
						if (Integer.bitCount(delta) > 1 || single != -1) {
							single = -2;
						} else {
							single = Integer.bitCount(delta - 1) + (k << 5);
						}
					}
				}
				if (single >= 0) {
					destination[graph.addFlowEdge(i, first + single, 1)] = j;
				}
			}
		}
		int[] knownInitially = new int[(hints + 31) >> 5];
		for (int i = 0; i < first; i++) {
			for (int j = 0; j < knownInitially.length; j++) {
				knownInitially[j] |= known[i][j];
			}
		}
		for (int i = 0; i < hints; i++) {
			if ((knownInitially[i >> 5] >> (i & 31) & 1) == 0) {
				graph.addFlowEdge(i + first, sink, 1);
			}
		}
		int total = (int) MaxFlow.dinic(graph, source, sink);
		for (int i : knownInitially) {
			total += Integer.bitCount(i);
		}
		if (total == hints) {
			out.printLine(1);
			int[] answer = new int[first];
			for (int i = 0; i < first; i++) {
				answer[i] = 1;
				for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
					if (graph.flow(j) != 0) {
						answer[i] = destination[j] + 1;
						break;
					}
				}
			}
			out.printLine(answer);
		} else {
			out.printLine(2);
		}
	}
}
