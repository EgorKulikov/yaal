package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BearsAndBees {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] from = new int[m];
		int[] to = new int[m];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		boolean[][] g1 = new boolean[m][m];
		int[] g1Deg = new int[m];
		int g1EdgeCount = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				if (i == j)
					continue;
				g1[i][j] = from[i] == from[j] || from[i] == to[j] || to[i] == from[j] || to[i] == to[j];
				if (g1[i][j]) {
					g1Deg[i]++;
					g1EdgeCount++;
				}
			}
		}
		g1EdgeCount /= 2;
		long[] g2Deg = new long[g1EdgeCount];
		int index = 0;
		long g2EdgeCount = 0;
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				if (g1[i][j])
					g2EdgeCount += g2Deg[index++] = g1Deg[i] + g1Deg[j] - 2;
			}
		}
		g2EdgeCount /= 2;
		long g3EdgeCount = 0;
		for (long deg : g2Deg)
			g3EdgeCount += deg * (deg - 1);
		g3EdgeCount /= 2;
		out.printLine(g2EdgeCount, g3EdgeCount);
	}
}
