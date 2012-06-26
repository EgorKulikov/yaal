import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class TaskG implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int[][] bugIndex = new int[rowCount][columnCount];
		int index = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] == '#')
					bugIndex[i][j] = index++;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] == 'B')
					bugIndex[i][j] = index;
			}
		}
		int[][] distances = new int[index + 1][index + 1];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] == '#' || table[i][j] == 'B') {
					Queue<Integer> rows = new ArrayDeque<Integer>();
					rows.add(i);
					Queue<Integer> columns = new ArrayDeque<Integer>();
					columns.add(j);
					boolean[][] visited = new boolean[rowCount][columnCount];
					int[][] distance = new int[rowCount][columnCount];
					visited[i][j] = true;
					while (!rows.isEmpty()) {
						int row = rows.poll();
						int column = columns.poll();
						if (table[row][column] != '.')
							distances[bugIndex[i][j]][bugIndex[row][column]] = distance[row][column];
						for (int k = 0; k < 4; k++) {
							int nextRow = row + MiscUtils.DX4[k];
							int nextColumn = column + MiscUtils.DY4[k];
							if (nextRow >= 0 && nextRow < rowCount && nextColumn >= 0 && nextColumn < columnCount && table[nextRow][nextColumn] != 'X' && !visited[nextRow][nextColumn]) {
								visited[nextRow][nextColumn] = true;
								distance[nextRow][nextColumn] = distance[row][column] + 1;
								rows.add(nextRow);
								columns.add(nextColumn);
							}
						}
					}
				}
			}
		}
		for (int i = 0; i <= index; i++) {
			for (int j = i + 1; j <= index; j++) {
				if (distances[i][j] == 0) {
					out.println(-1);
					return;
				}
			}
		}
		int[][] answer = new int[index + 1][1 << index];
		ArrayUtils.fill(answer, Integer.MAX_VALUE);
		answer[index][0] = 0;
		for (int i = 0; i < (1 << index) - 1; i++) {
			for (int j = 0; j <= index; j++) {
				if (i != 0 && j == index || j != index && (i >> j & 1) == 0)
					continue;
				for (int k = 0; k < index; k++) {
					if ((i >> k & 1) == 1)
						continue;
					answer[k][i + (1 << k)] = Math.min(answer[k][i + (1 << k)], answer[j][i] + distances[j][k]);
				}
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < index; i++)
			result = Math.min(result, answer[i][(1 << index) - 1]);
		result += index;
		out.println(result);
	}
}

