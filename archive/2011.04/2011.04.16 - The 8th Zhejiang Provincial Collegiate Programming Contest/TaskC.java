package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int queryCount = in.readInt();
		@SuppressWarnings({"unchecked"})
		List<Integer>[] incident = new List[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			incident[i] = new ArrayList<Integer>();
		for (int i = 0; i < vertexCount - 1; i++) {
			int from = in.readInt();
			int to = in.readInt();
			incident[from].add(to);
		}
		Node[] nodes = new Node[vertexCount];
		init(0, nodes, incident, 0);
		List<List<Integer>> result = buildPath(0, nodes, new ArrayList<Integer>(), new ArrayList<List<Integer>>());
		for (int i = 0; i < queryCount; i++) {
			int index = in.readInt() - 1;
			if (index >= result.size())
				out.println("Out of range.");
			else
				IOUtils.printCollection(result.get(index), out);
		}
		out.println();
	}

	private List<List<Integer>> buildPath(int vertex, Node[] nodes, List<Integer> path, List<List<Integer>> paths) {
		if (nodes[vertex].capacity == 1) {
			paths.add(path);
			return paths;
		}
		for (int i = 0; i < nodes[vertex].order.size(); i++) {
			List<Integer> nextPath = new ArrayList<Integer>(path);
			nextPath.add(i);
			buildPath(nodes[vertex].order.get(i), nodes, nextPath, paths);
		}
		return paths;
	}

	private int init(int vertex, final Node[] nodes, List<Integer>[] incident, int depth) {
		nodes[vertex] = new Node();
		nodes[vertex].capacity = 1;
		nodes[vertex].order = incident[vertex];
		for (int next : incident[vertex])
			nodes[vertex].capacity += init(next, nodes, incident, depth + 1);
		if (nodes[vertex].capacity == 1) {
			nodes[vertex].depth = depth;
			return 1;
		}
		nodes[vertex].depth = Integer.MAX_VALUE;
		for (int i : incident[vertex])
			nodes[vertex].depth = Math.min(nodes[vertex].depth, nodes[i].depth);
		Collections.sort(nodes[vertex].order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (nodes[o1].depth != nodes[o2].depth)
					return nodes[o1].depth - nodes[o2].depth;
//				if (nodes[o1].capacity != nodes[o2].capacity)
//					return nodes[o2].capacity - nodes[o1].capacity;
				for (int i = 0; ; i++) {
					if (i == nodes[o1].order.size() && i == nodes[o2].order.size())
						return 0;
					if (i == nodes[o1].order.size())
						return 1;
					if (i == nodes[o2].order.size())
						return -1;
					int value = compare(nodes[o1].order.get(i), nodes[o2].order.get(i));
					if (value != 0)
						return value;
				}
			}
		});
		return nodes[vertex].capacity;
	}

	private static class Node {
		private int capacity, depth;
		private List<Integer> order;
	}
}

