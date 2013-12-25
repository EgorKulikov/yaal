package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class KthAncestor {
	Node[] nodes = new Node[100001];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Arrays.fill(nodes, null);
		int nodeCount = in.readInt();
		int[] id = new int[nodeCount];
		int[] parent = new int[nodeCount];
		IOUtils.readIntArrays(in, id, parent);
		Graph graph = Graph.createGraph(100001, parent, id);
		int[] order = GraphAlgorithms.topologicalSort(graph);
		nodes[0] = new Node(0, 0);
		for (int i : order) {
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j))
				nodes[graph.destination(j)] = new Node(graph.destination(j), i);
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 0) {
				int curParent = in.readInt();
				int curID = in.readInt();
				nodes[curID] = new Node(curID, curParent);
			} else if (type == 1) {
				int curID = in.readInt();
				nodes[curID] = null;
			} else {
				int curID = in.readInt();
				int at = in.readInt();
				if (nodes[curID] == null)
					out.printLine(0);
				else
					out.printLine(nodes[curID].getParent(at));
			}
		}
    }

	class Node {
		int id;
		IntList parents = new IntArrayList();

		Node(int id, int parent) {
			this.id = id;
			parents.add(parent);
			int level = 0;
			while (parent != 0) {
				IntList parentParents = nodes[parent].parents;
				parent = parentParents.get(Math.min(level, parentParents.size() - 1));
				parents.add(parent);
				level++;
			}
		}

		int getParent(int at) {
			if (at == 0)
				return id;
			int level = Integer.bitCount(Integer.highestOneBit(at) - 1);
			return nodes[parents.get(Math.min(parents.size() - 1, level))].getParent(at - (1 << level));
		}
	}
}
