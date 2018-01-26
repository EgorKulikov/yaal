package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {

	static final int MODULO = (int) 1e9 + 7;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int p = in.readInt();
		int[] dislikeMask = new int[n + 1];
		for (int i = 0; i < k; ++i) {
			int a = in.readInt();
			int b = in.readInt();
			if (Math.abs(a - b) > p) continue;
			dislikeMask[a] |= 1 << (b - a + p);
		}
		if (n == 1) {
			out.printLine(1);
			return;
		}
		if (p == 0) {
			out.printLine(0);
			return;
		}
		if (n == 2) {
			out.printLine(k == 0 ? 1 : 0);
			return;
		}
		if (p == 1) {
			out.printLine(0);
			return;
		}
		if (p == 2) {
			int[] seq = new int[n];
			int cur = 1;
			int dir = 2;
			for (int i = 0; i < n; ++i) {
				seq[i] = cur;
				if (cur + dir <= n) {
					cur += dir;
				} else if (cur < n) {
					++cur;
					dir = -2;
				} else {
					--cur;
					dir = -2;
				}
			}
			boolean ok = true;
			int res = 0;
			for (int i = 0; i < n; ++i) {
				int a = seq[i];
				int b = seq[(i + 1) % n];
				if (Math.abs(a - b) > p) throw new RuntimeException();
				if ((dislikeMask[a] & (1 << (b - a + p))) != 0) {
					ok = false;
				}
			}
			if (ok) ++res;
			ok = true;
			for (int i = 0; i < n; ++i) {
				int a = seq[i];
				int b = seq[(i + n - 1) % n];
				if ((dislikeMask[a] & (1 << (b - a + p))) != 0) {
					ok = false;
				}
			}
			if (ok) ++res;
			out.printLine(res);
			return;
		}
		if (p != 3) throw new RuntimeException();

		int[] a = new int[n + 1];
		int[] b = new int[n + 1];
		int[] c = new int[n + 1];
		int[] d = new int[n + 1];
		int[] e = new int[n + 1];
		int[] f = new int[n + 1];
		int[] g = new int[n + 1];
		int[] h = new int[n + 1];

		if ((dislikeMask[n] & (1 << (n - 1 - n + p))) == 0) {
			++b[n - 1];
		}

		if ((dislikeMask[n - 1] & (1 << (n - (n - 1) + p))) == 0) {
			++a[n - 1];
		}

		if ((dislikeMask[n] & (1 << (n - 2 - n + p))) == 0 && (dislikeMask[n - 3] & (1 << (n - (n - 3) + p))) == 0) {
			++g[n - 3];
		}

		if ((dislikeMask[n] & (1 << (n - 3 - n + p))) == 0 && (dislikeMask[n - 2] & (1 << (n - (n - 2) + p))) == 0) {
			++h[n - 3];
		}

		for (int i = n - 1; i >= 1; --i) {
			if (e[i] != 0 && (dislikeMask[i + 2] & (1 << (-1 + p))) == 0) {
				a[i] += e[i];
				if (a[i] >= MODULO) a[i] -= MODULO;
			}
			if (e[i] != 0 && i - 1 >= 1 && (dislikeMask[i + 2] & (1 << (-3 + p))) == 0) {
				h[i - 1] += e[i];
				if (h[i - 1] >= MODULO) h[i - 1] -= MODULO;
			}
			if (f[i] != 0 && (dislikeMask[i + 1] & (1 << (1 + p))) == 0) {
				b[i] += f[i];
				if (b[i] >= MODULO) b[i] -= MODULO;
			}
			if (f[i] != 0 && i - 1 >= 1 && (dislikeMask[i - 1] & (1 << (3 + p))) == 0) {
				g[i - 1] += f[i];
				if (g[i - 1] >= MODULO) g[i - 1] -= MODULO;
			}
			if (g[i] != 0 && (dislikeMask[i + 1] & (1 << (1 + p))) == 0) {
				c[i] += g[i];
				if (c[i] >= MODULO) c[i] -= MODULO;
			}
			if (g[i] != 0 && i - 2 >= 1 && (dislikeMask[i + 1] & (1 << (-3 + p))) == 0 && (dislikeMask[i + 2] & (1 << (-2 + p))) == 0 && (dislikeMask[i - 1] & (1 << (3 + p))) == 0) {
				b[i - 2] += g[i];
				if (b[i - 2] >= MODULO) b[i - 2] -= MODULO;
			}
			if (h[i] != 0 && (dislikeMask[i + 2] & (1 << (-1 + p))) == 0) {
				d[i] += h[i];
				if (d[i] >= MODULO) d[i] -= MODULO;
			}
			if (h[i] != 0 && i - 2 >= 1 && (dislikeMask[i - 2] & (1 << (3 + p))) == 0 && (dislikeMask[i] & (1 << (2 + p))) == 0 && (dislikeMask[i + 2] & (1 << (-3 + p))) == 0) {
				a[i - 2] += h[i];
				if (a[i - 2] >= MODULO) a[i - 2] -= MODULO;
			}
			if (a[i] != 0 && i - 1 >= 1 && (dislikeMask[i + 1] & (1 << (-2 + p))) == 0) {
				b[i - 1] += a[i];
				if (b[i - 1] >= MODULO) b[i - 1] -= MODULO;
			}
			if (a[i] != 0 && i - 1 >= 2 && (dislikeMask[i + 1] & (1 << (-3 + p))) == 0) {
				f[i - 2] += a[i];
				if (f[i - 2] >= MODULO) f[i - 2] -= MODULO;
			}
			if (b[i] != 0 && i - 1 >= 1 && (dislikeMask[i - 1] & (1 << (2 + p))) == 0) {
				a[i - 1] += b[i];
				if (a[i - 1] >= MODULO) a[i - 1] -= MODULO;
			}
			if (b[i] != 0 && i - 1 >= 2 && (dislikeMask[i - 2] & (1 << (3 + p))) == 0) {
				e[i - 2] += b[i];
				if (e[i - 2] >= MODULO) e[i - 2] -= MODULO;
			}
			if (c[i] != 0 && i - 1 >= 1 && (dislikeMask[i + 2] & (1 << (-3 + p))) == 0) {
				b[i - 1] += c[i];
				if (b[i - 1] >= MODULO) b[i - 1] -= MODULO;
			}
			if (d[i] != 0 && i - 1 >= 1 && (dislikeMask[i - 1] & (1 << (3 + p))) == 0) {
				a[i - 1] += d[i];
				if (a[i - 1] >= MODULO) a[i - 1] -= MODULO;
			}
		}

		long res = 0;
		if (a[1] != 0 && (dislikeMask[2] & (1 << (-1 + p))) == 0)
			res += a[1];
		if (b[1] != 0 && (dislikeMask[1] & (1 << (1 + p))) == 0)
			res += b[1];
		if (c[1] != 0 && (dislikeMask[3] & (1 << (-2 + p))) == 0)
			res += c[1];
		if (d[1] != 0 && (dislikeMask[1] & (1 << (2 + p))) == 0)
			res += d[1];
		if (g[2] != 0 && (dislikeMask[3] & (1 << (-2 + p))) == 0 && (dislikeMask[1] & (1 << (3 + p))) == 0 && (dislikeMask[4] & (1 << (-2 + p))) == 0)
			res += g[2];
		if (h[2] != 0 && (dislikeMask[2] & (1 << (2 + p))) == 0 && (dislikeMask[4] & (1 << (-3 + p))) == 0 && (dislikeMask[1] & (1 << (2 + p))) == 0)
			res += h[2];
		out.printLine(res % MODULO);

		/*for (int i = 1; i <= n; ++i) {
			out.printLine(a[i], b[i], c[i], d[i], e[i], f[i], g[i], h[i]);
		}*/
	}
}
