package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxTaken = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int[] order = new int[count];
		int[] level = new int[count];
		dfs(0, -1, graph, order, 0);
		int[][] asArray = new int[count][];
		for (int i = 0; i < count; i++) {
			IntList list = new IntArrayList();
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j))
				list.add(graph.destination(j));
			asArray[i] = list.toArray();
		}
		LCA lca = new LCA(asArray);
		int[] reverse = ArrayUtils.reversePermutation(order);
		NavigableSet<Integer> set = new TreeSet<Integer>();
		int size = 2;
		int k = 0;
		int answer = 1;
		set.add(reverse[0]);
		for (int i = 1; i < count; i++) {
			int distance = 0;
			Integer left = set.lower(reverse[i]);
			Integer right = set.higher(reverse[i]);
			if (left != null)
				distance += lca.getPathLength(i, order[left]);
			else
				distance += lca.getPathLength(i, order[set.last()]);
			if (right != null)
				distance += lca.getPathLength(i, order[right]);
			else
				distance += lca.getPathLength(i, order[set.first()]);
			if (left != null && right != null)
				distance -= lca.getPathLength(order[left], order[right]);
			else if (left == null)
				distance -= lca.getPathLength(order[right], order[set.last()]);
			else
				distance -= lca.getPathLength(order[left], order[set.first()]);
			size += distance;
			set.add(reverse[i]);
			while (size > maxTaken * 2) {
				set.remove(reverse[k]);
				distance = 0;
				left = set.lower(reverse[k]);
				right = set.higher(reverse[k]);
				if (left != null)
					distance += lca.getPathLength(k, order[left]);
				else
					distance += lca.getPathLength(k, order[set.last()]);
				if (right != null)
					distance += lca.getPathLength(k, order[right]);
				else
					distance += lca.getPathLength(k, order[set.first()]);
				if (left != null && right != null)
					distance -= lca.getPathLength(order[left], order[right]);
				else if (left == null)
					distance -= lca.getPathLength(order[right], order[set.last()]);
				else
					distance -= lca.getPathLength(order[left], order[set.first()]);
				k++;
				size -= distance;
			}
			answer = Math.max(answer, i - k + 1);
		}
		out.printLine(answer);
    }

	private int dfs(int vertex, int last, Graph graph, int[] order, int at) {
		order[at++] = vertex;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int to = graph.destination(i);
			if (to != last)
				at = dfs(to, vertex, graph, order, at);
		}
		return at;
	}
}
