package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() * 2;
		char[][] programs = new char[count][];
		for (int i = 0; i < count; i++)
			programs[i] = in.readString().toCharArray();
		int[][] time = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++)
				time[i][j] = calculate(programs[i], programs[j]);
		}
		int[] answer = new int[1 << count];
		for (int i = 1; i < answer.length; i++) {
			if ((Integer.bitCount(i) & 1) != 0)
				continue;
			answer[i] = Integer.MAX_VALUE;
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 0)
					continue;
				for (int k = j + 1; k < count; k++) {
					if ((i >> k & 1) == 1)
						answer[i] = Math.min(answer[i], Math.max(answer[i - (1 << j) - (1 << k)], time[j][k]));
				}
			}
		}
		out.printLine(answer[answer.length - 1]);
	}

	private int calculate(char[] first, char[] second) {
		int[][] time = new int[first.length + 1][second.length + 1];
		for (int i = 0; i <= first.length; i++) {
			for (int j = 0; j <= second.length; j++) {
				if (i == 0 && j == 0)
					continue;
				time[i][j] = Integer.MAX_VALUE;
				if (i > 0)
					time[i][j] = Math.min(time[i][j], time[i - 1][j] + 1);
				if (j > 0)
					time[i][j] = Math.min(time[i][j], time[i][j - 1] + 1);
				if (i > 0 && j > 0 && first[i - 1] == second[j - 1])
					time[i][j] = Math.min(time[i][j], time[i - 1][j - 1] + 1);
			}
		}
		return time[first.length][second.length];
	}
}
