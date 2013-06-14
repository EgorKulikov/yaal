package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intervaltree.LCA;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TreeFun {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		final LCA lca = new LCA(graph);
		IntComparator comparator = new IntComparator() {
			public int compare(int first, int second) {
				return lca.getPosition(first) - lca.getPosition(second);
			}
		};
		int[] vertices = new int[count];
		for (int i = 0; i < queryCount; i++) {
			int length = in.readInt();
			for (int j = 0; j < length; j++)
				vertices[j] = in.readInt() - 1;
			if (length == 2) {
				out.printLine(lca.getPathLength(vertices[0], vertices[1]) - 1);
			} else if (length == 3) {
				int curLCA = lca.getLCA(vertices[0], vertices[1]);
				int cntSame = 0;
				if (curLCA == vertices[0] || curLCA == vertices[1] || curLCA == vertices[2]) {
					cntSame++;
				}
				curLCA = lca.getLCA(vertices[2], vertices[1]);
				if (curLCA == vertices[2] || curLCA == vertices[1] || curLCA == vertices[0]) {
					cntSame++;
				}
				curLCA = lca.getLCA(vertices[2], vertices[0]);
				if (curLCA == vertices[2] || curLCA == vertices[0] || curLCA == vertices[1]) {
					cntSame++;
				}
				if (cntSame == 2 || cntSame == 0)
					out.printLine(1);
				else
					out.printLine(0);
			} else {
				ArrayUtils.sort(vertices, 0, length, comparator);
				int curLCA = getLCA(lca, 1, length - 1, vertices);
				if (curLCA == -1 || curLCA == vertices[0] || curLCA == vertices[length - 1]) {
					out.printLine(0);
					continue;
				}
				if (lca.getLCA(vertices[1], vertices[0]) == curLCA && lca.getLevel(lca.getLCA(vertices[length - 2], vertices[length - 1])) <= lca.getLevel(curLCA)) {
					out.printLine(1);
					continue;
				}
				if (lca.getLCA(vertices[length - 2], vertices[length - 1]) == curLCA && lca.getLevel(lca.getLCA(vertices[1], vertices[0])) <= lca.getLevel(curLCA)) {
					out.printLine(1);
					continue;
				}
				out.printLine(0);
			}
		}
	}

	private int getLCA(LCA lca, int from, int to, int[] vertices) {
		int curLCA = lca.getLCA(vertices[from], vertices[from + 1]);
		if (curLCA == vertices[from] || curLCA == vertices[from + 1])
			curLCA = -1;
		for (int j = from + 2; j < to && curLCA != -1; j++) {
			if (lca.getLCA(vertices[j], vertices[j - 1]) != curLCA || curLCA == vertices[j])
				curLCA = -1;
		}
		return curLCA;
	}
}
