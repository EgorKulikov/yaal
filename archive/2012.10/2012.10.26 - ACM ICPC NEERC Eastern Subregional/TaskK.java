package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int side = in.readInt();
		int count = in.readInt();
		if (count % 2 != 0) {
			out.printLine("Unsuitable device");
			return;
		}
		for (int i = 1; i <= side / 2; i++) {
			if (2 * i * side < count)
				continue;
			out.printLine("Overwhelming power of magic");
			int small = (count / 2) / i;
			int big = small + 1;
			int bigCount = (count / 2) % i;
			int smallCount = i - bigCount;
			for (int j = 0; j < bigCount; j++) {
				for (int k = 1; k < big; k++)
					out.printLine(2 * j + 1, k + 1);
				for (int k = big - 1; k > 0; k--)
					out.printLine(2 * j + 2, k + 1);
			}
			for (int j = 0; j < smallCount; j++) {
				for (int k = 1; k < small; k++)
					out.printLine(2 * j + 1 + 2 * bigCount, k + 1);
				for (int k = small - 1; k > 0; k--)
					out.printLine(2 * j + 2 + 2 * bigCount, k + 1);
			}
			for (int j = 2 * i; j > 0; j--)
				out.printLine(j, 1);
			return;
		}
		if (side * side > count) {
			out.printLine("Overwhelming power of magic");
			for (int i = 0; i < side - 3; i += 2) {
				for (int j = 1; j < side; j++)
					out.printLine(i + 1, j + 1);
				for (int j = side - 1; j > 0; j--)
					out.printLine(i + 2, j + 1);
			}
			for (int j = 1; j < side; j++)
				out.printLine(side - 2, j + 1);
			int backCount = (count - side * (side - 1)) / 2 - 1;
			for (int j = 0; j < backCount; j++) {
				out.printLine(side - 1, side - 2 * j);
				out.printLine(side, side - 2 * j);
				out.printLine(side, side - 2 * j - 1);
				out.printLine(side - 1, side - 2 * j - 1);
			}
			out.printLine(side - 1, side - 2 * backCount);
			for (int j = side - 2 * backCount; j > 1; j--)
				out.printLine(side, j);
			for (int j = side; j > 0; j--)
				out.printLine(j, 1);
			return;
		}
		out.printLine("Unsuitable device");
	}
}
