package on2016_01.on2016_01_16_SNWS_2016__R2.C___Cheating;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Queue;

import static net.egork.misc.MiscUtils.*;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		char[][] map = IOUtils.readTable(in, n, n);
		Queue<IntIntPair> queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'X') {
					queue.add(new IntIntPair(i, j));
				}
			}
		}
		int answer = 0;
		while (!queue.isEmpty()) {
			int row = queue.peek().first;
			int column = queue.poll().second;
			for (int i = 0; i < 8; i++) {
				int nRow = row + DX8[i];
				int nColumn = column + DY8[i];
				if (isValidCell(nRow, nColumn, n, n) && map[nRow][nColumn] == '.') {
					map[nRow][nColumn] = 'X';
					queue.add(new IntIntPair(nRow, nColumn));
					answer++;
				}
			}
		}
		out.printLine(answer);
	}
}
