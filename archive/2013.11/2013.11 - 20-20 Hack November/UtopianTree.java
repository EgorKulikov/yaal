package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class UtopianTree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long result = 1;
		for (int i = 0; i < count; i++) {
			if ((i & 1) == 0)
				result *= 2;
			else
				result++;
		}
		out.printLine(result);
    }
}
