package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	static final int MAX_HEAP = (int) 5e6;

	long[] heap;
	int nheap;
	int ndead;
	int[][] best;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rows = in.readInt();
		int columns = in.readInt();
		String[] field = new String[rows];
		for (int i = 0; i < rows; ++i) {
			field[i] = in.readString();
		}
		best = new int[rows][columns];
		for (int[] x : best) Arrays.fill(x, Integer.MAX_VALUE);
		heap = new long[MAX_HEAP];

		nheap = 0;
		ndead = 0;
		for (int r = 0; r < rows; ++r)
			for (int c = 0; c < columns; ++c)
				if (field[r].charAt(c) == '1') {
					reach(r, c, r, c);
				}
		long last = -1;
		while (nheap > 0) {
			long cur = heap[0];
			if (nheap > 1) {
				swap(0, nheap - 1);
				--nheap;
				heapDown(0);
			} else {
				--nheap;
			}
			if (cur == last) continue;
			last = cur;
			int r, c, sr, sc;
			{
				long pos = cur;
				int mask = (1 << 15) - 1;
				r = (int) ((pos >> 45) & mask);
				c = (int) ((pos >> 30) & mask);
				sr = (int) ((pos >> 15) & mask);
				sc = (int) (pos & mask);
			}
			int x = (r - sr) * (r - sr) + (c - sc) * (c - sc);
			for (int dr = -1; dr <= 1; ++dr)
				for (int dc = -1; dc <= 1; ++dc)
					if (Math.abs(dr) + Math.abs(dc) == 1) {
						int nr = r + dr;
						int nc = c + dc;
						if (nr >= 0 && nr < rows && nc >= 0 && nc < columns) {
							int nx = (nr - sr) * (nr - sr) + (nc - sc) * (nc - sc);
							if (nx > x) {
								reach(nr, nc, sr, sc);
							}
						}
					}

		}
		for (int r = 0; r < rows; ++r)
			out.printLine(best[r]);
    }

	private void heapDown(int at) {
		while (true) {
			int i = at;
			if (2 * at + 1 < nheap) {
				long vi = getVal(heap[2 * at + 1]);
				long vat = getVal(heap[i]);
				if (vi < vat || vi == vat && heap[2 * at + 1] < heap[i]) {
					i = 2 * at + 1;
				}
			}
			if (2 * at + 2 < nheap) {
				long vi = getVal(heap[2 * at + 2]);
				long vat = getVal(heap[i]);
				if (vi < vat || vi == vat && heap[2 * at + 2] < heap[i]) {
					i = 2 * at + 2;
				}
			}
			if (i == at) break;
			swap(i, at);
			at = i;
		}
	}

	private void reach(int r, int c, int sr, int sc) {
		int x = (r - sr) * (r - sr) + (c - sc) * (c - sc);
		int y = best[r][c];
		if (!better(x, y)) return;
		if (x < y) best[r][c] = x;
		heap[nheap] = ((((((long) r << 15) + c) << 15) + sr) << 15) + sc;
		++nheap;
		heapUp(nheap - 1);
	}

	private void heapUp(int at) {
		while (at > 0) {
			int i = (at - 1) / 2;
			long vi = getVal(heap[i]);
			long vat = getVal(heap[at]);
			if (vi < vat || vi == vat && heap[i] < heap[at]) break;
			swap(i, at);
			at = i;
		}
	}

	private void swap(int a, int b) {
		long tmp = heap[a];
		heap[a] = heap[b];
		heap[b] = tmp;
	}

	private int getVal(long pos) {
		int mask = (1 << 15) - 1;
		int r = (int) ((pos >> 45) & mask);
		int c = (int) ((pos >> 30) & mask);
		int sr = (int) ((pos >> 15) & mask);
		int sc = (int) (pos & mask);
		int x = (r - sr) * (r - sr) + (c - sc) * (c - sc);
		return x;
	}

	static final int c = 1;

	private boolean better(long x, long y) {
		if (x < y) return true;
		long val = x - y - c * c;
		if (val <= 0) return true;
		return val * val <= 4 * c * c * y;
	}
}
