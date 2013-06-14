package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class taskG {
	static class Edge {
		int a;
		int b;
		int c;
		boolean alive;

		Edge(int a, int b, int c, boolean alive) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.alive = alive;
		}
	}

	static class Request {
		Edge removed;
		Edge added;
		int index;
		int a;
		int b;
		int w;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		if (n == 0) throw new UnknownError();
		Edge[] e = new Edge[m];
		List<Edge> allEdges = new ArrayList<Edge>();
		for (int i = 0; i < m; ++i) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			int c = in.readInt();
			e[i] = new Edge(a, b, c, true);
			allEdges.add(e[i]);
		}
		int numRequests = in.readInt();
		Request[] req = new Request[numRequests];
		int lastIndex = 0;
		for (int i = 0; i < numRequests; ++i) {
			req[i] = new Request();
			char ch = in.readCharacter();
			if (ch == 'B' || ch == 'R') {
				int roadId = in.readInt() - 1;
				int newCap = in.readInt();
				Edge ne = new Edge(e[roadId].a, e[roadId].b, newCap, false);
				allEdges.add(ne);
				req[i].removed = e[roadId];
				req[i].added = ne;
				e[roadId] = ne;
			} else {
				req[i].a = in.readInt() - 1;
				req[i].b = in.readInt() - 1;
				req[i].w = in.readInt();
				req[i].index = lastIndex++;
			}
		}
		Collections.sort(allEdges, new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return o2.c - o1.c;
			}
		});
		int[] output = new int[lastIndex];
		Arrays.fill(output, -1);
		int pos = 0;
		parent = new int[n];
		rank = new int[n];
		while (pos < req.length) {
			if (req[pos].removed != null) {
				if (!req[pos].removed.alive) throw new RuntimeException();
				req[pos].removed.alive = false;
				if (req[pos].added.alive) throw new RuntimeException();
				req[pos].added.alive = true;
				++pos;
			} else {
				int lastPos = pos;
				while (lastPos < req.length && req[lastPos].removed == null)
					++lastPos;
				Arrays.sort(req, pos, lastPos, new Comparator<Request>() {
					public int compare(Request o1, Request o2) {
						return o2.w - o1.w;
					}
				});
				Arrays.fill(parent, -1);
				Arrays.fill(rank, 0);
				for (Edge edge : allEdges) {
					if (!edge.alive) continue;
					while (pos < lastPos && req[pos].w > edge.c) {
						int a = get(req[pos].a);
						int b = get(req[pos].b);
						if (a == b) output[req[pos].index] = 1; else output[req[pos].index] = 0;
						++pos;
					}
					{
						int a = get(edge.a);
						int b = get(edge.b);
						if (a != b) merge(a, b);
					}
				}
				while (pos < lastPos) {
					int a = get(req[pos].a);
					int b = get(req[pos].b);
					if (a == b) output[req[pos].index] = 1; else output[req[pos].index] = 0;
					++pos;
				}
			}
		}
		for (int x : output) out.printLine(x);
    }

	private void merge(int a, int b) {
		if (rank[a] < rank[b]) {
			parent[a] = b;
		} else if (rank[b] < rank[a]) {
			parent[b] = a;
		} else {
			parent[a] = b;
			++rank[b];
		}
	}

	private int get(int a) {
		int p = parent[a];
		if (p < 0) return a;
		parent[a] = get(p);
		return parent[a];
	}

	int[] rank;
	int[] parent;
}
