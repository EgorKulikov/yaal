package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Embedding {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 3) {
			out.printLine("1 2 3 4 5 6");
			out.printLine("1 4 5 3 2 6");
			out.printLine("2 6 1 3 4 5");
			return;
		}
		if (n == 0) throw new UnknownError();
		boolean[][] e = new boolean[2 * n][2 * n];
		for (int i = 0; i < 2* n; ++i) e[i][i] = true;
		int[][] res = new int[6][n + 1];
		if (!rec(e, res, 0, 0, n + 1)) throw new RuntimeException();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < n + 1; ++j) {
				if (j > 0) out.print(" ");
				out.print(res[i][j] + 1);
			}
			for (int j = 1; j < n; ++j) {
				out.print(" ");
				out.print(res[i + 3][j] + 1);
			}
			out.printLine();
		}

    }

	int ptr = 0;

	private boolean rec(boolean[][] e, int[][] res, int block, int what, int len) {
		if (what >= len) {
			return rec(e, res, block + 1, 0, len);
		}
		if (block >= 3) {
			return single(e, res, 0, 1, len);
		}
		for (int i = 0; i < e.length; ++i) {
			ptr = (ptr + 1) % e.length;
			boolean ok = true;
			for (int j = 0; j < what; ++j) if (res[block][j] == ptr) ok = false;
			if (!ok) continue;
			if (what == 0 || !e[res[block][what - 1]][ptr]) {
				if (what != 0) {
					e[res[block][what - 1]][ptr] = true;
					e[ptr][res[block][what - 1]] = true;
				}
				res[block][what] = ptr;
				if (rec(e, res, block, what + 1, len)) return true;
				if (what != 0) {
					e[res[block][what - 1]][ptr] = false;
					e[ptr][res[block][what - 1]] = false;
				}
			}
		}
		return false;
	}

	private boolean single(boolean[][] e, int[][] res, int block, int what, int len) {
		if (block >= 3) {
			return true;
		}
		if (what >= len - 1) {
			return single(e, res, block + 1, 1, len);
		}
		for (int i = 0; i < e.length; ++i) {
			ptr = (ptr + 1) % e.length;
			boolean ok = true;
			for (int j = 0; j < res[block].length; ++j)
				if (res[block][j] == ptr) ok = false;
			for (int t = 1; t < what; ++t)
				if (res[block + 3][t] == ptr) ok = false;
			if (!ok) continue;
			if (!e[res[block][what]][ptr]) {
				e[res[block][what]][ptr] = true;
				e[ptr][res[block][what]] = true;
				res[block + 3][what] = ptr;
				if (single(e, res, block, what + 1, len)) return true;
				e[res[block][what]][ptr] = false;
				e[ptr][res[block][what]] = false;
			}
		}
		return false;
	}
}
