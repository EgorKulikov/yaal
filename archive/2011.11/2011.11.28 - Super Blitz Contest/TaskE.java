package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count % 10 == 1 && count % 100 != 11)
			out.printLine(count, "bochka");
		else if (count % 10 >= 2 && count % 10 <= 4 && count % 100 / 10 != 1)
			out.printLine(count, "bochki");
		else
			out.printLine(count, "bochek");
	}
}
