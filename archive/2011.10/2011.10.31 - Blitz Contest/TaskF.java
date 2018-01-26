package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readCharacter();
		char digit = in.readCharacter();
		if (digit != '0')
			out.print(digit);
		out.printLine(in.readCharacter());
	}
}
