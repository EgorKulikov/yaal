package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LaserMaze {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		Graph graph = new Graph(4 * rowCount * columnCount);
		int si = -1;
		int sj = -1;
		int ti = -1;
		int tj = -1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'S') {
					si = i;
					sj = j;
				}
				if (map[i][j] == 'G') {
					ti = i;
					tj = j;
				}
				if (map[i][j] == '#' || turretType(map[i][j]) != -1) continue;
				for (int k = 0; k < 4; k++) {
					boolean valid = true;
					for (int l = i - 1; l >= 0; l--) {
						if (map[l][j] == '#') {
							break;
						}
						int turretType = turretType(map[l][j]);
						if (turretType == -1) continue;
						if (2 == ((turretType + k) & 3)) valid = false;
						break;
					}
					for (int l = i + 1; l < rowCount; l++) {
						if (map[l][j] == '#') {
							break;
						}
						int turretType = turretType(map[l][j]);
						if (turretType == -1) continue;
						if (0 == ((turretType + k) & 3)) valid = false;
						break;
					}
					for (int l = j - 1; l >= 0; l--) {
						if (map[i][l] == '#') {
							break;
						}
						int turretType = turretType(map[i][l]);
						if (turretType == -1) continue;
						if (1 == ((turretType + k) & 3)) valid = false;
						break;
					}
					for (int l = j + 1; l < columnCount; l++) {
						if (map[i][l] == '#') {
							break;
						}
						int turretType = turretType(map[i][l]);
						if (turretType == -1) continue;
						if (3 == ((turretType + k) & 3)) valid = false;
						break;
					}
					if (valid) {
						for (int l = 0; l < 4; l++) {
							int ni = i + MiscUtils.DX4[l];
							int nj = j + MiscUtils.DY4[l];
							if (MiscUtils.isValidCell(ni, nj, rowCount, columnCount)) {
								graph.addWeightedEdge(ni * columnCount * 4 + nj * 4 + ((k + 3) & 3), i * columnCount * 4 + j * 4 + k, 1);
							}
						}
					}
				}
			}
		}
		long result = Long.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			Pair<Long, IntList> pair = ShortestDistance.dijkstraAlgorithm(graph, si * columnCount * 4 + sj * 4, ti * columnCount * 4 + tj * 4 + i);
			if (pair != null) result = Math.min(result, pair.first);
		}
		if (result == Long.MAX_VALUE) {
			out.printLine("Case #" + testNumber + ": impossible");
		} else {
			out.printLine("Case #" + testNumber + ":", result);
		}
    }

	private int turretType(char c) {
		if (c == '^') {
			return 0;
		}
		if (c == '>') {
			return 1;
		}
		if (c == 'v') {
			return 2;
		}
		if (c == '<') {
			return 3;
		}
		return -1;
	}
}
