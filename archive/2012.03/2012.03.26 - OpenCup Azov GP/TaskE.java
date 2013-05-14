package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	long knight(long n, long x) {
		return (n - x % 4 - 1) / 4;
	}

	long bishop(long w, long a, long b) {
		if (a < b) {
			long c = a; a = b; b = c;
		}
		long h = a + b + 1;
		long ans = 0;
		ans += count(-b, 0, w, b + 2, false, 2 * a + b + 2, false, 2 * h - 2);
		ans += count(2, a - b, w, 4, true, 2 * a, false, 2 * h - 2);
		ans += count(a - b, a + 1, w, a - b + 2, true, a + b + 2, true, 2 * h - 2);
		return ans;
	}

	long count(long from, long to, long w, long x1, boolean slash1, long x2, boolean slash2, long period) {
		int dx1 = slash1 ? 1 : -1;
		int dx2 = slash2 ? 1 : -1;
		if (from == 0) {
			from += 2;
			x1 += 2 * dx1;
			x2 += 2 * dx2;
		}
		if (from % 2 != 0) {
			from++;
			x1 += dx1;
			x2 += dx2;
		}
		if (to % 2 != 0) {
			to++;
		}
		if (from >= to) {
			return 0;
		}
		if ((x2 - x1) % 2 != 0) {
			throw new Error("My kozly");
		}
		long a0 = (x2 - x1) / 2;
		long d0 = dx2 - dx1;
		long num = (to - from) / 2;
		long inPeriod = (a0 + a0 + d0 * (num - 1)) * num / 2;
		long x2r = slash2 ? (x2 + to - from - 4) : (x2 - 2);
		long full = (w - x2r + period) / period;
		full = Math.max(full, 0);
		long ans = full * inPeriod;
		w -= full * period;
		long x1r = slash1 ? (x1 + (to - from - 2)) : (x1);
		long x1l = slash1 ? (x1) : (x1 - (to - from - 2));
		     x2r = slash2 ? (x2 + (to - from - 2)) : (x2);
		long x2l = slash2 ? (x2) : (x2 - (to - from - 2));
		if (w % 2 != 0) {
			w--;
		}
		if (w < x1l) {
			return ans;
		}
		if (w < x1r) {
			long n = (w - x1l + 2) / 2;
			return ans + n * (n + 1) / 2;
		}
		if (w < x2l) {
			a0 = (w - x1 + 2) / 2;
			d0 = - dx1;
			return ans + (a0 + a0 + d0 * (num - 1)) * num / 2;
		}
		long n = (x2r - w - 2) / 2;
		return ans + inPeriod - n * (n + 1) / 2;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readInt();
		long m = in.readInt();
		long x = in.readInt() - 1;
		long y = in.readInt() - 1;
//		out.printLine(stupid((int)n, (int)m, (int)x, (int)y));
		long even = 0;
		if (n == 2) {
			even += knight(m, y);
		}
		if (m == 2) {
			even += knight(n, x);
		}
		even += bishop(n - 1 - x, y, m - 1 - y);
		even += bishop(x, y, m - 1 - y);
		even += bishop(m - 1 - y, x, n - 1 - x);
		even += bishop(y, x, n - 1 - x);
		out.printLine(n * m - even - 1);
	}

	private static int stupid(int n, int m, int x, int y) {
		boolean[][] good = new boolean[n][m];
		process(good, x, y, new King());
		process(good, x, y, new Queen());
		process(good, x, y, new Rock());
		process(good, x, y, new Bishop());
		process(good, x, y, new Knight());
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (good[i][j])
					result++;
			}
		}
		return result;
	}

	private static void process(boolean[][] good, int x, int y, Piece piece) {
		int rowCount = good.length;
		int columnCount = good[0].length;
		int[][] distance = new int[rowCount][columnCount];
		for (int[] row : distance)
			Arrays.fill(row, Integer.MAX_VALUE - 1);
		distance[x][y] = 0;
		int[] queueRow = new int[rowCount * columnCount];
		int[] queueColumn = new int[rowCount * columnCount];
		int size = 1;
		queueRow[0] = x;
		queueColumn[0] = y;
		for (int i = 0; i < size; i++) {
			int row = queueRow[i];
			int column = queueColumn[i];
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (distance[j][k] > distance[row][column] + 1 && piece.canMove(row, column, j, k)) {
						distance[j][k] = distance[row][column] + 1;
						queueRow[size] = j;
						queueColumn[size++] = k;
					}
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (distance[i][j] % 2 == 1)
					good[i][j] = true;
			}
		}
	}

	static abstract class Piece {
		public boolean canMove(int x, int y, int x0, int y0) {
			return x != x0 || y != y0;
		}
	}

	static class King extends Piece {
		public boolean canMove(int x, int y, int x0, int y0) {
			return super.canMove(x, y, x0, y0) && Math.abs(x - x0) <= 1 && Math.abs(y - y0) <= 1;
		}
	}

	static class Queen extends Piece {
		public boolean canMove(int x, int y, int x0, int y0) {
			return super.canMove(x, y, x0, y0) && (x == x0 || y == y0 || x - y == x0 - y0 || x + y == x0 + y0);
		}
	}

	static class Rock extends Piece {
		public boolean canMove(int x, int y, int x0, int y0) {
			return super.canMove(x, y, x0, y0) && (x == x0 || y == y0);
		}
	}

	static class Bishop extends Piece {
		public boolean canMove(int x, int y, int x0, int y0) {
			return super.canMove(x, y, x0, y0) && (x - y == x0 - y0 || x + y == x0 + y0);
		}
	}

	static class Knight extends Piece {
		public boolean canMove(int x, int y, int x0, int y0) {
			return super.canMove(x, y, x0, y0) && Math.abs(x - x0) + Math.abs(y - y0) == 3 && x != x0 && y != y0;
		}
	}

}
