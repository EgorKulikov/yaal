package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class KnightMoving {
	long[] factorial = IntegerUtils.generateFactorial(1000001, MOD);
	long[] reverseFactorial = IntegerUtils.generateReverseFactorials(1000001, MOD);

	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int blockedCount = in.readInt();
		int ax = in.readInt();
		int ay = in.readInt();
		int bx = in.readInt();
		int by = in.readInt();
		int[] xBlocked = new int[blockedCount];
		int[] yBlocked = new int[blockedCount];
		IOUtils.readIntArrays(in, xBlocked, yBlocked);
		if (ax * by == ay * bx) {
			if (x * by != y * bx || x * ay != y * ax) {
				out.printLine(0);
				return;
			}
			int dx = IntegerUtils.gcd(ax, bx);
			int dy = IntegerUtils.gcd(ay, by);
			if (dx == 0 && dy == 0) {
				if (x == 0 && y == 0) {
					out.printLine(-1);
					return;
				}
				out.printLine(0);
				return;
			}
			if (x < 0)
				dx = -dx;
			if (y < 0)
				dy = -dy;
			if (dx != 0 && x % dx != 0 || dy != 0 && y % dy != 0) {
				out.printLine(0);
				return;
			}
			int aMove = dx == 0 ? ay / dy : ax / dx;
			int bMove = dx == 0 ? by / dy : bx / dx;
			int length = dx == 0 ? y / dy : x / dx;
			if (ax * bx >= 0 && ay * by >= 0) {
				boolean[] isBlocked = new boolean[length + 1];
				long[] answer = new long[length + 1];
				for (int i = 0; i <= length; i++) {
					int xx = dx * i;
					int yy = dy * i;
					for (int j = 0; j < blockedCount; j++) {
						if (xBlocked[j] == xx && yBlocked[j] == yy) {
							isBlocked[i] = true;
							break;
						}
					}
				}
				if (aMove < 0 && bMove < 0) {
					if (x == 0 && y == 0) {
						out.printLine(1);
						return;
					}
					out.printLine(0);
					return;
				}
				if (aMove < 0 || bMove < 0) {
					if (x == 0 && y == 0) {
						out.printLine(-1);
						return;
					}
					out.printLine(0);
					return;
				}
				answer[0] = 1;
				for (int i = 0; i < length; i++) {
					if (isBlocked[i])
						continue;
					if (aMove != 0 && i + aMove <= length)
						answer[i + aMove] = (answer[i + aMove] + answer[i]) % MOD;
					if (bMove != 0 && aMove != bMove && i + bMove <= length)
						answer[i + bMove] = (answer[i + bMove] + answer[i]) % MOD;
				}
				if ((aMove == 0 || bMove == 0) && answer[length] != 0)
					answer[length] = -1;
				out.printLine(answer[length]);
				return;
			}
			if (aMove < 0) {
				int temp = aMove;
				aMove = bMove;
				bMove = temp;
			}
			boolean[] isBlocked = new boolean[2001];
			long[] current = new long[2001];
			long[] next = new long[2001];
			for (int i = -1000; i <= 1000; i++) {
				for (int j = 0; j < blockedCount; j++) {
					if (i * dx == xBlocked[j] && i * dy == yBlocked[j])
						isBlocked[i + 1000] = true;
				}
			}
			long answer = 0;
			boolean leftReached = false;
			boolean rightReached = false;
			int finish = (dx == 0 ? y / dy : x / dx) + 1000;
			current[1000] = 1;
			if (current[finish] == 1)
				answer = 1;
			for (int i = 0; i < 5000; i++) {
				for (int j = 0; j <= 2000; j++) {
					if (current[j] == 0 || isBlocked[j])
						continue;
					if (j < 500 && current[j] != 0)
						leftReached = true;
					if (j > 1500 && current[j] != 0)
						rightReached = true;
					if (leftReached && j < 500)
						current[j] = 1;
					if (rightReached && j > 1500)
						current[j] = 1;
					if (j + aMove <= 2000)
						next[j + aMove] += current[j];
					if (j >= -bMove)
						next[j + bMove] += current[j];
				}
				answer += next[finish];
				for (int j = 0; j <= 2000; j++) {
					current[j] = next[j];
					if (current[j] >= MOD)
						current[j] -= MOD;
					next[j] = 0;
				}
			}
			for (int i = 0; i < 5000; i++) {
				for (int j = 0; j <= 2000; j++) {
					if (current[j] == 0 || isBlocked[j])
						continue;
					if (j < 500)
						leftReached = true;
					if (j > 1500)
						rightReached = true;
					if (leftReached && j < 500)
						current[j] = 1;
					if (rightReached && j > 1500)
						current[j] = 1;
					if (j + aMove <= 2000)
						next[j + aMove] += current[j];
					if (j >= -bMove)
						next[j + bMove] += current[j];
				}
				if (next[finish] != 0) {
					out.printLine(-1);
					return;
				}
				for (int j = 0; j <= 2000; j++) {
					current[j] = next[j];
					if (current[j] >= MOD)
						current[j] -= MOD;
					next[j] = 0;
				}
			}
			out.printLine(answer % MOD);
			return;
		}
		int[] allX = new int[blockedCount + 2];
		int[] allY = new int[blockedCount + 2];
		System.arraycopy(xBlocked, 0, allX, 2, blockedCount);
		System.arraycopy(yBlocked, 0, allY, 2, blockedCount);
		allX[0] = x;
		allY[0] = y;
		int index = 0;
		for (int i = 0; i < allX.length; i++) {
			int d = ax * by - ay * bx;
			int f = (allX[i] * by - allY[i] * bx) / d;
			int s = (ax * allY[i] - allX[i] * ay) / d;
			if (f * ax + s * bx == allX[i] && f * ay + s * by == allY[i] && f >= 0 && s >= 0) {
				allX[index] = f;
				allY[index++] = s;
			} else if (i == 0) {
				out.printLine(0);
				return;
			}
		}
		allX = Arrays.copyOf(allX, index);
		allY = Arrays.copyOf(allY, index);
		long[][] answer = new long[allX.length][allX.length];
		ArrayUtils.fill(answer, -1);
		out.printLine(go(0, 1, answer, allX, allY));
	}

	private long go(int from, int to, long[][] answer, int[] x, int[] y) {
		if (answer[from][to] != -1)
			return answer[from][to];
		answer[from][to] = c(x[from] - x[to], y[from] - y[to]);
		for (int i = 0; i < x.length; i++) {
			if (from != i && to != i && x[from] >= x[i] && y[from] >= y[i] && x[to] <= x[i] && y[to] <= y[i])
				answer[from][to] -= go(i, to, answer, x, y) * c(x[from] - x[i], y[from] - y[i]) % MOD;
		}
		answer[from][to] %= MOD;
		answer[from][to] += MOD;
		answer[from][to] %= MOD;
		return answer[from][to];
	}

	private long c(int x, int y) {
		return factorial[x + y] * reverseFactorial[x] % MOD * reverseFactorial[y] % MOD;
	}
}
