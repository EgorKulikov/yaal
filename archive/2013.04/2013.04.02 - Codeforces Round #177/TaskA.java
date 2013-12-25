package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int different = in.readInt();
		if (length < different || length > 1 && different == 1) {
			out.printLine(-1);
			return;
		}
		for (int i = 0; i < length - different; i++)
			out.print((char)('a' + (i & 1)));
		for (int i = 0; i < different; i++) {
			if (i < 2)
				out.print((char)('a' + ((length - different + i) & 1)));
			else
				out.print((char)('a' + i));
		}
		out.printLine();
    }
}
