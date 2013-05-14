package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskD {
	int n;
	ArrayList<Integer>[] nei;
	int[][] len, val, sum;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		int m = in.readInt();
		nei = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			nei[i] = new ArrayList();
		}
		for (int i = 0; i < m; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			nei[a].add(b);
			nei[b].add(a);
		}
		len = new int[n][n];
		val = new int[n][n];
		sum = new int[n][n];
		boolean[] mark = new boolean[n];
		for (int dest = 0; dest < n; dest++) {
			Arrays.fill(mark, false);
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(dest);
			mark[dest] = true;
			for (int dist = 1;; dist++) {
				ArrayList<Integer> next = new ArrayList<Integer>();
				for (int v : list) {
					for (int u : nei[v]) {
						if (mark[u]) {
							continue;
						}
						mark[u] = true;
						next.add(u);
						len[u][dest] = dist;
						val[u][dest] = val[v][dest] + sum[v][dest] + (u + 1);
						sum[u][dest] = sum[v][dest] + (u + 1);
					}
				}
				if (next.isEmpty()) {
					break;
				}
				list = next;
				Collections.sort(list);
			}
		}
		int routes = in.readInt();
		Map<Long, Integer> already = new HashMap();
		for (int route = 0; route < routes; route++) {
			m = in.readInt();
			int v = in.readInt() - 1;
			int u = 0;
			long length = 0;
			long s = 0;
			for (int i = 1; i < m; i++) {
				u = in.readInt() - 1;
				s += length * sum[v][u] + val[v][u];
				length += len[v][u];
				v = u;
			}
			s += (length + 1) * (u + 1);
			if (already.containsKey(s)) {
				int num = already.get(s) + 1;
				already.put(s, num);
				out.printLine(s + "#" + num);
			} else {
				out.printLine(s);
				already.put(s, 1);
			}
		}
	}
}
