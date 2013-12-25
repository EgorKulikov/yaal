package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskR {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int maxLength = in.readInt();
		int length = 0;
		int current = 0;
		boolean first = true;
		while (true) {
			int bit = in.readInt();
			int oldLength = length;
			boolean same = bit == current;
			if (length == maxLength || bit != current) {
				if (first)
					first = false;
				else
					out.print(' ');
				out.print(length);
				length = 1;
				current = bit;
			} else
				length++;
			if (oldLength == maxLength && same)
				out.print(" 0");
			if (bit == 2)
				break;
		}
		out.printLine();
	}
}
