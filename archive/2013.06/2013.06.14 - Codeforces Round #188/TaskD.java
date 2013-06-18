package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] answer = {0, 1, 2, 1, 4, 3, 2, 1, 5, 6, 2, 1, 8, 7, 5, 9, 8, 7, 3, 4, 7, 4, 2, 1, 10, 9, 3, 6, 11, 12};
		int n = in.readInt();
		IntSet was = new IntHashSet();
		int i;
		int result = 1;
		for (i = 2; i * i <= n; i++) {
			if (was.contains(i))
				continue;
			int length = 0;
			for (long j = i; j <= n; j *= i) {
				was.add((int) j);
				length++;
			}
			result ^= answer[length];
		}
		int remaining = n - i + 1;
		for (int j : was.toArray()) {
			if (j >= i)
				remaining--;
		}
		result ^= remaining & 1;
		if (result != 0)
			out.printLine("Vasya");
		else
			out.printLine("Petya");
    }

}
