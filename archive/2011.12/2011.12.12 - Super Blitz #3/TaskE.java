package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int[] array = new int[length];
		for (int i = 0; i < length; i++)
			array[i] = i + 1;
		List<Integer> wrapped = Array.wrap(array);
		do {
			out.printLine(wrapped.toArray());
		} while (ListUtils.nextPermutation(wrapped));
	}
}
