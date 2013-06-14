package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String number = in.readString();
		int base1 = in.readInt();
		int base2 = in.readInt();
		try {
			out.printLine(Integer.toString(Integer.parseInt(number, base1), base2));
		} catch (NumberFormatException e) {
			out.printLine(-1);
		}
	}
}
