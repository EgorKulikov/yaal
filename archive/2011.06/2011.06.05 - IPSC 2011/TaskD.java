import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		System.err.println(testNumber);
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int redRow = in.readInt() - 1;
		int redColumn = in.readInt() - 1;
		int blueRow = in.readInt() - 1;
		int blueColumn = in.readInt() - 1;
		if (rowCount % 2 == 1 && columnCount % 2 == 1) {
			out.println("IMPOSSIBLE");
			return;
		}
		if (rowCount == 1 || columnCount == 1) {
			if (rowCount == 1) {
				if (redColumn < columnCount / 2 && blueColumn >= columnCount / 2) {
					for (int i = 0; i < columnCount / 2; i++)
						out.print('R');
					for (int i = 0; i < columnCount / 2; i++)
						out.print('B');
					out.println();
					return;
				}
				if (redColumn >= columnCount / 2 && blueColumn < columnCount / 2) {
					for (int i = 0; i < columnCount / 2; i++)
						out.print('B');
					for (int i = 0; i < columnCount / 2; i++)
						out.print('R');
					out.println();
					return;
				}
			} else {
				if (redRow < rowCount / 2 && blueRow >= rowCount / 2) {
					for (int i = 0; i < rowCount / 2; i++)
						out.println('R');
					for (int i = 0; i < rowCount / 2; i++)
						out.println('B');
					out.println();
					return;
				}
				if (redRow >= rowCount / 2 && blueRow < rowCount / 2) {
					for (int i = 0; i < rowCount / 2; i++)
						out.println('B');
					for (int i = 0; i < rowCount / 2; i++)
						out.println('R');
					out.println();
					return;
				}
			}
			out.println("IMPOSSIBLE");
//			char[][] result = new char[rowCount][columnCount];
//			ArrayUtils.fill(result, ' ');
//			result[redRow][redColumn] = 'R';
//			result[blueRow][blueColumn] = 'B';
//			result[rowCount - redRow - 1][columnCount - redColumn - 1] = 'B';
//			result[rowCount - blueRow - 1][columnCount - blueColumn - 1] = 'R';
//			for (int i = 0; i < rowCount; i++) {
//				for (int j = 0; j < columnCount; j++) {
//					if (result[i][j] == ' ') {
//						result[i][j] = 'B';
//						result[rowCount - 1 - i][columnCount - 1 - j] = 'R';
//					}
//				}
//			}
//			for (char[] row : result)
//				out.println(row);
			return;
		}
		char[][] result = new char[rowCount][columnCount];
		ArrayUtils.fill(result, ' ');
		result[redRow][redColumn] = 'R';
		result[blueRow][blueColumn] = 'B';
		if (redRow + blueRow != rowCount - 1 || redColumn + blueColumn != columnCount - 1) {
			result[rowCount - redRow - 1][columnCount - redColumn - 1] = 'B';
			result[rowCount - blueRow - 1][columnCount - blueColumn - 1] = 'R';
			try {
			int fromRow = blueRow;
			int fromColumn = blueColumn;
			int toRow = rowCount - redRow - 1;
			int toColumn = columnCount - redColumn - 1;
			if (fromRow + toRow < rowCount && (fromRow + toRow < rowCount - 1 || (redRow != 0 || redColumn > Math.max(fromColumn, toColumn) || redColumn < Math.min(fromColumn, toColumn)) && (blueRow != rowCount - 1 || columnCount - blueColumn - 1 > Math.max(fromColumn, toColumn) || columnCount - blueColumn - 1 < Math.min(fromColumn, toColumn)))) {
				for (int i = 0; i < fromRow; i++) {
					if (result[i][blueColumn] == 'R')
						throw new RuntimeException();
					result[i][blueColumn] = 'B';
					result[rowCount - i - 1][columnCount - blueColumn - 1] = 'R';
				}
				for (int i = 0; i < toRow; i++) {
					if (result[i][columnCount - redColumn - 1] == 'R')
						throw new RuntimeException();
					result[i][columnCount - redColumn - 1] = 'B';
					result[rowCount - i - 1][redColumn] = 'R';
				}
				for (int i = Math.min(fromColumn, toColumn) + 1; i < Math.max(fromColumn, toColumn); i++) {
					if (result[0][i] == 'R')
						throw new RuntimeException();
					result[0][i] = 'B';
					result[rowCount - 1][columnCount - i - 1] = 'R';
				}
			} else {
				for (int i = fromRow + 1; i < rowCount; i++) {
					if (result[i][blueColumn] == 'R')
						throw new RuntimeException();
					result[i][blueColumn] = 'B';
					result[rowCount - i - 1][columnCount - blueColumn - 1] = 'R';
				}
				for (int i = toRow + 1; i < rowCount; i++) {
					if (result[i][columnCount - redColumn - 1] == 'R')
						throw new RuntimeException();
					result[i][columnCount - redColumn - 1] = 'B';
					result[rowCount - i - 1][redColumn] = 'R';
				}
				for (int i = Math.min(fromColumn, toColumn) + 1; i < Math.max(fromColumn, toColumn); i++) {
					if (result[rowCount - 1][i] == 'R')
						throw new RuntimeException();
					result[rowCount - 1][i] = 'B';
					result[0][columnCount - i - 1] = 'R';
				}
			}

			} catch (RuntimeException e) {
				if (!dfs(result, blueRow, blueColumn, rowCount - redRow - 1, columnCount - redColumn - 1))
					throw new RuntimeException();
			}
		}
		Queue<Pair<Integer, Integer>> queue = new ArrayDeque<Pair<Integer, Integer>>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (result[i][j] == 'B')
					queue.add(Pair.makePair(i, j));
			}
		}
		while (!queue.isEmpty()) {
			int row = queue.peek().first;
			int column = queue.poll().second;
			for (int i = 0; i < 4; i++) {
				int nextRow = row + MiscUtils.DX4[i];
				int nextColumn = column + MiscUtils.DY4[i];
				if (nextRow >= 0 && nextRow < rowCount && nextColumn >= 0 && nextColumn < columnCount && result[nextRow][nextColumn] == ' ') {
					result[nextRow][nextColumn] = 'B';
					result[rowCount - nextRow - 1][columnCount - nextColumn - 1] = 'R';
					queue.add(Pair.makePair(nextRow, nextColumn));
				}
			}
		}
		for (char[] row : result)
			out.println(row);
	}

	private boolean dfs(char[][] result, int row, int column, int finishRow, int finishColumn) {
		if (finishRow == row && finishColumn == column)
			return true;
		int[] dist = new int[4];
		for (int i = 0; i < 4; i++) {
			int nextRow = row + MiscUtils.DX4[i];
			int nextColumn = column + MiscUtils.DY4[i];
			dist[i] = Math.abs(nextRow - finishRow) + Math.abs(nextColumn - finishColumn);
		}
		Integer[] order = SequenceUtils.order(Array.wrap(dist));
		for (int i : order) {
			int nextRow = row + MiscUtils.DX4[i];
			int nextColumn = column + MiscUtils.DY4[i];
			if (nextRow >= 0 && nextRow < result.length && nextColumn >= 0 && nextColumn < result[0].length && (result[nextRow][nextColumn] == ' ' || nextRow == finishRow && nextColumn == finishColumn)) {
				result[nextRow][nextColumn] = 'B';
				result[result.length - nextRow - 1][result[0].length - nextColumn - 1] = 'R';
				if (dfs(result, nextRow, nextColumn, finishRow, finishColumn))
					return true;
				result[nextRow][nextColumn] = ' ';
				result[result.length - nextRow - 1][result[0].length - nextColumn - 1] = ' ';
			}
		}
		return false;
	}
}

