package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Boxes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int first = in.readInt() - 1;
		int second = in.readInt() - 1;
		for (int i = 0; ; i++) {
			first &= ~(1 << i);
			second &= ~(1 << i);
			if (first == second) {
				out.printLine(first + 1, first + 1 + (1 << i));
				break;
			}
			out.printLine(first + 1, first + 1 + (1 << i), second + 1, (second + 1 + (1 << i) > count ? second + 1 : second + 1 + (1 << i)));
		}
	}
}
