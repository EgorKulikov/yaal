package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskN {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		char first = in.readCharacter();
		char second = in.readCharacter();
		for (int i = 0; i < number.length; i++) {
			if (number[i] == first)
				number[i] = second;
		}
		out.printLine(Long.parseLong(new String(number)));
	}
}
