package on2016_01.on2016_01_31_SNWS_2016__R4.D___Double_Trouble;



import net.egork.collections.Pair;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Queue;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		char[][] map = IOUtils.readTable(in, n, m);
		IntIntPair first = null;
		IntIntPair second = null;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'S') {
					if (first == null) {
						first = new IntIntPair(i, j);
					} else {
						second = new IntIntPair(i, j);
					}
					map[i][j] = '.';
				}
			}
		}
		int[][][][] answer = new int[n][m][n][m];
		ArrayUtils.fill(answer, Integer.MAX_VALUE);
		answer[first.first][first.second][second.first][second.second] = 0;
		Queue<Pair<IntIntPair, IntIntPair>> queue = new ArrayDeque<>();
		queue.add(Pair.makePair(first, second));
		while (!queue.isEmpty()) {
			int r1 = queue.peek().first.first;
			int c1 = queue.peek().first.second;
			int r2 = queue.peek().second.first;
			int c2 = queue.poll().second.second;
			if (r1 == r2 && c1 == c2) {
				out.printLine(answer[r1][c1][r2][c2]);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr1 = r1 + MiscUtils.DX4[i];
				int nc1 = c1 + MiscUtils.DY4[i];
				int nr2 = r2 + MiscUtils.DX4[i];
				int nc2 = c2 + MiscUtils.DY4[i];
				if (nr1 < 0) {
					nr1 += n;
				}
				if (nr1 >= n) {
					nr1 -= n;
				}
				if (nr2 < 0) {
					nr2 += n;
				}
				if (nr2 >= n) {
					nr2 -= n;
				}
				if (nc1 < 0) {
					nc1 += m;
				}
				if (nc1 >= m) {
					nc1 -= m;
				}
				if (nc2 < 0) {
					nc2 += m;
				}
				if (nc2 >= m) {
					nc2 -= m;
				}
				if (map[nr1][nc1] == 'P' || map[nr2][nc2] == 'P') {
					continue;
				}
				if (map[nr1][nc1] == 'W') {
					nr1 = r1;
					nc1 = c1;
				}
				if (map[nr2][nc2] == 'W') {
					nr2 = r2;
					nc2 = c2;
				}
				if (answer[nr1][nc1][nr2][nc2] == Integer.MAX_VALUE) {
					answer[nr1][nc1][nr2][nc2] = answer[r1][c1][r2][c2] + 1;
					queue.add(Pair.makePair(new IntIntPair(nr1, nc1), new IntIntPair(nr2, nc2)));
				}
			}
		}
		out.printLine(-1);
	}
}
