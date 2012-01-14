package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int k = in.readInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; ++i) {
			x[i] = in.readInt();
			y[i] = -in.readInt();
		}
		int[] nextRight = new int[n];
		int[] nextBottom = new int[n];
		Arrays.fill(nextRight, -1);
		Arrays.fill(nextBottom, -1);
		for (int i = 0; i < m; ++i) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			if (x[a] < x[b]) {
				nextRight[a] = b;
			} else if (x[b] < x[a]) {
				nextRight[b] = a;
			} else if (y[a] < y[b]) {
				nextBottom[a] = b;
			} else if (y[b] < y[a]) {
				nextBottom[b] = a;
			} else throw new RuntimeException();
		}
		int[] nextRightmost = new int[n];
		buildRightmost(nextRight, nextBottom, nextRightmost);
		int[] nextBottommost = new int[n];
		buildRightmost(nextBottom, nextRight, nextBottommost);
		int[] rightmostDepth = new int[n];
		int[] rightmostBigJump = new int[n];
		buildBigJumps(nextRightmost, rightmostDepth, rightmostBigJump);
		int[] bottommostDepth = new int[n];
		int[] bottommostBigJump = new int[n];
		buildBigJumps(nextBottommost, bottommostDepth, bottommostBigJump);
		for (int i = 0; i < k; ++i) {
			int p = in.readInt() - 1;
			int q = in.readInt() - 1;
			if (x[p] > x[q]) {
				int tmp = p;
				p = q;
				q = tmp;
			}
			if (y[p] > y[q]) {
				int tmp = p;
				p = q;
				q = tmp;
			}
			if (x[p] > x[q]) {
				out.printLine("NIE");
			} else {
				if (goesOver(p, q, x, y, nextRightmost, rightmostDepth, rightmostBigJump))
					out.printLine("NIE");
				else if (goesOver(p, q, y, x, nextBottommost, bottommostDepth, bottommostBigJump))
					out.printLine("NIE");
				else
					out.printLine("TAK");
			}
		}
	}

	private boolean goesOver(int p, int q, int[] x, int[] y, int[] nextRightmost, int[] rightmostDepth,
		int[] rightmostBigJump) {
		while (p < nextRightmost.length - 1 && y[p] <= y[q]) {
			int candidate = rightmostBigJump[p];
			if (y[candidate] <= y[q])
				p = candidate;
			else
				p = nextRightmost[p];
		}
		if (x[p] < x[q])
			return true;
		else
			return false;
	}

	int[] next;
	int[] depth;
	int[] bigJump;

	private void buildBigJumps(int[] next, int[] depth, int[] bigJump) {
		this.next = next;
		this.depth = depth;
		this.bigJump = bigJump;
		Arrays.fill(depth, -1);
		for (int i = 0; i < next.length; ++i)
			doitBig(i);
		this.next = null;
		this.depth = null;
		this.bigJump = null;
	}

	private void doitBig(int pos) {
		if (depth[pos] >= 0) return;
		if (pos == next.length - 1) {
			depth[pos] = -1;
			return;
		}
		doitBig(next[pos]);
		depth[pos] = depth[next[pos]] + 1;
		int bigJumpBy = depth[pos] - ((depth[pos] & (depth[pos] + 1)) - 1);
		bigJump[pos] = jumpBy(next, depth, bigJump, next[pos], bigJumpBy - 1);
	}

	private int jumpBy(int[] next, int[] depth, int[] bigJump, int pos, int by) {
		if (by == 0)
			return pos;
		int d = depth[pos];
		int bigBy = d - ((d & (d + 1)) - 1);
		if (bigBy <= by)
			return jumpBy(next, depth, bigJump, bigJump[pos], by - bigBy);
		else
			return jumpBy(next, depth, bigJump, next[pos], by - 1);
	}

	int[] nextRight;
	int[] nextBottom;
	int[] nextRightmost;

	private void buildRightmost(int[] nextRight, int[] nextBottom, int[] nextRightmost) {
		this.nextRight = nextRight;
		this.nextBottom = nextBottom;
		this.nextRightmost = nextRightmost;
		Arrays.fill(nextRightmost, -1);
		for (int i = 0; i < nextRight.length; ++i)
			doit(i);
		this.nextRight = null;
		this.nextBottom = null;
		this.nextRightmost = null;
	}

	private void doit(int pos) {
		if (nextRightmost[pos] >= 0)
			return;
		if (pos == nextRightmost.length - 1)
			return;
		if (nextRight[pos] >= 0)
			nextRightmost[pos] = nextRight[pos];
		else if (nextBottom[pos] >= 0)
			nextRightmost[pos] = nextBottom[pos];
		else
			throw new RuntimeException();
	}
}
