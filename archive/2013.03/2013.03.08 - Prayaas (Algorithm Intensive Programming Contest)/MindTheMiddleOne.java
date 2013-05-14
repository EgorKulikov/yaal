package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MindTheMiddleOne {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		if ((size & 1) == 0)
			out.printLine(-1);
		else {
			size >>= 1;
			out.printLine((long) size * size);
		}
    }
}
