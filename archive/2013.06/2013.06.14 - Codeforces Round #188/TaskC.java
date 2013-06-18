package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int volume = in.readInt();
		int edgeCount = in.readInt();
		int[] start = IOUtils.readIntArray(in, count);
		int[] end = IOUtils.readIntArray(in, count);
		if (ArrayUtils.sumArray(start) != ArrayUtils.sumArray(end)) {
			out.printLine("NO");
			return;
		}
		boolean[][] was = new boolean[count][count];
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			was[to][from] = was[from][to] = true;
		}
		boolean[] processed = new boolean[count];
		IntList from = new IntArrayList();
		IntList to = new IntArrayList();
		IntList amount = new IntArrayList();
		for (int i = 0; i < count; i++) {
			int[] degree = new int[count];
			IndependentSetSystem system = new RecursiveIndependentSetSystem(count);
			for (int j = 0; j < count; j++) {
				if (processed[j])
					continue;
				for (int k = j + 1; k < count; k++) {
					if (!processed[k] && was[j][k] && system.join(j, k)) {
						degree[j]++;
						degree[k]++;
					}
				}
			}
			int current = -1;
			for (int j = 0; j < count; j++) {
				if (degree[j] == 1) {
					current = j;
					break;
				}
			}
			if (current == -1) {
				for (int j = 0; j < count; j++) {
					if (!processed[j] && start[j] != end[j]) {
						out.printLine("NO");
						return;
					}
				}
				break;
			}
			Graph graph = new Graph(count + 2);
			for (int j = 0; j < count; j++) {
				if (processed[j])
					continue;
				if (j != current)
					graph.addFlowEdge(j, count, end[current] < start[current] ? volume - start[j] : start[j]);
				else
					graph.addFlowEdge(count + 1, j, Math.abs(start[j] - end[j]));
				for (int k = 0; k < count; k++) {
					if (was[j][k])
						graph.addFlowEdge(j, k, Long.MAX_VALUE / 2);
				}
			}
			long flow = MaxFlow.dinic(graph, count + 1, count);
			if (start[current] > end[current])
				pushDown(from, to, amount, graph, current, count, start, end, volume);
			else {
				pushUp(from, to, amount, graph, current, count, start, end, volume);
			}
			processed[current] = true;
		}
		out.printLine(from.size());
		for (int i = 0; i < from.size(); i++)
			out.printLine(from.get(i) + 1, to.get(i) + 1, amount.get(i));
    }

	private void pushDown(IntList from, IntList to, IntList amount, Graph graph, int current, int count, int[] start, int[] end, int volume) {
		int id = graph.firstOutbound(current);
		while (id != -1) {
			if (graph.capacity(id) < 100500) {
				id = graph.nextOutbound(id);
				continue;
			}
			int next = graph.destination(id);
			int flow = (int) graph.flow(id);
			if (flow != 0 && next != count) {
				if (start[next] + flow <= volume) {
					from.add(current);
					to.add(next);
					amount.add(flow);
					start[next] += flow;
					start[current] -= flow;
					pushDown(from, to, amount, graph, next, count, start, end, volume);
				} else {
					from.add(current);
					to.add(next);
					int ss = volume - start[next];
					amount.add(ss);
					start[next] = volume;
					pushDown(from, to, amount, graph, next, count, start, end, volume);
					from.add(current);
					to.add(next);
					amount.add(flow - ss);
					start[next] += flow - ss;
					start[current] -= flow;
				}
			}
			id = graph.nextOutbound(id);
		}
	}

	private void pushUp(IntList from, IntList to, IntList amount, Graph graph, int current, int count, int[] start, int[] end, int volume) {
		int id = graph.firstOutbound(current);
		while (id != -1) {
			if (graph.capacity(id) < 100500) {
				id = graph.nextOutbound(id);
				continue;
			}
			int next = graph.destination(id);
			int flow = (int) graph.flow(id);
			if (flow != 0 && next != count) {
				if (start[next] >= flow) {
					from.add(next);
					to.add(current);
					amount.add(flow);
					start[next] -= flow;
					start[current] += flow;
					pushUp(from, to, amount, graph, next, count, start, end, volume);
				} else {
					from.add(next);
					to.add(current);
					int ss = start[next];
					amount.add(ss);
					start[next] = 0;
					pushUp(from, to, amount, graph, next, count, start, end, volume);
					from.add(next);
					to.add(current);
					amount.add(flow - ss);
					start[next] -= flow - ss;
					start[current] += flow;
				}
			}
			id = graph.nextOutbound(id);
		}
	}
}
