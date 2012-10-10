package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int sharpness = in.readInt();
		if (sharpness == 3) {
			out.printLine(5);
			return;
		}
		for (int size = 1; ; size += 2) {
			int maxSharpness = (size * size + 1) / 2;
			if (maxSharpness >= sharpness) {
				out.printLine(size);
				return;
			}
		}
	}
}
