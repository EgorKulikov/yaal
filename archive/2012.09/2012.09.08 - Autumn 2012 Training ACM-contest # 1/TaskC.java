package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int value = in.readCharacter() - '0';
		while (!in.isExhausted()) {
			char operation = in.readCharacter();
			int operand = in.readCharacter() - '0';
			if (operation == '+')
				value += operand;
			else
				value *= operand;
		}
		out.printLine(value);
	}
}
