package on2015_11.on2015_11_22_Grand_Prix_of_Europe___2015.J___Juice_Junctions;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.queue.IntArrayQueue;
import net.egork.generated.collections.queue.IntQueue;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskJ {
	int answer = 0;
	private int[] tin;
	private int[] fup;
	private boolean[] used;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		tin = new int[n];
		fup = new int[n];
		used = new boolean[n];
		int m = in.readInt();
		Graph graph = new BidirectionalGraph(n, m);
		for (int i = 0; i < m; i++) {
			graph.addSimpleEdge(in.readInt() - 1, in.readInt() - 1);
		}
		List<Graph> graphs = findComponents(graph, true);
		List<Graph> doubleConnected = new ArrayList<>();
		for (Graph component : graphs) {
			IntList bridges = getBridges(component);
			for (int i : bridges) {
				component.removeEdge(i);
				component.removeEdge(i ^ 1);
			}
			doubleConnected.addAll(findComponents(component, true));
		}
		for (Graph component : doubleConnected) {
			for (int i = 0; i < component.vertexCount(); i++) {
				int degree = 0;
				for (int j = component.firstOutbound(i); j != -1; j = component.nextOutbound(j)) {
					degree++;
				}
				if (degree == 2) {
					int first = component.firstOutbound(i);
					int second = component.nextOutbound(first);
					component.addSimpleEdge(component.destination(first), component.destination(second));
					component.removeEdge(first);
					component.removeEdge(first ^ 1);
					component.removeEdge(second);
					component.removeEdge(second ^ 1);
				}
			}
			List<Graph> simplified = findComponents(component, false);
			component = null;
			for (Graph sComponent : simplified) {
				if (sComponent.vertexCount() > 1) {
					component = sComponent;
					break;
				}
			}
			if (component == null) {
				continue;
			}
			IntSet doubles = new IntHashSet();
			for (int i = 0; i < component.edgeCount(); i += 2) {
				component.removeEdge(i);
				component.removeEdge(i + 1);
				doubles.addAll(getBridges(component));
				component.restoreEdge(i);
				component.restoreEdge(i + 1);
			}
			for (int i : doubles) {
				component.removeEdge(i);
				component.removeEdge(i ^ 1);
			}
			findComponents(component, true);
		}
		out.printLine(answer);
	}

	IntList bridges;
	int timer;
	Graph graph;

	private IntList getBridges(Graph graph) {
		timer = 0;
		Arrays.fill(used, 0, graph.vertexCount(), false);
		bridges = new IntArrayList();
		this.graph = graph;
		dfs(0, -1);
		return bridges;
	}

	private void dfs(int v, int p) {
		used[v] = true;
		tin[v] = fup[v] = timer++;
		for (int i = graph.firstOutbound(v); i != -1; i = graph.nextOutbound(i)) {
			int to = graph.destination(i);
			if (i == p || (i ^ 1) == p) continue;
			if (used[to]) {
				fup[v] = Math.min(fup[v], tin[to]);
			} else {
				dfs(to, i);
				fup[v] = Math.min(fup[v], fup[to]);
				if (fup[to] > tin[v]) {
					bridges.add(i);
				}
			}
		}
	}

	private List<Graph> findComponents(Graph graph, boolean addToAns) {
		int n = graph.vertexCount();
		int[] id = ArrayUtils.createArray(n, -1);
		List<Graph> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (id[i] == -1) {
				int next = 0;
				IntQueue queue = new IntArrayQueue();
				queue.add(i);
				id[i] = next++;
				IntList vertices = new IntArrayList();
				while (!queue.isEmpty()) {
					int j = queue.poll();
					vertices.add(j);
					for (int k = graph.firstOutbound(j); k != -1; k = graph.nextOutbound(k)) {
						int other = graph.destination(k);
						if (id[other] == -1) {
							id[other] = next++;
							queue.add(other);
						}
					}
				}
				Graph component = new BidirectionalGraph(next);
				for (int j : vertices) {
					for (int k = graph.firstOutbound(j); k != -1; k = graph.nextOutbound(k)) {
						int other = graph.destination(k);
						if (other < j) {
							component.addSimpleEdge(id[other], id[j]);
						}
					}
				}
				if (addToAns) {
					answer += (next * (next - 1)) >> 1;
				}
				result.add(component);
			}
		}
		return result;
	}
}
