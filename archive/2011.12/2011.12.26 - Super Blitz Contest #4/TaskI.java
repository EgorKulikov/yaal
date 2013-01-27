package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String formula = in.readString();
		String[] tokens = formula.split("[+=]");
		for (int i = 2; i <= 36; i++) {
			try {
				if (Integer.parseInt(tokens[0], i) + Integer.parseInt(tokens[1], i) == Integer.parseInt(tokens[2], i)) {
					out.printLine(i);
					return;
				}
			} catch (NumberFormatException ignored) {}
		}
		out.printLine(-1);
	}
}
