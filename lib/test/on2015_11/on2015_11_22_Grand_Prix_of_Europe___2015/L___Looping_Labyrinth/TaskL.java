package on2015_11.on2015_11_22_Grand_Prix_of_Europe___2015.L___Looping_Labyrinth;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class TaskL {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		char[][] map = IOUtils.readTable(in, n, m);
		int[][] sx = new int[n][m];
		int[][] sy = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		Set<IntIntPair> shifts = new HashSet<>();
		Queue<IntIntPair> queue = new ArrayDeque<>();
		queue.add(new IntIntPair(0, 0));
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			int row = queue.peek().first;
			int column = queue.poll().second;
			for (int i = 0; i < 4; i++) {
				int nRow = row + MiscUtils.DX4[i];
				int nColumn = column + MiscUtils.DY4[i];
				int nsx = sx[row][column];
				int nsy = sy[row][column];
				if (nRow < 0) {
					nRow += n;
					nsx--;
				}
				if (nRow >= n) {
					nRow -= n;
					nsx++;
				}
				if (nColumn < 0) {
					nColumn += m;
					nsy--;
				}
				if (nColumn >= m) {
					nColumn -= m;
					nsy++;
				}
				if (map[nRow][nColumn] == '#') {
					continue;
				}
				if (!visited[nRow][nColumn]) {
					visited[nRow][nColumn] = true;
					queue.add(new IntIntPair(nRow, nColumn));
					sx[nRow][nColumn] = nsx;
					sy[nRow][nColumn] = nsy;
				} else if (sx[nRow][nColumn] != nsx || sy[nRow][nColumn] != nsy) {
					shifts.add(new IntIntPair(nsx - sx[nRow][nColumn], nsy - sy[nRow][nColumn]));
				}
			}
		}
		boolean isFFA = false;
		for (IntIntPair one : shifts) {
			for (IntIntPair second : shifts) {
				if (one.first * second.second != one.second * second.first) {
					isFFA = true;
					break;
				}
			}
		}
		IntIntPair singleShift = new IntIntPair(0, 0);
		if (!isFFA && !shifts.isEmpty()) {
			singleShift = shifts.iterator().next();
			int g = IntegerUtils.gcd(singleShift.first, singleShift.second);
			singleShift = new IntIntPair(singleShift.first / g, singleShift.second / g);
		}
		int q = in.readInt();
		for (int i = 0; i < q; i++) {
			int r = in.readInt();
			int c = in.readInt();
			int sR = r / n;
			r %= n;
			int sC = c / m;
			c %= m;
			if (r < 0) {
				sR--;
				r += n;
			}
			if (c < 0) {
				sC--;
				c += m;
			}
			if (!visited[r][c]) {
				out.printLine("no");
				continue;
			}
			if (isFFA) {
				out.printLine("yes");
				continue;
			}
			sR -= sx[r][c];
			sC -= sy[r][c];
			if (sR == 0 && sC == 0) {
				out.printLine("yes");
				continue;
			}
			if (singleShift.first == singleShift.second && singleShift.first == 0) {
				out.printLine("no");
				continue;
			}
			if (sR * singleShift.second == sC * singleShift.first) {
				out.printLine("yes");
				continue;
			}
			out.printLine("no");
		}
	}
}
