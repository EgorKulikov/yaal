package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskP {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int upper = 0;
		int lower = 0;
		for (char c : in.readString().toCharArray()) {
			if (Character.isUpperCase(c))
				upper++;
			else
				lower++;
		}
		int lowerToUpper = in.readInt();
		int upperToLower = in.readInt();
		out.printLine(Math.min(lowerToUpper * lower, upperToLower * upper));
	}
}
