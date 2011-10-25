import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Treed implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		if (n == 0) {
			Exit.exit(in, out);
			return;
		}
		Node[] nodes = new Node[n];
		nodes[0] = new Node(null, 0);
		for (int i = 1; i < n; i++) {
			int j = in.readInt() - 1;
			nodes[i] = new Node(nodes[j], i);
		}
//		int mx = 0;
//		int s = 1;
//		while (s < 2 * n) {
//			s *= 2;
//			mx++;
//		}
//		int[][] hh = new int[mx + 1][2 * n];
//		pt = 0;
//		dfs(nodes[0], hh[0]);
//		s = 1;
//		for (int i = 1; i <= mx; i++) {
//			for (int j = 0; j < 2 * n; j++) {
//				hh[i][j] = hh[i - 1][j];
//				if (j + s < 2 * n) {
//					hh[i][j] = Math.min(hh[i][j], hh[i - 1][j + s]);
//				}
//			}
//			s *= 2;
//		}
		int[] array = new int[2 * n];
		pt = 0;
		dfs(nodes[0], array);
		IntervalTree tree = new IntervalTree(array);
		Node a = nodes[0];
		Node b = nodes[0];
		int d = 0;
		for (int i = 1; i < n; i++) {
			Node c = nodes[i];
			int dd = dist(a, c, tree, nodes);
			if (dd > d) {
				b = c;
				d = dd;
			}
			dd = dist(b, c, tree, nodes);
			if (dd > d) {
				a = c;
				d = dd;
			}
			if (i != n - 1)
				out.print(d + " ");
			else
				out.print(d);
		}
		out.println();
	}

	private int dist(Node a, Node b, IntervalTree hh, Node[] nodes) {
		return a.h + b.h - 2 * hh.value(a.position, b.position);
	}

	int pt;

	private void dfs(Node node, int[] hh) {
		node.position = pt;
		hh[pt++] = node.h;
		for (Node child : node.children) {
			dfs(child, hh);
			hh[pt++] = node.h;
		}
	}

	class Node {
		int id;
		int h;
		Node parent;
		int position;
		List<Node> children = new ArrayList<Node>();

		Node(Node parent, int id) {
			this.id = id;
			this.parent = parent;
			if (parent == null) {
				h = 0;
			} else {
				h = parent.h + 1;
				parent.children.add(this);
			}
		}
	}
}

class IntervalTree {
	private int[] left, right, value;

	IntervalTree(int[] array) {
		int size = Integer.highestOneBit(array.length) * 4;
		left = new int[size];
		right = new int[size];
		value = new int[size];
		init(0, 0, array.length, array);
	}

	private int init(int root, int from, int to, int[] array) {
		left[root] = from;
		right[root] = to;
		if (to - from == 1)
			value[root] = array[from];
		else
			value[root] = Math.min(init(2 * root + 1, from, (from + to) / 2, array), init(2 * root + 2, (from + to) / 2, to, array));
		return value[root];
	}

	public int value(int from, int to) {
		return value(0, Math.min(from, to), Math.max(from, to) + 1);
	}

	private int value(int root, int from, int to) {
		if (from >= right[root] || to <= left[root])
			return Integer.MAX_VALUE;
		if (from <= left[root] && to >= right[root])
			return value[root];
		return Math.min(value(2 * root + 1, from, to), value(2 * root + 2, from, to));
	}
}
