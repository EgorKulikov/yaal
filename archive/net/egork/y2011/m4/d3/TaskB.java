package net.egork.y2011.m4.d3;

import net.egork.helper.table.TableHelper;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.*;

public class TaskB implements Solver {
	private static class Position {
		private final int firstRow;
		private final int firstColumn;
		private final int secondRow;
		private final int secondColumn;
		private final boolean firstStuck;
		private final boolean secondStuck;

		private Position(int firstRow, int firstColumn, int secondRow, int secondColumn, boolean firstStuck, boolean secondStuck) {
			this.firstRow = firstRow;
			this.firstColumn = firstColumn;
			this.secondRow = secondRow;
			this.secondColumn = secondColumn;
			this.firstStuck = firstStuck;
			this.secondStuck = secondStuck;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Position position = (Position) o;

			if (firstColumn != position.firstColumn) return false;
			if (firstRow != position.firstRow) return false;
			if (firstStuck != position.firstStuck) return false;
			if (secondColumn != position.secondColumn) return false;
			if (secondRow != position.secondRow) return false;
			if (secondStuck != position.secondStuck) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = firstRow;
			result = 31 * result + firstColumn;
			result = 31 * result + secondRow;
			result = 31 * result + secondColumn;
			result = 31 * result + (firstStuck ? 1 : 0);
			result = 31 * result + (secondStuck ? 1 : 0);
			return result;
		}
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[][] tempMaze = in.readTable(10, 15);
		char[][] maze = new char[12][17];
		for (char[] row : maze)
			Arrays.fill(row, 'X');
		for (int i = 0; i < 10; i++)
			System.arraycopy(tempMaze[i], 0, maze[i + 1], 1, 15);
		int firstRow = in.readInt();
		int firstColumn = in.readInt();
		int secondRow = in.readInt();
		int secondColumn = in.readInt();
		Set<Position> visited = new HashSet<Position>();
		@SuppressWarnings({"unchecked"})
		List<Position>[] queues = new List[12];
		for (int i = 0; i < 12; i++)
			queues[i] = new ArrayList<Position>();
		int step = 0;
		Set<Position> finalPositions = new HashSet<Position>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 17; j++) {
				if (maze[i][j] == 'H') {
					maze[i][j] = '.';
					finalPositions.add(new Position(i, j, i, j, false, false));
				}
			}
		}
		queues[0].add(new Position(firstRow, firstColumn, secondRow, secondColumn, maze[firstRow][firstColumn] == 'O', maze[secondRow][secondColumn] == 'O'));
		queues[0].add(new Position(secondRow, secondColumn, firstRow, firstColumn, maze[secondRow][secondColumn] == 'O', maze[firstRow][firstColumn] == 'O'));
		while (true) {
			boolean allQueuesEmpty = true;
			for (int i = 0; i < 12; i++) {
				allQueuesEmpty &= queues[i].isEmpty();
				for (Position position : queues[i]) {
					if (visited.contains(position))
						continue;
					visited.add(position);
					if (finalPositions.contains(position)) {
						out.println(step);
						return;
					}
					if (position.firstStuck && position.secondStuck)
						continue;
					if (position.firstStuck || position.secondStuck) {
						int distance = Math.abs(position.firstColumn - position.secondColumn) + Math.abs(position.firstRow - position.secondRow);
						if (distance == 1) {
							if (position.firstStuck) {
								Position next = new Position(position.secondRow, position.secondColumn, position.secondRow, position.secondColumn, false, false);
								if (!visited.contains(next))
									queues[(i + 11) % 12].add(next);
							} else {
								Position next = new Position(position.firstRow, position.firstColumn, position.firstRow, position.firstColumn, false, false);
								if (!visited.contains(next))
									queues[(i + 11) % 12].add(next);
							}
						}
						if (position.firstStuck) {
							for (int j = 0; j < 4; j++) {
								int newSecondRow = position.secondRow + TableHelper.DX_4_CONNECTED[j];
								int newSecondColumn = position.secondColumn + TableHelper.DY_4_CONNECTED[j];
								if (maze[newSecondRow][newSecondColumn] != 'X') {
									Position next = new Position(position.firstRow, position.firstColumn, newSecondRow, newSecondColumn, true, maze[newSecondRow][newSecondColumn] == 'O');
									if (!visited.contains(next))
										queues[(i + 3) % 12].add(next);
								}
							}
						} else {
							for (int j = 0; j < 4; j++) {
								int newFirstRow = position.firstRow + TableHelper.DX_4_CONNECTED[j];
								int newFirstColumn = position.firstColumn+ TableHelper.DY_4_CONNECTED[j];
								if (maze[newFirstRow][newFirstColumn] != 'X') {
									Position next = new Position(newFirstRow, newFirstColumn, position.secondRow, position.secondColumn, maze[newFirstRow][newFirstColumn] == 'O', true);
									if (!visited.contains(next))
										queues[(i + 2) % 12].add(next);
								}
							}
						}
					} else {
						for (int j = 0; j < 4; j++) {
							int newFirstRow = position.firstRow + TableHelper.DX_4_CONNECTED[j];
							int newFirstColumn = position.firstColumn+ TableHelper.DY_4_CONNECTED[j];
							if (maze[newFirstRow][newFirstColumn] == 'X') {
								newFirstRow = position.firstRow;
								newFirstColumn = position.firstColumn;
							}
							int newSecondRow = position.secondRow + TableHelper.DX_4_CONNECTED[j];
							int newSecondColumn = position.secondColumn + TableHelper.DY_4_CONNECTED[j ^ 2];
							if (maze[newSecondRow][newSecondColumn] == 'X') {
								newSecondRow = position.secondRow;
								newSecondColumn = position.secondColumn;
							}
							Position next = new Position(newFirstRow, newFirstColumn, newSecondRow, newSecondColumn, maze[newFirstRow][newFirstColumn] == 'O', maze[newSecondRow][newSecondColumn] == 'O');
							if (!visited.contains(next))
								queues[(i + 5) % 12].add(next);
						}

					}
				}
				queues[i].clear();
				step++;
			}
			if (allQueuesEmpty)
				break;
		}
		out.println(-1);
	}
}

