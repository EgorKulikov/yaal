package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Rendezvous {
	int n;
	int k;
	int[] a;
	int[] firstChild;
	int[] nextSibling;
	int[] x;
	int[] y;
	int[] next;
	int[] first;
	int[] cyclePos;
	boolean[] mark;
	int[] dsu;
	int[] numSeen;
	int[] depth;
	int[] ansX;
	int[] ansY;
	int curCycleRoot;
	int cycleLen;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		k = in.readInt();
		a = new int[n];
		for (int i = 0; i < n; ++i) a[i] = in.readInt() - 1;
		x = new int[2 * k];
		y = new int[2 * k];
		ansX = new int[k];
		ansY = new int[k];
		next = new int[2 * k];
		first = new int[n];
		Arrays.fill(first, -1);
		for (int i = 0; i < k; ++i) {
			x[i * 2] = in.readInt() - 1;
			y[i * 2] = in.readInt() - 1;
			next[i * 2] = first[x[i * 2]];
			first[x[i * 2]] = i * 2;
			x[i * 2 + 1] = y[i * 2];
			y[i * 2 + 1] = x[i * 2];
			next[i * 2 + 1] = first[x[i *2 + 1]];
			first[x[i * 2 + 1]] = i * 2 + 1;
		}
		firstChild = new int[n];
		nextSibling = new int[n];
		Arrays.fill(firstChild, -1);
		for (int i = 0; i < n; ++i) {
			nextSibling[i] = firstChild[a[i]];
			firstChild[a[i]] = i;
		}
		mark = new boolean[n];
		boolean[] mark2 = new boolean[n];
		cyclePos = new int[n];
		int[] cycle = new int[n];
		Arrays.fill(cyclePos, -1);
		dsu = new int[n];
		for (int i = 0; i < n; ++i) {
			dsu[i] = i;
		}
		numSeen = new int[k];
		depth = new int[n];
		for (int i = 0; i < n; ++i) if (!mark[i]) {
			int root = i;
			while (!mark2[root]) {
				mark2[root] = true;
				root = a[root];
			}
			cycleLen = 0;
			while (cyclePos[root] < 0) {
				cycle[cycleLen] = root;
				cyclePos[root] = cycleLen++;
				root = a[root];
			}
			for (int ci = 0; ci < cycleLen; ++ci) {
				curCycleRoot = cycle[ci];
				dfs(cycle[ci]);
			}
			for (int ci = 0; ci < cycleLen; ++ci) {
				cyclePos[cycle[ci]] = -2;
			}
		}
		for (int i = 0; i < k; ++i) {
			out.printLine(ansX[i], ansY[i]);
		}
	}

	int dsuGet(int x) {
		if (dsu[x] == x) return x;
		dsu[x] = dsuGet(dsu[x]);
		return dsu[x];
	}

	private void dfs(int at) {
		if (mark[at]) throw new RuntimeException();
		mark[at] = true;
		int pair = first[at];
		while (pair >= 0) {
			++numSeen[pair / 2];
			if (numSeen[pair / 2] == 2) {
				int other = dsuGet(y[pair]);
				if (cyclePos[other] == -2) {
					ansX[pair / 2] = -1;
					ansY[pair / 2] = -1;
				} else if (cyclePos[other] == -1) {
					ansX[pair / 2] = depth[at] - depth[other];
					ansY[pair / 2] = depth[y[pair]] - depth[other];
					if (pair % 2 != 0) {
						int t = ansX[pair / 2];
						ansX[pair / 2] = ansY[pair / 2];
						ansY[pair / 2] = t;
					}
				} else {
					int p1 = cyclePos[other];
					int p2 = cyclePos[curCycleRoot];
					if (p1 == p2) {
						ansX[pair / 2] = depth[at] - depth[other];
						ansY[pair / 2] = depth[y[pair]] - depth[other];
						if (pair % 2 != 0) {
							int t = ansX[pair / 2];
							ansX[pair / 2] = ansY[pair / 2];
							ansY[pair / 2] = t;
						}
					} else {
						int rx1 = depth[at] + (p1 - p2 + cycleLen) % cycleLen;
						int ry1 = depth[y[pair]];
						if (pair % 2 != 0) {
							int t = rx1;
							rx1 = ry1;
							ry1 = t;
						}
						int rx2 = depth[at];
						int ry2 = depth[y[pair]] + (p2 - p1 + cycleLen) % cycleLen;
						if (pair % 2 != 0) {
							int t = rx2;
							rx2 = ry2;
							ry2 = t;
						}
						if (Math.max(rx1, ry1) < Math.max(rx2, ry2)) {
							ansX[pair / 2] = rx1;
							ansY[pair / 2] = ry1;
						} else if (Math.max(rx1, ry1) > Math.max(rx2, ry2)) {
							ansX[pair / 2] = rx2;
							ansY[pair / 2] = ry2;
						} else if (Math.min(rx1, ry1) < Math.min(rx2, ry2)) {
							ansX[pair / 2] = rx1;
							ansY[pair / 2] = ry1;
						} else if (Math.min(rx1, ry1) > Math.min(rx2, ry2)) {
							ansX[pair / 2] = rx2;
							ansY[pair / 2] = ry2;
						} else if (rx1 >= ry1) {
							ansX[pair / 2] = rx1;
							ansY[pair / 2] = ry1;
						} else {
							ansX[pair / 2] = rx2;
							ansY[pair / 2] = ry2;
						}
					}
				}
			}
			pair = next[pair];
		}
		int child = firstChild[at];
		while (child >= 0) {
			if (cyclePos[child] == -1) {
				depth[child] = depth[at] + 1;
				dfs(child);
				dsu[child] = at;
			}
			child = nextSibling[child];
		}
	}
}
