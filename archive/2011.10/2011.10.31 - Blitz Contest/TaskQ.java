package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskQ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.readCharacter() == '-')
			in.readCharacter();
		out.printLine(in.readCharacter());
	}
}
