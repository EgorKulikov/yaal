package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Science {
	static class Edge {
		Vertex dest;
		Edge rev;
		int cap;
		int flow = 0;

		Edge(Vertex dest, int cap) {
			this.dest = dest;
			this.cap = cap;
		}
	}

	static int generation;

	static class Vertex {
		int mark = 0;
		int id;
		List<Edge> adj = new ArrayList<Edge>();

		Vertex(int id) {
			this.id = id;
		}

		public boolean dfs(Vertex t) {
			if (this == t)
				return true;
			mark = generation;
			for (Edge e : adj) {
				if (e.flow < e.cap && e.dest.mark < generation && e.dest.dfs(t)) {
					++e.flow;
					--e.rev.flow;
					return true;
				}
			}
			return false;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		Vertex[] vl = new Vertex[n];
		Vertex[] vr = new Vertex[n];
		Vertex s = new Vertex(-1);
		Vertex t = new Vertex(-1);
		Edge[] el = new Edge[n];
		Edge[] er = new Edge[n];
		for (int i = 0; i < n; ++i) {
			vl[i] = new Vertex(i);
			vr[i] = new Vertex(i);
			el[i] = addEdge(s, vl[i], 0);
			er[i] = addEdge(vr[i], t, 0);
		}
		for (int i = 0; i < n; ++i) {
			String ss = in.next();
			for (int j = 0; j < n; ++j) {
				if (ss.charAt(j) == 'Y') {
					addEdge(vl[i], vr[j], 1);
				}
			}
		}
		generation = 1;
		int left = 0;
		int right = n + 1;
		while (right - left > 1) {
			int middle = (left + right) / 2;
			for (Edge e : el) e.cap = middle;
			for (Edge e : er) e.cap = middle;
			for (Vertex v : vl) for (Edge e : v.adj) e.flow = 0;
			for (Vertex v : vr) for (Edge e : v.adj) e.flow = 0;
			for (Edge e : s.adj) e.flow = 0;
			for (Edge e : t.adj) e.flow = 0;
			int size = maxFlow(s, t);
			if (size == middle * n)
				left = middle;
			else
				right = middle;
		}
		for (Edge e : el) e.cap = left;
		for (Edge e : er) e.cap = left;
		for (Vertex v : vl) for (Edge e : v.adj) e.flow = 0;
		for (Vertex v : vr) for (Edge e : v.adj) e.flow = 0;
		for (Edge e : s.adj) e.flow = 0;
		for (Edge e : t.adj) e.flow = 0;
		if (maxFlow(s, t) != left * n) throw new RuntimeException();
		for (Vertex v : vl) for (Edge e : v.adj) e.cap = Math.max(0, e.flow);
		for (Vertex v : vr) for (Edge e : v.adj) e.cap = Math.max(0, e.flow);
		for (Edge e : s.adj) e.cap = Math.max(0, e.flow);
		for (Edge e : t.adj) e.cap = Math.max(0, e.flow);
		out.printLine(left);
		doit(left, vl, vr, s, t, el, er, out);
	}

	private void doit(int left, Vertex[] vl, Vertex[] vr, Vertex s, Vertex t, Edge[] el, Edge[] er, OutputWriter out) {
		if (left == 0) return;
		int n = vl.length;
		if (left % 2 < 2) {
			for (Edge e : el) e.cap = 1;
			for (Edge e : er) e.cap = 1;
			for (Vertex v : vl) for (Edge e : v.adj) e.flow = 0;
			for (Vertex v : vr) for (Edge e : v.adj) e.flow = 0;
			for (Edge e : s.adj) e.flow = 0;
			for (Edge e : t.adj) e.flow = 0;
			if (maxFlow(s, t) != n) throw new RuntimeException();
			int[] res = new int[n];
			for (Vertex v : vl) for (Edge e : v.adj) if (e.flow > 0) {
				res[e.dest.id] = v.id;
				e.cap = 0;
			}
			for (int i = 0; i < n; ++i) {
				if (i > 0) out.print(" ");
				out.print(res[i] + 1);
			}
			out.printLine();
			doit(left - 1, vl, vr, s, t, el, er, out);
		} else {

		}
	}

	private int maxFlow(Vertex s, Vertex t) {
		int res = 0;
		while (true) {
			++generation;
			boolean by = s.dfs(t);
			if (!by) break;
			++res;
		}
		return res;
	}

	private Edge addEdge(Vertex src, Vertex dst, int cap) {
		Edge dir = new Edge(dst, cap);
		Edge rev = new Edge(src, 0);
		dir.rev = rev;
		rev.rev = dir;
		src.adj.add(dir);
		dst.adj.add(rev);
		return dir;
	}
}
