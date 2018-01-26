package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int answer = in.readCharacter() - '0';
		in.readCharacter();
		in.readCharacter();
		answer += in.readCharacter() - '0';
		out.printLine(answer);
	}
}
