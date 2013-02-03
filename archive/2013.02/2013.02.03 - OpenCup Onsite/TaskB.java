package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	private int[] p;
	private boolean[] v;
	private int[] h;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int t = in.readInt();
		int k = in.readInt();
		Edge[] e = new Edge[n - 1];
		for (int i = 0; i < n - 1; i++) {
			e[i] = new Edge();
			e[i].a = in.readInt() - 1;
			e[i].b = in.readInt() - 1;
			e[i].cost = in.readInt();
		}
		Arrays.sort(e);
		int dd = n - 1;
		p = new int[n];
		h = new int[n];
		for (int i = 0; i < n; i++) p[i] = i;
		v = new boolean[n];
		Arrays.fill(v, true);
		for (int i = 0; i < t; i++) {
			int x = in.readInt();
			v[x - 1] = false;
		}
		for (int i = n - 2; i >= 0; i--) {
			int x = get(e[i].a);
			int y = get(e[i].b);
			if (v[x] || v[y]) {
				e[i].z = true;
				join(x, y);
				dd--;
			}
			if (dd == k) break;
		}
		for (int i = n - 2; i >= 0; i--) {
			if (!e[i].z && dd > k) {
				dd--;
				e[i].z = true;
			}
		}
		int s = 0;
		for (int i = 0; i < n - 1; i++) {
			if (!e[i].z) {
				s += e[i].cost;
			}
		}
		out.printLine(s);
    }


	private void join(int x, int y) {
		if (h[x] < h[y]) {
			p[x] = y;
			v[y] = v[y] && v[x];
		} else {
			p[y] = x;
			v[x] = v[y] && v[x];
			if (h[x] == h[y]) {
				h[x]++;
			}
		}
	}

	private int get(int i) {
		if (p[i] == i) return i;
		p[i] = get(p[i]);
		return p[i];
	}

	class Edge implements Comparable<Edge>{
		int a;
		int b;
		int cost;
		boolean z;

		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}
}
