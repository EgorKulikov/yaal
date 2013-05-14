package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
	private int zz;
	private int n;
	boolean[] c;
	private boolean[] st;
	private boolean[] fn;
	private int[] p;
	List<boolean[]> res;


	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		st = new boolean[n];
		fn = new boolean[n];
		c = new boolean[n];
		int k = in.readInt();
		for (int i = 0; i < k; i++) {
			st[in.readInt() - 1] = true;
		}
		k = in.readInt();
		for (int i = 0; i < k; i++) {
			fn[in.readInt() - 1] = true;
		}
		boolean[] d = new boolean[n];
		for (int i = 0; i < n; i++) {
			d[i] = st[i] ^ fn[i];
		}
		p = new int[n];
		int s = 0;
		for (int i = 0; i < n; i++) {
			if (d[i]) {
				p[i] = s;
				s++;
			}
		}
		zz = s;
		for (int i = 0; i < n; i++) {
			if (!d[i]) {
				p[i] = s;
				s++;
			}
		}

		res = new ArrayList<boolean[]>();
		if (zz == 0) {
			bt(0, 0);
			res.remove(res.size() - 1);
		} else if (zz % 2 == 1) {
			bt(0, 0);
		} else {
			zz--;
			bt(0, 0);
			res.remove(res.size() - 1);
			int ii = 0;
			int jj = 0;
			for (int i = 0; i < n; i++) {
				if (p[i] == n - 1) ii = i;
				if (p[i] == zz) jj = i;
			}

			int t = p[ii];
			p[ii] = p[jj];
			p[jj] = t;
		}
		out.printLine(res.size());
		int[] c = new int[n];
		for (boolean[] booleans : res) {
			s = 0;
			for (int i = 0; i < n; i++) {
				boolean cc = booleans[p[i]] ^ st[i];
				if (cc) {
					c[s++] = i;
				}
			}
			out.print(s + " ");
			for (int i = 0; i < s; i++) {
				out.print((c[i] + 1) + " ");
			}
			out.printLine();
		}
	}

	private void bt(int i, int j) {
		if (i == n) {
			res.add(c.clone());
			return;
		}
		if (i >= zz) {
			if (j == 0) {
				c[i] = false;
				bt(i + 1, 0);
				c[i] = true;
				bt(i + 1, 1);
			} else {
				c[i] = true;
				bt(i + 1, 0);
				c[i] = false;
				bt(i + 1, 1);
			}
		} else {
			if (i == 0) {
				c[i] = false;
				bt(i + 1, 0);
				c[i] = true;
				bt(i + 1, 2);
			} else {
				if (j == 0) {
					c[i] = false;
					c[i + 1] = false;
					bt(i + 2, 0);
					c[i] = false;
					c[i + 1] = true;
					bt(i + 2, 1);
					c[i] = true;
					c[i + 1] = true;
					bt(i + 2, 0);
					c[i] = true;
					c[i + 1] = false;
					bt(i + 2, 1);
				} else if (j == 1) {
					c[i] = true;
					c[i + 1] = false;
					bt(i + 2, 0);
					c[i] = true;
					c[i + 1] = true;
					bt(i + 2, 1);
					c[i] = false;
					c[i + 1] = true;
					bt(i + 2, 0);
					c[i] = false;
					c[i + 1] = false;
					bt(i + 2, 1);
				} else {
					c[i] = true;
					c[i + 1] = false;
					bt(i + 2, 0);
					c[i] = false;
					c[i + 1] = false;
					bt(i + 2, 1);
					c[i] = false;
					c[i + 1] = true;
					bt(i + 2, 0);
					c[i] = true;
					c[i + 1] = true;
					bt(i + 2, 2);
				}
			}
		}
	}
}
