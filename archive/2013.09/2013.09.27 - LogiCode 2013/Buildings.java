package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Buildings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		long left = 1;
		long right = 50000;
		while (left < right) {
			long middle = (left + right) >> 1;
			if (middle * (middle + 1) / 2 >= size)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
    }
}
