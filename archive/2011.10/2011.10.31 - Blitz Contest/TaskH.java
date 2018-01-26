package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char first = (char) (in.readCharacter() - '0');
		in.readCharacter();
		out.printLine((in.readCharacter() - '0') % first);
	}
}
