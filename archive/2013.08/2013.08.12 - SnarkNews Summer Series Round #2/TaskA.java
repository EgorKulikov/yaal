package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] start = in.readString().toCharArray();
		char[] end = in.readString().toCharArray();
		int answer = 0;
		while (true) {
			boolean move = false;
			int w = 0;
			int v = 0;
			for (int i = 0; i < start.length - 1; i++) {
				if (start[i] == 'W')
					w++;
				else
					v++;
				if (end[i] == 'W')
					w--;
				else
					v--;
				if (w > v && start[i] == 'W' && start[i + 1] == 'V') {
					start[i] = 'V';
					start[i + 1] = 'W';
					v++;
					if (end[i + 1] == 'W')
						w--;
					else
						v--;
					move = true;
					i++;
				} else if (w < v && start[i] == 'V' && start[i + 1] == 'W') {
					start[i] = 'W';
					start[i + 1] = 'V';
					w++;
					if (end[i + 1] == 'W')
						w--;
					else
						v--;
					move = true;
					i++;
				}
			}
			if (move)
				answer++;
			else
				break;
		}
		out.printLine(answer);
    }
}
