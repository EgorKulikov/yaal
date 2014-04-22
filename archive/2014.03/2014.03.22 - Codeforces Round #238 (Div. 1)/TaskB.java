package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[] present = new boolean[1000000];
		for (int i = 0; i < count; i++)
			present[in.readInt() - 1] = true;
		int pairsNeeded = 0;
		IntList answer = new IntArrayList();
		for (int i = 0; i < 1000000; i++) {
			if (present[i]) {
				if (present[999999 - i])
					pairsNeeded++;
				else
					answer.add(1000000 - i);
			}
		}
		pairsNeeded >>= 1;
		for (int i = 0; pairsNeeded > 0; i++) {
			if (!present[i] && !present[999999 - i]) {
				answer.add(i + 1);
				answer.add(1000000 - i);
				pairsNeeded--;
			}
		}
		out.printLine(answer.size());
		out.printLine(answer.toArray());
    }
}
