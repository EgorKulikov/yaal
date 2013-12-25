package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int j = 0;
		int k = 0;
		long answer = 0;
		int[][] qty = new int[6][second.length];
		for (int i = 1; i < second.length; i++) {
			for (int l = 0; l < 6; l++)
				qty[l][i] = qty[l][i - 1];
			if (second[i - 1] == 'R') {
				if (second[i] == 'G')
					qty[0][i]++;
				else if (second[i] == 'B')
					qty[1][i]++;
			} else if (second[i - 1] == 'G') {
				if (second[i] == 'R')
					qty[2][i]++;
				else if (second[i] == 'B')
					qty[3][i]++;
			} else if (second[i - 1] == 'B') {
				if (second[i] == 'R')
					qty[4][i]++;
				else if (second[i] == 'G')
					qty[5][i]++;
			}
		}
		char last = first[0];
		for (char c : first) {
			while (k + 1 < second.length && second[k] != c)
				k++;
			answer += k - j + 1;
			if (c == 'R') {
				if (last == 'G')
					answer -= qty[0][k] - qty[0][j];
				else if (last == 'B')
					answer -= qty[1][k] - qty[1][j];
			} else if (c == 'G') {
				if (last == 'R')
					answer -= qty[2][k] - qty[2][j];
				else if (last == 'B')
					answer -= qty[3][k] - qty[3][j];
			} else if (c == 'B') {
				if (last == 'R')
					answer -= qty[4][k] - qty[4][j];
				else if (last == 'G')
					answer -= qty[5][k] - qty[5][j];
			}
			last = c;
			if (second[j] == c)
				j++;
			if (j == second.length)
				break;
			if (second[k] == c)
				k = Math.min(k + 1, second.length - 1);
		}
		out.printLine(answer);
    }
}
