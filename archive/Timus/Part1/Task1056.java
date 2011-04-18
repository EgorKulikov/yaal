package Timus.Part1;

import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.SimpleEdge;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Task1056 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		Graph tree = new BidirectionalGraph(n);
		for (int i = 0; i < n - 1; i++)
			tree.add(new SimpleEdge(i + 1, in.readInt() - 1));
		int[] skipped = new int[n];
		@SuppressWarnings({"unchecked"})
		NavigableSet<Pair<Integer, Integer>>[] results = new NavigableSet[n];
		List<Integer> result = new ArrayList<Integer>();
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int curDistance = go(i, -1, skipped, results, tree);
			if (curDistance < minDistance) {
				minDistance = curDistance;
				result.clear();
				result.add(i + 1);
			} else if (curDistance == minDistance)
				result.add(i + 1);
		}
		IOUtils.printCollection(result, out);
	}

	private int go(int vertex, int previous, int[] skipped, NavigableSet<Pair<Integer, Integer>>[] results, Graph tree) {
		if (results[vertex] == null) {
			results[vertex] = new TreeSet<Pair<Integer, Integer>>(new Comparator<Pair<Integer, Integer>>() {
				public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
					if (!o1.first().equals(o2.first()))
						return o2.first() - o1.first();
					return o1.second() - o2.second();
				}
			});
			skipped[vertex] = previous;
			results[vertex].add(new Pair<Integer, Integer>(-1, -2));
			for (Edge edge : tree.getIncident(vertex)) {
				if (edge.getDestination() != previous)
					results[vertex].add(new Pair<Integer, Integer>(go(edge.getDestination(), vertex, skipped, results, tree), edge.getDestination()));
			}
			return results[vertex].first().first() + 1;
		} else {
			if (skipped[vertex] == previous)
				return results[vertex].first().first() + 1;
			if (skipped[vertex] != -1)
				results[vertex].add(new Pair<Integer, Integer>(go(skipped[vertex], vertex, skipped, results, tree), skipped[vertex]));
			for (Pair<Integer, Integer> result : results[vertex]) {
				if (result.second() != previous)
					return result.first() + 1;
			}
			throw new RuntimeException();
		}
	}
}

