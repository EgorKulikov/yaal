package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StackCuriosity {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstCount = in.readInt();
		for (int i = 0; i < firstCount; i++) {
			int size = in.readInt();
			int count = in.readInt();
			for (int j = 0; j < count; j++) {
				int[] permutation = IOUtils.readIntArray(in, size);
				boolean good = true;
				boolean seen = false;
				for (int k = 0; k < size; k++) {
					if (k != 0) {
						if (seen ^ permutation[k - 1] > permutation[k]) {
							good = false;
							break;
						}
					}
					if (permutation[k] == size)
						seen = true;
				}
				if (good)
					out.printLine(1);
				else
					out.printLine(0);
			}
		}
		int secondCount = in.readInt();
		for (int i = 0; i < secondCount; i++) {
			int size = in.readInt();
			int count = in.readInt();
			for (int j = 0; j < count; j++) {
				int first = in.readInt();
				if (first == size)
					out.printLine(1);
				else
					out.printLine(1L << (size - first - 1));
			}
		}
    }
}
