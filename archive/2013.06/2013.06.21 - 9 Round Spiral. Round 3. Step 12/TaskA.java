package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char last = in.readCharacter();
		int answer = 0;
		for (int i = 1; i < count; i++) {
			char current = in.readCharacter();
			if (current != last) {
				answer++;
				last = current;
			}
		}
		if (last != 'W')
			answer++;
		out.printLine(answer);
    }
}
