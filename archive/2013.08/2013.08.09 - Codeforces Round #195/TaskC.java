package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		for (int i = 29; i >= 0; i--) {
			int all = -1;
			for (int j : array) {
				if ((j >> i & 1) == 1)
					all &= j;
			}
			if ((all & ((1 << i) - 1)) == 0) {
				IntList answer = new IntArrayList();
				for (int j : array) {
					if ((j >> i & 1) == 1)
						answer.add(j);
				}
				out.printLine(answer.size());
				out.printLine(answer);
				return;
			}
		}
		throw new RuntimeException();
    }
}
