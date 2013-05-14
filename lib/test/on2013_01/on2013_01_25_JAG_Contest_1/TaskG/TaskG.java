package on2013_01.on2013_01_25_JAG_Contest_1.TaskG;



import net.egork.misc.ArrayUtils;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	int rowCount, columnCount;
	char[][] maze;
	int[][][][] result;
	int[][] closeRow = new int[26][];
	int[][] closeColumn = new int[26][];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		rowCount = in.readInt();
		columnCount = in.readInt();
		maze = IOUtils.readTable(in, rowCount, columnCount);
		IntList[] cRow = new IntList[26];
		IntList[] cColumn = new IntList[26];
		for (int i = 0; i < 26; i++) {
			cRow[i] = new IntArrayList();
			cColumn[i] = new IntArrayList();
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (Character.isUpperCase(maze[i][j])) {
					cRow[maze[i][j] - 'A'].add(i);
					cColumn[maze[i][j] - 'A'].add(j);
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			closeRow[i] = cRow[i].toArray();
			closeColumn[i] = cColumn[i].toArray();
		}
		result = new int[rowCount][columnCount][rowCount][columnCount];
		ArrayUtils.fill(result, -2);
		out.printLine(go(0, 0, rowCount - 1, columnCount - 1));
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (go(i, j, 49, 47) > go(i, j, 49, 49))
					System.err.println(i + " " + j);
			}
		}
	}

	private int go(int fromRow, int fromColumn, int toRow, int toColumn) {
		if (fromRow < 0 || fromRow > toRow || toRow >= rowCount || fromColumn < 0 || fromColumn > toColumn || toColumn >= columnCount)
			return -1;
		if (maze[fromRow][fromColumn] == '#' || maze[toRow][toColumn] == '#')
			return -1;
		if (result[fromRow][fromColumn][toRow][toColumn] != -2)
			return result[fromRow][fromColumn][toRow][toColumn];
		if (fromRow == toRow && fromColumn == toColumn)
			return result[fromRow][fromColumn][toRow][toColumn] = 0;
		result[fromRow][fromColumn][toRow][toColumn] = -1;
		int right = go(fromRow, fromColumn + 1, toRow, toColumn);
		if (right != -1)
			result[fromRow][fromColumn][toRow][toColumn] = Math.max(result[fromRow][fromColumn][toRow][toColumn], right);
		int down = go(fromRow + 1, fromColumn, toRow, toColumn);
		if (down != -1)
			result[fromRow][fromColumn][toRow][toColumn] = Math.max(result[fromRow][fromColumn][toRow][toColumn], down);
		if (Character.isLowerCase(maze[fromRow][fromColumn])) {
			int color = maze[fromRow][fromColumn] - 'a';
			for (int i = 0; i < closeRow[color].length; i++) {
				int nextRow = closeRow[color][i];
				int nextColumn = closeColumn[color][i];
				if (nextRow < fromRow || nextColumn < fromColumn)
					continue;
				int additional = go(nextRow, nextColumn, toRow, toColumn);
				if (additional == -1)
					continue;
				if (nextRow == fromRow && nextColumn == fromColumn + 1 || nextColumn == fromColumn && nextRow == fromRow + 1)
					result[fromRow][fromColumn][toRow][toColumn] = Math.max(result[fromRow][fromColumn][toRow][toColumn], additional + 1);
				else {
					for (int j = 0; j < 2; j++) {
						for (int k = 0; k < 2; k++) {
							int direct = go(fromRow + j, fromColumn + 1 - j, nextRow - k, nextColumn - 1 + k);
							if (direct == -1)
								continue;
							result[fromRow][fromColumn][toRow][toColumn] = Math.max(result[fromRow][fromColumn][toRow][toColumn], direct + additional + 1);
						}
					}
				}
			}
		}
		return result[fromRow][fromColumn][toRow][toColumn];
	}
}
