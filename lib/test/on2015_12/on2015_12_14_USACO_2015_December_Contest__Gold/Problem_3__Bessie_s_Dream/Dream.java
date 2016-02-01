package on2015_12.on2015_12_14_USACO_2015_December_Contest__Gold.Problem_3__Bessie_s_Dream;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Queue;

public class Dream {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[][] map = IOUtils.readIntTable(in, n, m);
		Queue<State> queue = new ArrayDeque<>();
//		Map<State, Integer> moves = new HashMap<>();
		int[][][][] moves = new int[2][4][n][m];
		State begin = new State(0, 0, 0, false);
		ArrayUtils.fill(moves, -1);
		moves[0][0][0][0] = 0;
//		moves.put(begin, 0);
		queue.add(begin);
		while (!queue.isEmpty()) {
			State state = queue.poll();
			int result = moves[state.smells ? 1 : 0][state.sourceDir][state.row][state.column];
			if (state.row == n - 1 && state.column == m - 1) {
				out.printLine(result);
				return;
			}
			if (map[state.row][state.column] == 4) {
				state.smells = false;
			} else if (map[state.row][state.column] == 2) {
				state.smells = true;
			}
			if (map[state.row][state.column] == 4) {
				int nRow = state.row + MiscUtils.DX4[state.sourceDir];
				int nColumn = state.column + MiscUtils.DY4[state.sourceDir];
				int nDir = state.sourceDir;
				int nSmells = state.smells ? 1 : 0;
				if (MiscUtils.isValidCell(nRow, nColumn, n, m) && map[nRow][nColumn] != 0 &&
						(map[nRow][nColumn] != 3 || nSmells != 0)) {
					if (moves[nSmells][nDir][nRow][nColumn] == -1) {
						State next = new State(nRow, nColumn, nDir, nSmells != 0);
						queue.add(next);
						moves[nSmells][nDir][nRow][nColumn] = result + 1;
					}
					continue;
				}
			}
			for (int i = 0; i < 4; i++) {
				int nRow = state.row + MiscUtils.DX4[i];
				int nColumn = state.column + MiscUtils.DY4[i];
				int nDir = i;
				int nSmells = state.smells ? 1 : 0;
				if (MiscUtils.isValidCell(nRow, nColumn, n, m) && map[nRow][nColumn] != 0 &&
						(map[nRow][nColumn] != 3 || nSmells != 0)) {
					if (moves[nSmells][nDir][nRow][nColumn] == -1) {
						State next = new State(nRow, nColumn, nDir, nSmells != 0);
						queue.add(next);
						moves[nSmells][nDir][nRow][nColumn] = result + 1;
					}
				}
			}
		}
		out.printLine(-1);
	}

	static class State {
		int row;
		int column;
		int sourceDir;
		boolean smells;

		public State(int row, int column, int sourceDir, boolean smells) {
			this.row = row;
			this.column = column;
			this.sourceDir = sourceDir;
			this.smells = smells;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			State state = (State) o;

			if (row != state.row) return false;
			if (column != state.column) return false;
			if (sourceDir != state.sourceDir) return false;
			return smells == state.smells;

		}

		@Override
		public int hashCode() {
			int result = row;
			result = 31 * result + column;
			result = 31 * result + sourceDir;
			result = 31 * result + (smells ? 1 : 0);
			return result;
		}
	}
}
