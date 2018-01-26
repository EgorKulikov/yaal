package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class EmasSupercomputer {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		char[][] grid = IOUtils.readTable(in, n, m);
		List<Star> stars = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] != 'G') {
					continue;
				}
				for (int size = 1; ; size++) {
					stars.add(new Star(i, j, size));
					if (size > i || grid[i - size][j] != 'G' ||
						size > j || grid[i][j - size] != 'G' ||
						i + size >= n || grid[i + size][j] != 'G' ||
						j + size >= m || grid[i][j + size] != 'G')
					{
						break;
					}
				}
			}
		}
		int answer = 0;
		for (Star first : stars) {
			for (Star second : stars) {
				if (first.equals(second)) {
					break;
				}
				if (first.row == second.row && Math.abs(first.column - second.column) <= first.size + second.size - 2) {
					continue;
				}
				if (first.column == second.column && Math.abs(first.row - second.row) <= first.size + second.size - 2) {
					continue;
				}
				int dRow = Math.abs(first.row - second.row);
				int dColumn = Math.abs(first.column - second.column);
				if (Math.max(dRow, dColumn) <= Math.max(first.size, second.size) - 1 && Math.min(dRow, dColumn) <= Math.min(first.size, second.size) - 1) {
					continue;
				}
				int candidate = (4 * first.size - 3) * (4 * second.size - 3);
				answer = Math.max(answer, candidate);
			}
		}
		out.printLine(answer);
	}

	static class Star {
		int row;
		int column;
		int size;

		public Star(int row, int column, int size) {
			this.row = row;
			this.column = column;
			this.size = size;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Star star = (Star) o;

			if (row != star.row) return false;
			if (column != star.column) return false;
			return size == star.size;

		}

		@Override
		public int hashCode() {
			int result = row;
			result = 31 * result + column;
			result = 31 * result + size;
			return result;
		}
	}
}
