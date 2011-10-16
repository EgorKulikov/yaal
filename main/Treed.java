import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Treed implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
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
		int mx = 0;
		int s = 1;
		while (s < 2 * n) {
			s *= 2;
			mx++;
		}
		int[][] hh = new int[mx + 1][2 * n];
		pt = 0;
		dfs(nodes[0], hh[0]);
		s = 1;
		for (int i = 1; i <= mx; i++) {
			for (int j = 0; j < 2 * n; j++) {
				hh[i][j] = hh[i - 1][j];
				if (j + s < 2 * n) {
					hh[i][j] = Math.min(hh[i][j], hh[i - 1][j + s]);
				}
			}
			s *= 2;
		}
		Node a = nodes[0];
		Node b = nodes[0];
		int d = 0;
		for (int i = 1; i < n; i++) {
			Node c = nodes[i];
			int dd = dist(a, c, hh);
			if (dd > d) {
				b = c;
				d = dd;
			}
			dd = dist(b, c, hh);
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

	private int dist(Node a, Node b, int[][] hh) {
		int l = Math.min(a.l, b.l);
		int r = Math.max(a.r, b.r);
		int n = r - l;
		int s = 1;
		int c = 0;
		while (s * 2 <= n) {
			s *= 2;
			c++;
		}
		int min = Math.min(hh[c][l], hh[c][r - s]);
		return a.h - min + b.h - min;
	}

	int pt;

	private void dfs(Node node, int[] hh) {
		node.l = pt;
		hh[pt++] = node.h;
		for (Node child : node.children) {
			dfs(child, hh);
			hh[pt++] = node.h;
		}
		node.r = pt;
	}

	class Node {
		int id;
		int h;
		Node parent;
		int l, r;
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
