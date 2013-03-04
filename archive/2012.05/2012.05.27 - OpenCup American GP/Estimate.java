package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Estimate {
	static final long INF = (long) 1e18;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		if (n == 0 && k == 0) throw new UnknownError();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) a[i] = in.readInt() + 10000;
		long[][] best = new long[n + 1][k + 1];
		PriorityQueue<Integer> upper = new PriorityQueue<Integer>();
		PriorityQueue<Integer> lower = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		long upperSum;
		long lowerSum;
		int upperCnt;
		int lowerCnt;
		int[] am = new int[20001];
		for (int len = 1; len <= n; ++len) {
			Arrays.fill(best[len], INF);
			upper.clear();
			lower.clear();
			upperSum = 0;
			lowerSum = 0;
			upperCnt = 0;
			lowerCnt = 0;
			Arrays.fill(am, 0);
			for (int cnt = 1; cnt <= len; ++cnt) {
				int toAdd = a[len - cnt];
				++am[toAdd];
				if (lower.isEmpty() || toAdd > lower.peek()) {
					if (am[toAdd] == 1)
						upper.add(toAdd);
					upperSum += toAdd;
					++upperCnt;
				} else {
					if (am[toAdd] == 1)
						lower.add(toAdd);
					lowerSum += toAdd;
					++lowerCnt;
				}
				int middle;
				while (true) {
					if (lowerCnt > upperCnt) {
						int last = lower.peek();
						if (am[last] >= lowerCnt - upperCnt) {
							middle = last;
							break;
						}
						lowerCnt -= am[last];
						lowerSum -= am[last] * (long) last;
						upperCnt += am[last];
						upperSum += am[last] * (long) last;
						lower.poll();
						upper.add(last);
					} else {
						int last = upper.peek();
						if (am[last] >= upperCnt - lowerCnt) {
							middle = last;
							break;
						}
						lowerCnt += am[last];
						lowerSum += am[last] * (long) last;
						upperCnt -= am[last];
						upperSum -= am[last] * (long) last;
						lower.add(last);
						upper.poll();
					}
				}
				long penalty = upperSum - upperCnt * (long) middle + lowerCnt * (long) middle - lowerSum;
				for (int old = 0; old < k; ++old)
					best[len][old + 1] = Math.min(best[len][old + 1], best[len - cnt][old] + penalty);
			}
		}
		out.printLine(best[n][k]);
	}
}
