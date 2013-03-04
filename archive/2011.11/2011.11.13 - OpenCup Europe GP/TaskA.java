package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int height = in.readInt();
		int width = in.readInt();
		int[][] a = new int[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				a[i][j] = in.readInt();
			}
		}
		int[][] aTransposed = new int[width][height];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				aTransposed[j][i] = a[i][j];
			}
		}
		int res = 1;
		res = Math.max(res, findHeight1(height, width, a));
		res = Math.max(res, findHeight1(width, height, aTransposed));
		res = Math.max(res, findHeight2(height, width, a));
		res = Math.max(res, findHeight2(width, height, aTransposed));
		res = Math.max(res, findAtLeast3(height, width, a));
		out.printLine(res);
	}

	private int findAtLeast3(int height, int width, int[][] a) {
		if (width <= 2 || height <= 2) return 0;
		boolean[][] d = new boolean[height - 2][width - 2];
		for (int r = 0; r < height - 2; ++r)
			for (int c = 0; c < width - 2; ++c) {
				if (a[r][c] - 2 * a[r][c + 1] + a[r][c + 2] == 0
					&& a[r + 1][c] - 2 * a[r + 1][c + 1] + a[r + 1][c + 2] == 0
					&& a[r + 2][c] - 2 * a[r + 2][c + 1] + a[r + 2][c + 2] == 0
					&& a[r][c] - 2 * a[r + 1][c] + a[r + 2][c] == 0
					&& a[r][c + 1] - 2 * a[r + 1][c + 1] + a[r + 2][c + 1] == 0
					&& a[r][c + 2] - 2 * a[r + 1][c + 2] + a[r + 2][c + 2] == 0) {
					d[r][c] = true;
				}
			}
		int[][] cntUp = new int[height - 2][width - 2];
		for (int r = 0; r < height - 2; ++r)
			for (int c = 0; c < width - 2; ++c) {
				if (r > 0)
					cntUp[r][c] = cntUp[r - 1][c];
				if (d[r][c])
					++cntUp[r][c];
				else
					cntUp[r][c] = 0;
			}
		int res = 0;
		for (int bottomRow = 0; bottomRow < height - 2; ++bottomRow) {
			res = Math.max(res, solveFor(cntUp[bottomRow]));
		}
		return res;
	}

	private int solveFor(int[] heights) {
		int maxHeight = 0;
		for (int x : heights) maxHeight = Math.max(maxHeight, x);
		int[] first = new int[maxHeight + 1];
		Arrays.fill(first, -1);
		int[] next = new int[heights.length];
		for (int i = 0; i < heights.length; ++i) {
			next[i] = first[heights[i]];
			first[heights[i]] = i;
		}
		int[] leftToRight = new int[heights.length];
		int[] rightToLeft = new int[heights.length];
		for (int i = 0; i < heights.length; ++i) {
			leftToRight[i] = -1;
			rightToLeft[i] = -1;
		}
		int res = 0;
		for (int h = maxHeight; h >= 1; --h) {
			int cur = first[h];
			while (cur >= 0) {
				int left = cur;
				int right = cur;
				if (cur - 1 >= 0 && rightToLeft[cur - 1] >= 0) {
					left = rightToLeft[cur - 1];
					rightToLeft[cur - 1] = -1;
				}
				if (cur + 1 < heights.length && leftToRight[cur + 1] >= 0) {
					right = leftToRight[cur + 1];
					leftToRight[cur + 1] = -1;
				}
				leftToRight[left] = right;
				rightToLeft[right] = left;
				res = Math.max(res, (h + 2) * (right - left + 3));
				cur = next[cur];
			}
		}
		return res;
	}

	private int findHeight2(int height, int width, int[][] a) {
		if (width <= 1 || height <= 1) return 0;
		int res = 0;
		for (int r = 0; r + 1 < height; ++r) {
			int[] delta1 = new int[width - 1];
			for (int c = 0; c + 1 < width; ++c)
				delta1[c] = a[r][c + 1] - a[r][c];
			int[] delta2 = new int[width - 1];
			for (int c = 0; c + 1 < width; ++c)
				delta2[c] = a[r + 1][c + 1] - a[r + 1][c];
			int cnt = 0;
			for (int i = 0; i < delta1.length; ++i) {
				if (i != 0 && (delta1[i] != delta1[i - 1] || delta2[i] != delta2[i - 1]))
					cnt = 0;
				++cnt;
				res = Math.max(res, 2 * (cnt + 1));
			}
		}
		return res;
	}

	private int findHeight1(int height, int width, int[][] a) {
		if (width <= 1) return 0;
		int res = 0;
		for (int r = 0; r < height; ++r) {
			int[] delta = new int[width - 1];
			for (int c = 0; c + 1 < width; ++c)
				delta[c] = a[r][c + 1] - a[r][c];
			int cnt = 0;
			for (int i = 0; i < delta.length; ++i) {
				if (i != 0 && delta[i] != delta[i - 1])
					cnt = 0;
				++cnt;
				res = Math.max(res, cnt + 1);
			}
		}
		return res;
	}
}
