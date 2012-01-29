package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Borders {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String w = in.next() + (char) ('a' - 1);
		int n = w.length();
		int[] perm = new int[n];
		int[] id = new int[n];
		int[] cnt = new int[Math.max(n, 27)];
		for (int i = 0; i < n; ++i) {
			++cnt[w.charAt(i) - 'a' + 1];
			id[i] = w.charAt(i) - 'a' + 1;
		}
		int[] ptr = new int[Math.max(n, 27)];
		for (int i = 1; i < n; ++i) {
			ptr[i] = ptr[i - 1] + cnt[i - 1];
		}
		for (int i = 0; i < n; ++i) {
			perm[ptr[id[i]]++] = i;
		}
		int[] nperm = new int[n];
		int[] nid = new int[n];
		for (int len = 1; len < n; len *= 2) {
			Arrays.fill(cnt, 0);
			for (int i = 0; i < n; ++i) ++cnt[id[i]];
			ptr[0] = 0;
			for (int i = 1; i < n; ++i) {
				ptr[i] = ptr[i - 1] + cnt[i - 1];
			}
			for (int tm = 0; tm < n; ++tm) {
				int j = perm[tm];
				int i = (j - len + n) % n;
				nperm[ptr[id[i]]++] = i;
			}
			System.arraycopy(nperm, 0, perm, 0, n);
			int lastId = -1;
			for (int i = 0; i < n; ++i) {
				if (i == 0) ++lastId; else {
					int p1 = perm[i];
					int p2 = perm[i - 1];
					int p3 = (p1 + len) % n;
					int p4 = (p2 + len) % n;
					if (id[p1] != id[p2] || id[p3] != id[p4])
						++lastId;
				}
				nid[perm[i]] = lastId;
			}
			System.arraycopy(nid, 0, id, 0, n);
		}
		int[] rperm = new int[n];
		for (int i = 0; i < n; ++i)
			rperm[perm[i]] = i;
		int curLcp = 0;
		int[] lcp = new int[n];
		for (int i = 0; i < n; ++i) {
			int at = rperm[i];
			if (at == n - 1) {
				curLcp = 0;
				continue;
			} else {
				--curLcp;
				if (curLcp < 0) curLcp = 0;
			}
			int p1 = (i + curLcp) % n;
			int p2 = (perm[at + 1] + curLcp) % n;
			while (w.charAt(p1) == w.charAt(p2)) {
				++p1;
				++p2;
				if (p1 >= n) p1 = 0;
				if (p2 >= n) p2 = 0;
				++curLcp;
			}
			lcp[at] = curLcp;
		}
		int[] bornAt = new int[n];
		int[] len = new int[n];
		int sp = 0;
		int slen = 0;
		long res = 0;
		for (int i = 0; i < n; ++i) {
			int clen = n - perm[i];
			if (clen < slen) throw new RuntimeException();
			if (clen > slen) {
				bornAt[sp] = i;
				len[sp] = clen - slen;
				++sp;
				slen = clen;
			}
			clen = lcp[i];
			while (clen < slen) {
				int by = Math.min(slen - clen, len[sp - 1]);
				int times = i - bornAt[sp - 1] + 1;
				res += times * (long) (times - 1) / 2 * (long) by;
				len[sp - 1] -= by;
				slen -= by;
				if (len[sp - 1] == 0) {
					--sp;
				}
			}
		}
		out.printLine(res);
	}
}
