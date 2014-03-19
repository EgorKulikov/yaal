package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[][] answer = new boolean[92][92];
		for (int i = 0; i < 29; i++) {
			answer[i + 2][i + 3] = true;
			answer[i + 2][i + 33] = true;
			answer[i + 32][i + 3] = true;
			answer[i + 32][i + 33] = true;
			answer[i + 62][i + 63] = true;
		}
		answer[31][1] = true;
		answer[61][1] = true;
		answer[0][62] = true;
		for (int i = 0; i < 30; i++) {
			if ((count >> i & 1) == 1)
				answer[62 + 29 - i][31 - i] = true;
		}
		out.printLine(92);
		for (int i = 0; i < 92; i++) {
			for (int j = 0; j < 92; j++) {
				if (answer[i][j] || answer[j][i])
					out.print('Y');
				else
					out.print('N');
			}
			out.printLine();
		}
    }
}
