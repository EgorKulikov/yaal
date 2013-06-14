package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskG {
	private char[] c;
	private int[] p;
	private int m;
	private int n;
	private int l;
	private int r;
	private List<String> res;


	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();

		n = in.readInt();
		m = in.readInt();
		l = in.readInt();
		r = in.readInt();

		c = new char[n];
		p = new int[n];

		res = new ArrayList<String>();

		nn = 0;

		bt(0, -1);

		out.printLine("Case " + testNumber + ":");
//		System.err.println(res.size());
		for (String s : res) {
			out.printLine(s);
		}
	}

	int nn;

	private void bt(int i, int ll) {
		if (nn > r) return;
		if (i > 0) {
			if (ll == -1) {
				nn++;
				if (nn >= l && nn <= r) {
					res.add(new String(c, 0, i));
				}
			}
		}
		if (i == n) return;
		for (c[i] = 'a'; c[i] < 'a' + m; c[i]++) {
			if (i == 0) {
				bt(i + 1, -1);
			} else {
				if (c[i] < c[ll + 1]) {
					continue;
				} else if (c[i] == c[ll + 1]) {
					bt(i + 1, ll + 1);
				} else {
					bt(i + 1, -1);
				}
			}
		}
	}
}
