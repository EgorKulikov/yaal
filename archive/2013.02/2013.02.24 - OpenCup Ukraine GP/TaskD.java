package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskD {
	static class Edge {
		int src;
		int dst;
	}
	Edge[] edges;
	int[] first;
	boolean seen[];
	int topid[];
	int topsort[];
	int topcnt;
	BitSet reach[];
	final Comparator<Edge> topcmp = new Comparator<Edge>() {
		public int compare(Edge o1, Edge o2) {
			return topid[o2.dst] - topid[o1.dst];
		}
	};

	void dfs(int i) {
		if (seen[i]) {
			return;
		}
		seen[i] = true;
		int start = first[i];
		int end = first[i + 1];
		for (int j = start; j < end; ++j) {
			dfs(edges[j].dst);
		}
		int id = topcnt++;
		topid[i] = id;
		topsort[id] = i;
		BitSet r = new BitSet(id + 1);
		reach[id] = r;
		r.set(id);
		Arrays.sort(edges, start, end, topcmp);
		for (int j = start; j < end; ++j) {
			int jj = topid[edges[j].dst];
			if (!r.get(jj)) {
				out.printLine(i + 1, edges[j].dst + 1);
				r.or(reach[jj]);
			}
		}
	}

	OutputWriter out;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		this.out = out;
		int n = in.readInt();
		int k = in.readInt();
		edges = new Edge[k];
		for (int i = 0; i < k; i++) {
			edges[i] = new Edge();
			edges[i].src = in.readInt() - 1;
			edges[i].dst = in.readInt() - 1;
		}
		Arrays.sort(edges, new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return o1.src - o2.src;
			}
		});
		first = new int[n + 1];
		int at = 0;
		for (int i = 0; i <= n; ++i) {
			first[i] = at;
			while (at < edges.length && edges[at].src == i) ++at;
		}
		seen = new boolean[n];
		topid = new int[n];
		topsort = new int[n];
		topcnt = 0;
		reach = new BitSet[n];
		for (int i = 0; i < n; i++) {
			dfs(i);
		}
	}
}
