package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int lastDigit = first[first.length - 1] - '0';
		int mod = 0;
		for (int i = second.length - 2; i < second.length; i++) {
			mod *= 10;
			if (i >= 0)
				mod += second[i] - '0';
		}
		if (mod == 0)
			mod = 4;
		int result = 1;
		for (int i = 0; i < mod; i++)
			result = result * lastDigit % 10;
		out.printLine(result);
	}
}
