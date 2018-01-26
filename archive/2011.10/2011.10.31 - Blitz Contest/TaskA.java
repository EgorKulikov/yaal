package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readCharacter() - '0';
		int second = in.readCharacter() - '0';
		int number = first + second;
		out.printLine(number * number);
	}
}
