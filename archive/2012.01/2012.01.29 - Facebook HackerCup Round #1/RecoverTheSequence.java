package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RecoverTheSequence {
	int position;
	char[] sequence;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		sequence = in.readString().toCharArray();
		position = 0;
		int[] result = sort(0, count - 1);
		int[] answer = new int[count];
		for (int i = 0; i < count; i++)
			answer[result[i]] = i + 1;
		int hash = 1;
		for (int i : answer)
			hash = (31 * hash + i) % 1000003;
		System.err.println(Array.wrap(answer));
		out.printLine("Case #" + testNumber + ":", hash);
	}

	private int[] sort(int from, int to) {
		if (from == to)
			return new int[]{from};
		int middle = (from + to + 1) / 2;
		int[] left = sort(from, middle - 1);
		int[] right = sort(middle, to);
		int indexLeft = 0;
		int indexRight = 0;
		int[] result = new int[to - from + 1];
		while (indexLeft != left.length && indexRight != right.length) {
			if (sequence[position++] == '1') {
				result[indexLeft + indexRight] = left[indexLeft];
				indexLeft++;
			} else {
				result[indexLeft + indexRight] = right[indexRight];
				indexRight++;
			}
		}
		System.arraycopy(left, indexLeft, result, indexLeft + indexRight, left.length - indexLeft);
		indexLeft = left.length;
		System.arraycopy(right, indexRight, result, indexLeft + indexRight, right.length - indexRight);
		return result;
	}
}
