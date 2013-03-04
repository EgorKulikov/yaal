package on2012_09.on2012_09_16_Latin_America___Brazil_Sub_Regional.TaskK;



import net.egork.collections.map.Indexer;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskK {
	private static final int SQUARES = 54;
	int[][] rotations = new int[6][SQUARES];
	int[] index = new int[256];

	{
		Square[] squares = new Square[SQUARES];
		Indexer<Square> indexer = new Indexer<Square>();
		int ii = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k <= 3; k += 3) {
					squares[ii++] = new Square(i, i + 1, j, j + 1, k, k);
					indexer.get(squares[ii - 1]);
					squares[ii++] = new Square(i, i + 1, k, k, j, j + 1);
					indexer.get(squares[ii - 1]);
					squares[ii++] = new Square(k, k, i, i + 1, j, j + 1);
					indexer.get(squares[ii - 1]);
				}
			}
		}
		Arrays.fill(index, -1);
		index['F'] = 0;
		index['B'] = 1;
		index['U'] = 2;
		index['D'] = 3;
		index['R'] = 4;
		index['L'] = 5;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < SQUARES; j++) {
				Square square = squares[j];
				if (i == 0 && square.x1 <= 1) {
					square = square.rotateYZ();
				} else if (i == 1 && square.x0 >= 2) {
					square = square.rotateYZ();
					square = square.rotateYZ();
					square = square.rotateYZ();
				} else if (i == 2 && square.y0 >= 2) {
					square = square.rotateXZ();
				} else if (i == 3 && square.y1 <= 1) {
					square = square.rotateXZ();
					square = square.rotateXZ();
					square = square.rotateXZ();
				} else if (i == 4 && square.z0 >= 2) {
					square = square.rotateXY();
				} else if (i == 5 && square.z1 <= 1) {
					square = square.rotateXY();
					square = square.rotateXY();
					square = square.rotateXY();
				}
				rotations[i][j] = indexer.get(square);
				if (indexer.get(square) >= SQUARES)
					throw new RuntimeException();
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		char[] sequence = in.readString().toCharArray();
		int[] permutation = new int[SQUARES];
		for (int i = 0; i < SQUARES; i++)
			permutation[i] = i;
		for (char c : sequence) {
			int index = this.index[Character.toUpperCase(c)];
			int count = Character.isUpperCase(c) ? 1 : 3;
			for (int i = 0; i < count; i++)
				permutation = multiply(permutation, rotations[index]);
		}
		long answer = 1;
		for (int i = 0; i < SQUARES; i++) {
			int length = 0;
			int current = i;
			do {
				length++;
				current = permutation[current];
			} while (current != i);
			answer = IntegerUtils.lcm(answer, length);
		}
		out.printLine(answer);
	}

	private int[] multiply(int[] p1, int[] p2) {
		int[] result = new int[SQUARES];
		for (int i = 0; i < SQUARES; i++)
			result[p2[i]] = p1[i];
		return result;
	}

	static class Square {
		int x0, x1, y0, y1, z0, z1;

		Square(int x0, int x1, int y0, int y1, int z0, int z1) {
			this.x0 = x0;
			this.x1 = x1;
			this.y0 = y0;
			this.y1 = y1;
			this.z0 = z0;
			this.z1 = z1;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Square square = (Square) o;

			if (x0 != square.x0) return false;
			if (x1 != square.x1) return false;
			if (y0 != square.y0) return false;
			if (y1 != square.y1) return false;
			if (z0 != square.z0) return false;
			if (z1 != square.z1) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = x0;
			result = 31 * result + x1;
			result = 31 * result + y0;
			result = 31 * result + y1;
			result = 31 * result + z0;
			result = 31 * result + z1;
			return result;
		}

		Square rotateYZ() {
			return new Square(x0, x1, 3 - z1, 3 - z0, y0, y1);
		}

		Square rotateXY() {
			return new Square(y0, y1, 3 - x1, 3 - x0, z0, z1);
		}

		Square rotateXZ() {
			return new Square(3 - z1, 3 - z0, y0, y1, x0, x1);
		}
	}
}
