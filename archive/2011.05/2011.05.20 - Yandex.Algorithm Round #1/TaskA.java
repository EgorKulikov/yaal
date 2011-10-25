import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int length = in.readInt();
		char[][] answer = new char[4][length];
		for (int i = 0; i < length; i += 4) {
			if (i + 1 < length) {
				answer[0][i] = answer[0][i + 1] = 'a';
				answer[1][i] = answer[1][i + 1] = 'b';
			}
			if (i + 3 < length) {
				answer[0][i + 2] = answer[0][i + 3] = 'c';
				answer[1][i + 2] = answer[1][i + 3] = 'd';
			}
			if (i + 2 < length) {
				answer[2][i + 2] = answer[2][i + 1] = 'e';
				answer[3][i + 2] = answer[3][i + 1] = 'f';
			}
			if (i + 4 < length) {
				answer[2][i + 3] = answer[2][i + 4] = 'g';
				answer[3][i + 3] = answer[3][i + 4] = 'h';
			}
		}
		if (length % 2 == 1) {
			answer[0][length - 1] = 'i';
			answer[1][length - 1] = 'i';
			answer[2][0] = 'j';
			answer[3][0] = 'j';
		} else {
			answer[2][length - 1] = 'i';
			answer[3][length - 1] = 'i';
			answer[2][0] = 'j';
			answer[3][0] = 'j';
		}
		for (char[] row : answer)
			out.println(row);
	}
}

