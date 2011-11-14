package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	static final int INF = 1000000000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int nLeft = in.readInt();
		int nRight = in.readInt();
		int k = in.readInt();
		boolean[][] friends = new boolean[nLeft][nRight];
		int[] iqLeft = new int[nLeft];
		int[] iqRight = new int[nRight];
		for (int i = 0; i < k; ++i) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			friends[a][b] = true;
		}
		long totalIq = 0;
		for (int i = 0; i < nLeft; ++i) {
			iqLeft[i] = in.readInt();
			totalIq += iqLeft[i];
		}
		for (int i = 0; i < nRight; ++i) {
			iqRight[i] = in.readInt();
			totalIq += iqRight[i];
		}
		int nv = nLeft + nRight + 2;
		int s = nv - 2;
		int t = nv - 1;
		int[][] cap = new int[nv][nv];
		int[][] flow = new int[nv][nv];
		for (int i = 0; i < nLeft; ++i) {
			cap[s][i] = iqLeft[i];
		}
		for (int i = 0; i < nRight; ++i) {
			cap[i + nLeft][t] = iqRight[i];
		}
		for (int i = 0; i < nLeft; ++i)
			for (int j = 0; j < nRight; ++j)
				if (!friends[i][j])
					cap[i][j + nLeft] = INF;
		boolean[] mark = new boolean[nv];
		long flowSize = maxFlow(nv, s, t, cap, flow, mark);
		out.printLine(totalIq - flowSize);
		int cnt = 0;
		for (int i = 0; i < nLeft; ++i)
			if (mark[i])
				++cnt;
		out.printLine(cnt);
		cnt = 0;
		for (int i = 0; i < nLeft; ++i)
			if (mark[i]) {
				if (cnt > 0) out.print(" ");
				out.print(i + 1);
				++cnt;
			}
		out.printLine();
		cnt = 0;
		for (int i = 0; i < nRight; ++i)
			if (!mark[i + nLeft])
				++cnt;
		out.printLine(cnt);
		cnt = 0;
		for (int i = 0; i < nRight; ++i)
			if (!mark[i + nLeft]) {
				if (cnt > 0) out.print(" ");
				out.print(i + 1);
				++cnt;
			}
		out.printLine();
	}

	int nv;
	int t;
	int[][] cap;
	int[][] flow;
	int[][] adj;
	int[] adjPos;
	boolean[] mark;

	private long maxFlow(int nv, int s, int t, int[][] cap, int[][] flow, boolean[] mark) {
		this.nv = nv;
		this.t = t;
		this.cap = cap;
		this.flow = flow;
		this.mark = mark;
		adj = new int[nv][];
		for (int i = 0; i < nv; ++i) {
			int cnt = 0;
			for (int j = 0; j < nv; ++j) if (cap[i][j] > 0 || cap[j][i] > 0)
				++cnt;
			adj[i] = new int[cnt];
			cnt = 0;
			for (int j = 0; j < nv; ++j) if (cap[i][j] > 0 || cap[j][i] > 0)
				adj[i][cnt++] = j;
		}
		adjPos = new int[nv];
		long res = 0;
		Arrays.fill(mark, false);
		while (true) {
			int by = dfs(s, INF);
			if (by == 0) break;
			res += by;
			Arrays.fill(mark, false);
		}
		return res;
	}

	private int dfs(int s, int by) {
		if (s == t) return by;
		if (mark[s]) return 0;
		mark[s] = true;
		int[] cadj = adj[s];
		int pos = adjPos[s];
		int toAdd = 0;
		for (int ii = 0; ii < cadj.length; ++ii) {
			int i = cadj[pos];
			int extra = cap[s][i] - flow[s][i];
			if (extra > 0) {
				int nby = Math.min(by, extra);
				int retBy = dfs(i, nby);
				if (retBy > 0) {
					flow[s][i] += retBy;
					flow[i][s] -= retBy;
					if (retBy < by) {
						by -= retBy;
						toAdd += retBy;
					} else {
						adjPos[s] = pos;
						return nby + toAdd;
					}
				}
			}
			++pos;
			if (pos >= cadj.length)
				pos = 0;
		}
		adjPos[s] = pos;
		return toAdd;
	}
}
