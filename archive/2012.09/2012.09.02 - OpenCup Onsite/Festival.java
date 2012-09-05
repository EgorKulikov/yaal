package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Festival {
	int INF = (int) 1e9;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m1 = in.readInt();
		int m2 = in.readInt();
		int[][] fixedDelta = new int[n][n];
		for (int[] x : fixedDelta) Arrays.fill(x, INF);
		for (int i = 0; i < n; ++i) fixedDelta[i][i] = 0;
		for (int i = 0; i < m1; ++i) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			if (fixedDelta[a][b] != INF && fixedDelta[a][b] != -1) {
				out.printLine("NIE");
				return;
			}
			fixedDelta[a][b] = -1;
			fixedDelta[b][a] = 1;
		}
		for (int k = 0; k < n; ++k)
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j) {
					if (fixedDelta[i][k] != INF && fixedDelta[k][j] != INF) {
						int nd = fixedDelta[i][k] + fixedDelta[k][j];
						if (fixedDelta[i][j] != INF && fixedDelta[i][j] != nd) {
							out.printLine("NIE");
							return;
						}
						fixedDelta[i][j] = nd;
					}
				}
		int[] root = new int[n];
		int[] rootId = new int[n];
		int[] rootSize = new int[n];
		int nroot = 0;
		for (int i = 0; i < n; ++i) {
			root[i] = -1;
			for (int j = 0; j < n; ++j)
				if (fixedDelta[j][i] != INF && (root[i] < 0 || fixedDelta[j][i] < fixedDelta[root[i]][i])) {
					root[i] = j;
				}
			if (root[i] == i) {
				rootId[i] = nroot++;
				for (int j = 0; j < n; ++j)
					if (fixedDelta[j][i] != INF && fixedDelta[j][i] > rootSize[rootId[i]])
						rootSize[rootId[i]] = fixedDelta[j][i];
			}
		}
		int[][] minDelta = new int[nroot][nroot];
		for (int[] x : minDelta) Arrays.fill(x, INF);
		for (int i = 0; i < nroot; ++i) minDelta[i][i] = 0;
		for (int i = 0; i < m2; ++i) {
			int c = in.readInt() - 1;
			int d = in.readInt() - 1;
			int rc = root[c];
			int rd = root[d];
			int vc = rootId[rc];
			int vd = rootId[rd];
			int dc = fixedDelta[c][rc];
			int dd = fixedDelta[d][rd];
			minDelta[vc][vd] = Math.min(minDelta[vc][vd], dd - dc);
		}
		for (int k = 0; k < nroot; ++k)
			for (int i = 0; i < nroot; ++i)
				for (int j = 0; j < nroot; ++j) {
					if (minDelta[i][k] < INF && minDelta[k][j] < INF) {
						int nd = minDelta[i][k] + minDelta[k][j];
						if (nd < minDelta[i][j]) {
							minDelta[i][j] = nd;
						}
					}
				}
		for (int i = 0; i < nroot; ++i)
			if (minDelta[i][i] < 0) {
				out.printLine("NIE");
				return;
			}
		boolean[] mark = new boolean[nroot];
		int[] seq = new int[n];
		int res = 0;
		while (true) {
			int minDeg = INF;
			int minI = -1;
			for (int i = 0; i < nroot; ++i) if (!mark[i]) {
				int deg = 0;
				for (int j = 0; j < nroot; ++j) if (!mark[j] && minDelta[j][i] < INF) {
					++deg;
				}
				if (deg < minDeg) {
					minDeg = deg;
					minI = i;
				}
			}
			if (minI < 0) break;
			int nseq = 0;
			for (int i = 0; i < nroot; ++i) if (!mark[i] && minDelta[i][minI] < INF && minDelta[minI][i] < INF) {
				seq[nseq++] = i;
				mark[i] = true;
			}
			int mx = 0;
			for (int aa = 0; aa < nseq; ++aa)
				for (int bb = 0; bb < nseq; ++bb) {
					int a = seq[aa];
					int b = seq[bb];
					int la = 0;
					int ra = rootSize[a];
					int lb = minDelta[b][a];
					int rb = lb + rootSize[b];
					mx = Math.max(mx, Math.max(ra, rb) - Math.min(la, lb) + 1);
				}
			res += mx;
		}
		out.printLine(res);
	}
}
