package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Juggler {
	static class Fenwick {
		int[] sums;

		public Fenwick(int n) {
			sums = new int[n];
		}

		void add(int at, int by) {
			while (at < sums.length) {
				sums[at] += by;
				at |= (at + 1);
			}
		}

		int get(int at) {
			if (at < 0) return 0;
			return sums[at] + get((at & (at + 1)) - 1);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		int[] perm = new int[n];
		for (int i = 0; i < n; ++i) {
			perm[i] = in.readInt() - 1;
		}
		int[] rev = new int[n];
		for (int i = 0; i < n; ++i) rev[perm[i]] = i;
		Fenwick f = new Fenwick(n);
		for (int i = 0; i < n; ++i) f.add(i, 1);
		int pos = 0;
		long res = 0;
		for (int i = 0; i < n; ++i) {
			int at = rev[i];
			int cnt = Math.abs(f.get(at - 1) - f.get(pos - 1));
			res += Math.min(cnt, n - i - cnt);
			f.add(at, -1);
			pos = at;
		}
		out.printLine(res + n);
	}
}
