package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {
	int[] primes = IntegerUtils.generatePrimes(20000);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] age = IOUtils.readIntArray(in, count);
		Graph graph = new Graph(count + 2);
		for (int i = 0; i < count; i++) {
			if ((age[i] & 1) == 0) {
				graph.addFlowEdge(count, i, 2);
				for (int j = 0; j < count; j++) {
					if (Arrays.binarySearch(primes, age[i] + age[j]) >= 0) {
						graph.addFlowEdge(i, j, 1);
					}
				}
			} else {
				graph.addFlowEdge(i, count + 1, 2);
			}
		}
		if (MaxFlow.dinic(graph, count, count + 1) != count) {
			out.printLine("Impossible");
		} else {
			List<IntList> answer = new ArrayList<>();
			boolean[] taken = new boolean[count];
			for (int i = 0; i < count; i++) {
				if (taken[i]) {
					continue;
				}
				IntList table = new IntArrayList();
				table.add(i + 1);
				taken[i] = true;
				int current = i;
				boolean completed = false;
				while (!completed) {
					if ((age[current] & 1) == 0) {
						for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
							int to = graph.destination(j);
							if (graph.flow(j) > 0 && (!taken[to] || to == i && table.size() > 2)) {
								if (!taken[to]) {
									taken[to] = true;
									table.add(to + 1);
									current = to;
								} else {
									completed = true;
								}
								break;
							}
						}
					} else {
						for (int j = graph.firstInbound(current); j != -1; j = graph.nextInbound(j)) {
							int to = graph.source(j);
							if (graph.flow(j) > 0 && (!taken[to] || to == i && table.size() > 2)) {
								if (!taken[to]) {
									taken[to] = true;
									table.add(to + 1);
									current = to;
								} else {
									completed = true;
								}
								break;
							}
						}
					}
				}
				answer.add(table);
			}
			out.printLine(answer.size());
			for (IntList table : answer) {
				out.print(table.size() + " ");
				out.printLine(table);
			}
		}
    }
}
