package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskS {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[] column = new boolean[count];
		boolean[] firstDiagonal = new boolean[2 * count - 1];
		boolean[] secondDiagonal = new boolean[2 * count - 1];
		out.printLine(go(0, count, column, firstDiagonal, secondDiagonal));
	}

	private int go(int step, int count, boolean[] column, boolean[] firstDiagonal, boolean[] secondDiagonal) {
		if (step == count)
			return 1;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			if (!column[i] && !firstDiagonal[i + step] && !secondDiagonal[i - step + count - 1]) {
				column[i] = true;
				firstDiagonal[i + step] = true;
				secondDiagonal[i - step + count - 1] = true;
				answer += go(step + 1, count, column, firstDiagonal, secondDiagonal);
				column[i] = false;
				firstDiagonal[i + step] = false;
				secondDiagonal[i - step + count - 1] = false;
			}
		}
		return answer;
	}
}
