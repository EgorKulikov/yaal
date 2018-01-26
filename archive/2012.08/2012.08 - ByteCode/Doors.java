package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Doors {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		int answer = (int) (Math.sqrt(number) + 10);
		while (answer * answer > number)
			answer--;
		out.printLine(answer);
	}
}
