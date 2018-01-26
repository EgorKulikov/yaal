package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] delta = new int[m + 1];
		for (int i = 0; i < n; i++) {
			int s = in.readInt() + in.readInt();
			if (s <= m) {
				delta[s]++;
			}
		}
		int answer = 0;
		int size = 0;
		for (int i = 0; i < m; i++) {
			if (in.readCharacter() == '+') {
				size++;
				answer += delta[size];
				size -= delta[size];
			} else {
				if (size > 0) {
					size--;
				}
			}
		}
		out.printLine(answer);
	}
}
