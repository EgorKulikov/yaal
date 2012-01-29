package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Waste {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int l = in.readInt();
		int s = in.readInt();
		if (n == 0) throw new UnknownError();
		boolean[][] accept = new boolean[n][256];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			String ss = x == 0 ? "" : in.readString();
			for (int j = 0; j < x; j++) {
				accept[i][ss.charAt(j)] = true;
			}
		}
		int[] dest = new int[2 * m];
		int[] next = new int[2 * m];
		int[] len = new int[2 * m];
		int[] first = new int[n];
		Arrays.fill(first, -1);
		for (int i = 0; i < m; i++) {
			int x = in.readInt() - 1;
			int y = in.readInt() - 1;
			int w = in.readInt();
			dest[2 * i] = y;
			len[2 * i] = w;
			next[2 * i] = first[x];
			first[x] = 2 * i;
			dest[2 * i + 1] = x;
			len[2 * i + 1] = w;
			next[2 * i + 1] = first[y];
			first[y] = 2 * i + 1;
		}
		String train = in.readString();
		int[][][] d = new int[l][l][n];
		for (int i = l - 1; i >= 0; i--) {
			for (int j = i; j < l; j++) {
				Arrays.fill(d[i][j], 1000000000);
				for (int k = 0; k < n; k++) {
					if (i == j) {
						if (accept[k][train.charAt(i)]) d[i][j][k] = 0;
					} else {
						for (int c = i; c < j; c++) {
							d[i][j][k] = Math.min(d[i][j][k], d[i][c][k] + d[c + 1][j][k]);
						}
					}
				}
				updateDistances(d[i][j], dest, next, len, first);
			}
		}
		int res = d[0][l - 1][s - 1];
		if (res == 1000000000) {
			out.printLine("-1");
		} else {
			out.printLine(res);
		}

	}

	private void updateDistances(int[] d, int[] dest, int[] next, int[] len, int[] first) {
		int n = d.length;
		int[] heap = new int[n];
		int[] heapAt = new int[n];
		int heapN = 0;
		for (int j = 0; j < n; ++j) {
			int at = j;
			heap[heapN] = j;
			heapAt[j] = heapN;
			++heapN;
			while (at > 0) {
				int i = (at - 1) / 2;
				if (d[heap[i]] <= d[heap[at]]) break;
				int tmp = heap[i];
				heap[i] = heap[at];
				heap[at] = tmp;
				heapAt[heap[i]] = i;
				heapAt[heap[at]] = at;
				at = i;
			}
		}
		while (heapN > 0) {
			int cur = heap[0];
			heapAt[cur] = -1;
			--heapN;
			if (heapN > 0) {
				heap[0] = heap[heapN];
				heapAt[heap[0]] = 0;
				int at = 0;
				while (true) {
					int i = at;
					if (2 * at + 1 < heapN && d[heap[2 * at + 1]] < d[heap[i]]) i = 2 * at + 1;
					if (2 * at + 2 < heapN && d[heap[2 * at + 2]] < d[heap[i]]) i = 2 * at + 2;
					if (i == at) break;
					int tmp = heap[i];
					heap[i] = heap[at];
					heap[at] = tmp;
					heapAt[heap[i]] = i;
					heapAt[heap[at]] = at;
					at = i;
				}
			}
			int edge = first[cur];
			while (edge >= 0) {
				int dst = dest[edge];
				int l = len[edge];
				if (d[dst] > d[cur] + l) {
					d[dst] = d[cur] + l;
					int at = heapAt[dst];
					if (at < 0) throw new RuntimeException();
					while (at > 0) {
						int i = (at - 1) / 2;
						if (d[heap[i]] <= d[heap[at]]) break;
						int tmp = heap[i];
						heap[i] = heap[at];
						heap[at] = tmp;
						heapAt[heap[i]] = i;
						heapAt[heap[at]] = at;
						at = i;
					}
				}
				edge = next[edge];
			}
		}
	}
}
