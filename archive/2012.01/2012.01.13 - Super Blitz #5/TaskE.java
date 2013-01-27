package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = 0;
		char last = in.readCharacter();
		for (int i = 1; i < count; i++) {
			char current = in.readCharacter();
			if (last != current) {
				answer++;
				last = current;
			}
		}
		if (last == 'B')
			answer++;
		out.printLine(answer);
	}
}
