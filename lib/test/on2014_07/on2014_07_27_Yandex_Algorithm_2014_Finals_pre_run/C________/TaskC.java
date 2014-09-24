package on2014_07.on2014_07_27_Yandex_Algorithm_2014_Finals_pre_run.C________;



import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskC {
	int[][] permutations = {
		{0, 1, 4, 5, 3, 2},
		{2, 3, 1, 0, 4, 5},
		{4, 5, 2, 3, 1, 0},
		{0, 1, 3, 2, 4, 5}
	};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] cube = IOUtils.readIntArray(in, 6);
		List<char[]> planeList = new ArrayList<>();
		while (!in.isExhausted()) {
			planeList.add(in.readString().toCharArray());
		}
		char[][] plane = new char[planeList.size()][];
		for (int i = 0; i < plane.length; i++) {
			plane[i] = planeList.get(i);
		}
		int[] current = new int[6];
		Queue<State> queue = new ArrayDeque<>();
		boolean[][] seen = new boolean[plane.length][plane[0].length];
		for (int i = 0; i < plane.length && queue.size() == 0; i++) {
			for (int j = 0; j < plane[0].length; j++) {
				if (plane[i][j] != '0') {
					seen[i][j] = true;
					queue.add(new State(0, 2, 3, 4, 5, i, j));
					current[0] = plane[i][j] - '0';
					break;
				}
			}
		}
		while (!queue.isEmpty()) {
			State state = queue.poll();
			for (State next : state.positions()) {
				if (MiscUtils.isValidCell(next.row, next.column, plane.length, plane[0].length) && plane[next.row][next.column] != '0' && !seen[next.row][next.column]) {
					if (current[next.position] != 0 && current[next.position] != plane[next.row][next.column] - '0') {
						out.printLine("No");
						return;
					}
					current[next.position] = plane[next.row][next.column] - '0';
					queue.add(next);
					seen[next.row][next.column] = true;
				}
			}
		}
		Cube start = new Cube(cube);
		Cube target = new Cube(current);
		Queue<Cube> cubes = new ArrayDeque<>();
		cubes.add(start);
		Set<Cube> set = new EHashSet<>();
		while (!cubes.isEmpty()) {
			Cube cur = cubes.poll();
			if (target.equals(cur)) {
				out.printLine("Yes");
				return;
			}
			for (int[] permutation : permutations) {
				Cube next = new Cube(ArrayUtils.multiplyPermutations(cur.planes, permutation));
				if (!set.contains(next)) {
					set.add(next);
					cubes.add(next);
				}
			}
		}
		out.printLine("No");
	}

	static class Cube {
		int[] planes;

		Cube(int[] planes) {
			this.planes = planes;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Cube cube = (Cube) o;

			if (!Arrays.equals(planes, cube.planes)) return false;

			return true;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(planes);
		}
	}

	static class State {
		int position;
		int toLeft;
		int toRight;
		int toTop;
		int toBottom;

		int row;
		int column;

		State(int position, int toLeft, int toRight, int toTop, int toBottom, int row, int column) {
			this.position = position;
			this.toLeft = toLeft;
			this.toRight = toRight;
			this.toTop = toTop;
			this.toBottom = toBottom;
			this.row = row;
			this.column = column;
		}

		State right() {
			return new State(toRight, position, position ^ 1, toTop, toBottom, row, column + 1);
		}

		State left() {
			return new State(toLeft, position ^ 1, position, toTop, toBottom, row, column - 1);
		}

		State bottom() {
			return new State(toBottom, toLeft, toRight, position, position ^ 1, row + 1, column);
		}

		State top() {
			return new State(toTop, toLeft, toRight, position ^ 1, position, row - 1, column);
		}

		State[] positions() {
			return new State[]{right(), left(), bottom(), top()};
		}
	}
}
