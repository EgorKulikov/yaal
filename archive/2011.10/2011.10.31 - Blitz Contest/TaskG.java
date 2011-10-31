package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.print(in.readCharacter());
		char c = in.readCharacter();
		out.print(in.readCharacter());
		out.print(c);
		out.printLine(in.readCharacter());
	}
}
