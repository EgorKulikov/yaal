package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = getMask(in);
		int second = getMask(in);
		int result = first & second;
		out.print("Case #" + testNumber + ": ");
		if (result == 0)
			out.printLine("Volunteer cheated!");
		else if (Integer.bitCount(result) == 1)
			out.printLine(1 + Integer.bitCount(result - 1));
		else
			out.printLine("Bad magician!");
    }

	private int getMask(InputReader in) {
		int row = in.readInt() - 1;
		int result = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int number = in.readInt() - 1;
				if (i == row)
					result |= 1 << number;
			}
		}
		return result;
	}
}
