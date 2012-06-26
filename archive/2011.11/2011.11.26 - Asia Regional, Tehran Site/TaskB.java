package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] original = in.readString().toCharArray();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < original.length; i++) {
			if (i == 0 || original[i] != original[i - 1]) {
				builder.append(original[i]);
			}
		}
		original = builder.toString().toCharArray();
		int length = original.length;
		int[] result = new int[length];
		int left = 0;
		int right = -1;
		for (int i = 0; i < length; ++i) {
			int shift = (i > right ? 0 : Math.min(result[left + right - i], right - i)) + 1;
			while (i + shift < length && i - shift >= 0 && original[i + shift] == original[i - shift])
				shift++;
			result[i] = --shift;
			if (i + shift > right) {
				left = i - shift;
				right = i + shift;
			}
		}
		out.printLine(CollectionUtils.maxElement(Array.wrap(result)) + 1);
	}
}
