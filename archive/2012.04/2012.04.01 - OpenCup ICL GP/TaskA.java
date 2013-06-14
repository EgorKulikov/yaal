package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();

		Set<Brick> removed = new HashSet<Brick>();
		for (int i = 0; i < k; i++) {
			Brick brick = new Brick(
				in.readInt(), in.readInt()
			);
			if ((!removed.contains(new Brick(brick.row - 1, brick.col - 1)) &&
				removed.contains(new Brick(brick.row, brick.col - 1))) ||
				(!removed.contains(new Brick(brick.row - 1, brick.col)) &&
					removed.contains(new Brick(brick.row, brick.col + 1)))) {
				out.printLine(i + 1);
				return;
			}
			removed.add(brick);
		}
		out.printLine(-1);
	}

	class Brick {
		int row;
		int col;

		Brick(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Brick)) return false;

			Brick brick = (Brick) o;

			if (col != brick.col) return false;
			if (row != brick.row) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = row;
			result = 31 * result + col;
			return result;
		}
	}
}
