package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskG {
	private static final int INF = 1000000000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int s = in.readInt();
		int t = in.readInt();
		int f = in.readInt();

		Node[] v = new Node[n];
		for (int i = 0; i < n; i++) {
			v[i] = new Node();
			v[i].id = i;
		}

		Edge bad = null;
		for (int i = 0; i < m; i++) {
			int ss = in.readInt();
			int tt = in.readInt();
			int aa = in.readInt();
			int bb = in.readInt();
			int dd = in.readInt();
			Edge edge = new Edge();
			edge.src = v[ss];
			edge.dst = v[tt];
			edge.a = aa;
			edge.b = bb;
			edge.d = dd;
			v[ss].out.add(edge);
			if (bb > aa) bad = edge;
		}

		int[] d = new int[n];
		boolean[] z = new boolean[n];
		Arrays.fill(d, INF);
		d[s] = 0;
		for (int i = 0; i < n; i++) {
			int minj = -1;
			for (int j = 0; j < n; j++) if (!z[j]) {
				if (minj == -1 || d[j] < d[minj]) {
					minj = j;
				}
			}
			z[minj] = true;
			for (Edge e : v[minj].out) {
				int cost = f < e.d ? e.a * f : e.d * e.a + (f - e.d) * e.b;
				Node dst = e.dst;
				if (d[dst.id] > d[minj] + cost) {
					d[dst.id] = d[minj] + cost;
					dst.p = e;
				}
			}
		}
		if (d[t] == INF) {
			out.printLine("Impossible");
		} else {
			int res = d[t];
			if (bad == null || bad.d >= f) {
				out.printLine(res);
				return;
			}
			res = 0;
			Node node = v[t];
			int f1 = bad.d;
			int f2 = f - f1;
			while (node != v[s]) {
				Edge e = node.p;
				int cost = f1 < e.d ? e.a * f1 : e.d * e.a + (f1 - e.d) * e.b;
				e.d -= f1;
				if (e.d < 0) e.d = 0;
				res += cost;
				node = e.src;
			}
			Arrays.fill(z, false);
			Arrays.fill(d, INF);
			d[s] = 0;
			for (int i = 0; i < n; i++) {
				int minj = -1;
				for (int j = 0; j < n; j++) if (!z[j]) {
					if (minj == -1 || d[j] < d[minj]) {
						minj = j;
					}
				}
				z[minj] = true;
				for (Edge e : v[minj].out) {
					int cost = f2 < e.d ? e.a * f2 : e.d * e.a + (f2 - e.d) * e.b;
					Node dst = e.dst;
					if (d[dst.id] > d[minj] + cost) {
						d[dst.id] = d[minj] + cost;
						dst.p = e;
					}
				}
			}
			res += d[t];
			out.printLine(res);
		}
    }

	class Node {
		int id;
		List<Edge> out = new ArrayList<Edge>();
		Edge p;
	}

	class Edge {
		Node src;
		Node dst;
		int a;
		int b;
		int d;
	}
}
