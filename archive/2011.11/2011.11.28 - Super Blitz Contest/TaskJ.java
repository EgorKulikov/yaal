package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		int odd = 0;
		for (int i = 0; i < number.length; i += 2)
			odd += number[i] - '0';
		int even = 0;
		for (int i = 1; i < number.length; i += 2)
			even += number[i] - '0';
		out.printLine(odd * even);
	}
}
