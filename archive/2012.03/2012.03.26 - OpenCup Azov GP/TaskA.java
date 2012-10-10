package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		String s = in.readString();
		boolean[] was = new boolean[256];
		res = 100;
		for (int i = n - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (!was[c]) {
				was[c] = true;
				for (int l = 1; l < 52 && l <= i + 1; l++) {
					String ss = s.substring(i - l + 1, i + 1);
//					System.out.println(ss);
					go(s, ss);
				}
			}
		}
//		System.err.println(c);
		out.printLine(res);
	}

	int res;
	final int[] z = new int[101000];
	int c = 0;

	private int go(String s, String t) {
		c++;
		int l = 0;
		int r = 0;
		int p = 0;
		int res = t.length();
		boolean[] was = new boolean[256];
		int n = s.length() + t.length() + 1;
		for (int i = 1; i < n; i++) {
			if (r > i) {
				z[i] = Math.min(z[i - l], r - i);
			} else {
				z[i] = 0;
			}
			while (i + z[i] < n && charAt(t, s, i + z[i]) == charAt(t, s, z[i])) {
				z[i]++;
			}
			if (i + z[i] > r) {
				l = i;
				r = i + z[i];
			}
			if (i > t.length()) {
				p = Math.max(p, i + z[i]);
				if (p == i) {
					char c = charAt(t, s, i);
					if (!was[c]) {
						was[c] = true;
						res += 2;
						if (res >= this.res)
							return res;
					}
				}
			}
		}
		if (res < this.res) {
			//System.out.println(t + " " + res);
			this.res = res;
		}
		return res;
	}

	private char charAt(String t, String s, int i) {
		if (i < t.length()) return t.charAt(i);
		if (i == t.length()) return '#';
		return s.charAt(i - t.length() - 1);
	}
}
