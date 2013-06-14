package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskC {

	int[][] mv = new int[][]
		{{1, 2}, {0, 3, 4}, {0, 4, 5}, {1, 6, 7}, {1, 2, 7, 8},
			{2, 8, 9}, {3, 10, 11}, {3, 4, 11, 12}, {4, 5, 12, 13},
			{5, 13, 14}, {6}, {6, 7}, {7, 8}, {8, 9}, {9}};
	private int n;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		n = (n * (n + 1)) / 2;
		long start = 0;
		for (int i = 0; i < n; i++) {
			start |= (1L * in.readInt()) << (i * 4);
		}
		long finish = 0;
		for (int i = 0; i < n; i++) {
			finish |= (1L * (i + 1)) << (i * 4);
		}
		Map<Long, Integer> d1 = bfs(start, 22);
		Map<Long, Integer> d2 = bfs(finish, 22);
		int res = 45;
		for (Long state : d1.keySet()) {
			if (d2.containsKey(state)) {
				res = Math.min(res, d1.get(state) + d2.get(state));
			}
		}
		out.printLine(res);
    }

	private Map<Long, Integer> bfs(long start, int maxD) {
		Map<Long, Integer> res = new HashMap<Long, Integer>();
		List<Long> cur = new ArrayList<Long>();
		cur.add(start);
		res.put(start, 0);
		for (int d = 0; d < maxD; d++) {
			List<Long> next = new ArrayList<Long>();
			for (Long state : cur) {
				for (int i = 0; i < n; i++) if (((state >> (i * 4)) & 15) == 1) {
					for (int j : mv[i]) if (j < n) {
						long st = state;
						st &= ~(15L << (i * 4));
						st &= ~(15L << (j * 4));
						st |= ((state >> (i * 4)) & 15) << (j * 4);
						st |= ((state >> (j * 4)) & 15) << (i * 4);
						if (!res.containsKey(st)) {
							res.put(st, d + 1);
							next.add(st);
						}
					}
				}
			}
			cur = next;
		}
		return res;
	}
}
